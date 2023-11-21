package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Bicicleta;
import br.com.fiap.entity.Usuario;

public class BicicletaDao {

    // attributes
    private Connection conexao;

    // methods
    public void inserir(Bicicleta bicicleta) {
        try {
            conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            String sql = "insert into bicicleta (id, anofabricacao, marca, modelo, cor, numeroserie, usuario_id)"
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setInt(1, bicicleta.getId());
            comandoSql.setString(2, bicicleta.getAnoFabricacao());
            comandoSql.setString(3, bicicleta.getMarca());
            comandoSql.setString(4, bicicleta.getModelo());
            comandoSql.setString(5, bicicleta.getCor());
            comandoSql.setString(6, bicicleta.getNumeroSerie());
            comandoSql.setInt(7, bicicleta.getUsuario().getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problema no BicicletaDao");
        }
    }

    // alterar
    public void atualizar(Bicicleta bicicleta) {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("update bicicleta set anofabricacao=?, marca=?, modelo=?, cor=?, numeroserie=?, usuario_id=?"
                    + " where id=?");
            
            comandoSql.setString(1, bicicleta.getAnoFabricacao());
            comandoSql.setString(2, bicicleta.getMarca());
            comandoSql.setString(3, bicicleta.getModelo());
            comandoSql.setString(4, bicicleta.getCor());
            comandoSql.setString(5, bicicleta.getNumeroSerie());
            comandoSql.setInt(6, bicicleta.getUsuario().getId());
            comandoSql.setInt(7, bicicleta.getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // buscarTodos
    public List<Bicicleta> buscarTodasBicicletas() {
        List<Bicicleta> listaBicicletas = new ArrayList<>();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from bicicleta");
            ResultSet rs = comandoSql.executeQuery();
            while (rs.next()) {
                Bicicleta bicicleta = new Bicicleta();
                bicicleta.setId(rs.getInt("id"));
                bicicleta.setAnoFabricacao(rs.getString("anofabricacao"));
                bicicleta.setMarca(rs.getString("marca"));
                bicicleta.setModelo(rs.getString("modelo"));
                bicicleta.setCor(rs.getString("cor"));
                bicicleta.setNumeroSerie(rs.getString("numeroserie"));
                // Fetch the associated Usuario object.
                UsuarioDao usuarioDao = new UsuarioDao();
                int usuarioId = rs.getInt("usuario_id");
                Usuario usuario = usuarioDao.buscarUsuarioPorId(usuarioId);
                bicicleta.setUsuario(usuario);
                listaBicicletas.add(bicicleta);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaBicicletas;
    }

    // buscarPorId
    public Bicicleta buscarBicicletaPorId(int id) {
        Bicicleta bicicleta = new Bicicleta();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from bicicleta where id = ?");
            comandoSql.setInt(1, id);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                bicicleta.setId(rs.getInt("id"));
                bicicleta.setAnoFabricacao(rs.getString("anofabricacao"));
                bicicleta.setMarca(rs.getString("marca"));
                bicicleta.setModelo(rs.getString("modelo"));
                bicicleta.setCor(rs.getString("cor"));
                bicicleta.setNumeroSerie(rs.getString("numeroserie"));
                // Fetch the associated Usuario object.
                UsuarioDao usuarioDao = new UsuarioDao();
                int usuarioId = rs.getInt("usuario_id");
                Usuario usuario = usuarioDao.buscarUsuarioPorId(usuarioId);
                bicicleta.setUsuario(usuario);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problema no BicicletaDao");
        }
        return bicicleta;
    }

    public int buscarId() {
        var id = 0;
        try {
            conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            var sql = "select Max(id) from bicicleta";
            comandoSql = conexao.prepareStatement(sql);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            comandoSql.close();
            conexao.close();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return id + 1;
    }
}
