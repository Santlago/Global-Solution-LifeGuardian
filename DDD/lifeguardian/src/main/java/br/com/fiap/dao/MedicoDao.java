package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Medico;
import br.com.fiap.enums.MedicoAutenticado;

public class MedicoDao {

    private Connection conexao;

    public void inserir(Medico medico) throws SQLException {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        String sql = "insert into gs_medico (crm, nome, especialidade, formacao, autenticado)"
                + "values (?, ?, ?, ?, ?)";
        comandoSql = conexao.prepareStatement(sql);
        comandoSql.setString(1, medico.getCrm());
        comandoSql.setString(2, medico.getNome());
        comandoSql.setString(3, medico.getEspecialidade());
        comandoSql.setString(4, medico.getFormacao());
        comandoSql.setString(5, medico.getAutenticado().toString());
        comandoSql.executeUpdate();
        conexao.close();
        comandoSql.close();
    }

    public void atualizar(Medico medico) {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement(
                    "update gs_medico set nome=?, especialidade=?, formacao=?, autenticado=?" + " where crm=?");

            comandoSql.setString(1, medico.getNome());
            comandoSql.setString(2, medico.getEspecialidade());
            comandoSql.setString(3, medico.getFormacao());
            comandoSql.setString(4, medico.getAutenticado().toString());
            comandoSql.setString(5, medico.getCrm());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Medico> buscarTodosMedicos() {
        List<Medico> listaMedicos = new ArrayList<Medico>();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from gs_medico");
            ResultSet rs = comandoSql.executeQuery();
            while (rs.next()) {
                Medico medico = new Medico();
                medico.setCrm(rs.getString(1));
                medico.setNome(rs.getString(2));
                medico.setEspecialidade(rs.getString(3));
                medico.setFormacao(rs.getString(4));
                String autenticado = rs.getString(5);
                MedicoAutenticado autenticadoEnum = MedicoAutenticado.valueOf(autenticado);
                medico.setAutenticado(autenticadoEnum);
                listaMedicos.add(medico);
            }
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaMedicos;
    }

    public Medico buscarMedicoPorCrm(String crm) {
        Medico medico = new Medico();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from gs_medico where crm = ?");
            comandoSql.setString(1, crm);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                medico.setCrm(rs.getString(1));
                medico.setNome(rs.getString(2));
                medico.setEspecialidade(rs.getString(3));
                medico.setFormacao(rs.getString(4));
                String autenticado = rs.getString(5);
                MedicoAutenticado autenticadoEnum = MedicoAutenticado.valueOf(autenticado);
                medico.setAutenticado(autenticadoEnum);
            }
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medico;
    }
}
