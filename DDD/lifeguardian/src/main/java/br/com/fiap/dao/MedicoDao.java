package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Medico;
import br.com.fiap.entity.Usuario;
import br.com.fiap.enums.MedicoAutenticado;

public class MedicoDao {

    private Connection conexao;

    public void inserir(Medico medico) throws SQLException {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        String sql = "insert into gs_medico (nome, crm, usuario_id, especialidade, formacao, autenticado)"
                + "values (?, ?, ?, ?, ?, ?)";
        comandoSql = conexao.prepareStatement(sql);
        comandoSql.setString(1, medico.getNome());
        comandoSql.setString(2, medico.getCrm());
        comandoSql.setInt(3, medico.getUsuario().getId());
        comandoSql.setString(4, medico.getEspecialidade());
        comandoSql.setString(5, medico.getFormacao());
        comandoSql.setString(6, medico.getAutenticado().toString());
        comandoSql.executeUpdate();
        conexao.close();
        comandoSql.close();
    }

    public void atualizar(Medico medico) {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement(
                    "update gs_medico set nome=?, especialidade=?, formacao=?, autenticado=?, usuario_id=?" + " where crm=?");

            comandoSql.setString(1, medico.getNome());            
            comandoSql.setString(2, medico.getEspecialidade());
            comandoSql.setString(3, medico.getFormacao());
            comandoSql.setString(4, medico.getAutenticado().toString());
            comandoSql.setInt(5, medico.getUsuario().getId());
            comandoSql.setString(6, medico.getCrm());
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
                medico.setNome(rs.getString(1));
                medico.setCrm(rs.getString(2));
                UsuarioDao usuarioDao = new UsuarioDao();
                int usuarioId = rs.getInt(3);
                Usuario usuario = usuarioDao.buscarUsuarioPorId(usuarioId);
                medico.setUsuario(usuario);
                medico.setEspecialidade(rs.getString(4));
                medico.setFormacao(rs.getString(5));
                String autenticado = rs.getString(6);
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
            	medico.setNome(rs.getString(1));
                medico.setCrm(rs.getString(2));
                UsuarioDao usuarioDao = new UsuarioDao();
                int usuarioId = rs.getInt(3);
                Usuario usuario = usuarioDao.buscarUsuarioPorId(usuarioId);
                medico.setUsuario(usuario);
                medico.setEspecialidade(rs.getString(4));
                medico.setFormacao(rs.getString(5));
                String autenticado = rs.getString(6);
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
