package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Conteudo;
import br.com.fiap.entity.Medico;

public class ConteudoDao {

    private Connection conexao;

    public void inserir(Conteudo conteudo) throws SQLException {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        String sql = "insert into gs_conteudo (conteudo_id, medico_crm, imagem, texto)"
                + "values (?, ?, ?, ?)";
        comandoSql = conexao.prepareStatement(sql);
        comandoSql.setInt(1, conteudo.getId());
        comandoSql.setString(2, conteudo.getAutor().getCrm());
        comandoSql.setString(3, conteudo.getImagem());
        comandoSql.setString(4, conteudo.getTexto());
        comandoSql.executeUpdate();
        conexao.close();
        comandoSql.close();
    }

    public void atualizar(Conteudo conteudo) {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("update gs_conteudo set medico_crm=?, imagem=?, texto=?"
                        + " where conteudo_id=?");
            
            comandoSql.setString(1, conteudo.getAutor().getCrm());
            comandoSql.setString(2, conteudo.getImagem());
            comandoSql.setString(3, conteudo.getTexto());
            comandoSql.setInt(4, conteudo.getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Conteudo> buscarTodosConteudos() {
        List<Conteudo> listaConteudos = new ArrayList<>();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from gs_conteudo");
            ResultSet rs = comandoSql.executeQuery();
            while (rs.next()) {
                Conteudo conteudo = new Conteudo();
                conteudo.setId(rs.getInt(1));
                String autorId = rs.getString(2);
                Medico autor = new MedicoDao().buscarMedicoPorCrm(autorId);
                conteudo.setAutor(autor);
                conteudo.setImagem(rs.getString(3));
                conteudo.setTexto(rs.getString(4));
                
                listaConteudos.add(conteudo);
            }
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaConteudos;
    }

    public Conteudo buscarConteudoPorId(int id) {
        Conteudo conteudo = new Conteudo();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from gs_conteudo where conteudo_id = ?");
            comandoSql.setInt(1, id);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                conteudo.setId(rs.getInt(1));
                conteudo.setImagem(rs.getString(2));
                conteudo.setTexto(rs.getString(3));
                String autorId = rs.getString(2);
                Medico autor = new MedicoDao().buscarMedicoPorCrm(autorId);
                conteudo.setAutor(autor);
            }
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conteudo;
    }

    public int buscarId() {
        var id = 0;
        try {
            conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            var sql = "select Max(conteudo_id) from gs_conteudo";
            comandoSql = conexao.prepareStatement(sql);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            comandoSql.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id + 1;
    }
}
