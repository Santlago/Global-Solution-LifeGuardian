package br.com.fiap.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Seguro;
import br.com.fiap.enums.Plano;
import br.com.fiap.enums.Status;

public class SeguroDao {

    // attributes
    private Connection conexao;

    // methods
    // inserir
    public void inserir(Seguro seguro) {
        try {
            conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            String sql = "insert into seguro (id, plano, ativo, usuario_id)"
                    + "values (?, ?, ?, ?)";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setInt(1, seguro.getId());
            comandoSql.setString(2, seguro.getPlano().toString());
            comandoSql.setString(3, seguro.getStatus().toString());
            comandoSql.setInt(4, seguro.getUsuario().getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problema no SeguroDao");
        }
    }

    // alterar
    public void atualizar(Seguro seguro) {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("update seguro set plano=?, ativo=?, usuario_id=?"
                    + " where id=?");
            
            comandoSql.setString(1, seguro.getPlano().toString());
            comandoSql.setString(2, seguro.getStatus().toString());
            comandoSql.setInt(3, seguro.getUsuario().getId());
            comandoSql.setInt(4, seguro.getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // buscarTodos
    public List<Seguro> buscarTodosSeguros() {
        List<Seguro> listaSeguros = new ArrayList<>();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from seguro");
            ResultSet rs = comandoSql.executeQuery();
            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setId(rs.getInt("id"));
                
                String plano = rs.getString("plano");
				Plano planoEnum = Plano.valueOf(plano);
				seguro.setPlano(planoEnum);

				String status = rs.getString("ativo");
				Status statusEnum = Status.valueOf(status);
				seguro.setStatus(statusEnum);
                
                UsuarioDao usuarioDao = new UsuarioDao();
                int usuarioId = rs.getInt("usuario_id");
                seguro.setUsuario(usuarioDao.buscarUsuarioPorId(usuarioId));
                
                listaSeguros.add(seguro);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaSeguros;
    }

    // buscarPorId
    public Seguro buscarSeguroPorId(int id) {
        Seguro seguro = new Seguro();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from seguro where id = ?");
            comandoSql.setInt(1, id);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                seguro.setId(rs.getInt("id"));
                
                String plano = rs.getString("plano");
				Plano planoEnum = Plano.valueOf(plano);
				seguro.setPlano(planoEnum);

				String status = rs.getString("ativo");
				Status statusEnum = Status.valueOf(status);
				seguro.setStatus(statusEnum);
                
                UsuarioDao usuarioDao = new UsuarioDao();
                int usuarioId = rs.getInt("usuario_id");
                seguro.setUsuario(usuarioDao.buscarUsuarioPorId(usuarioId));
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seguro;
    }
    
    public int buscarId() {
        var id = 0;
        try {
            conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            var sql = "select Max(id) from seguro";
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
