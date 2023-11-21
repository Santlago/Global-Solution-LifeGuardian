package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Usuario;;

public class PessoaDao {
    
    // attributes
    private Connection conexao;

    // methods

    // inserir
    public void inserir(Pessoa pessoa) {
        try {
            conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            String sql = "insert into pessoa (id, nome, cpf, email, telefone, idade, genero, usuario_id)"
            + "values (?, ?, ?, ?, ?, ?, ?, ?)";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setInt(1, pessoa.getId());
            comandoSql.setString(2, pessoa.getNome());
            comandoSql.setString(3, pessoa.getCpf());
            comandoSql.setString(4, pessoa.getEmail());
            comandoSql.setString(5, pessoa.getTelefone());
            comandoSql.setInt(6, pessoa.getIdade());
            comandoSql.setString(7, pessoa.getGenero());
            comandoSql.setInt(8, pessoa.getUsuario().getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Problema no pessoaDao");
        }
    }

    //alterar
    public void atualizar(Pessoa pessoa) {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("update pessoa set nome=?, cpf=?, email=?, telefone=?, idade=?, genero=?, usuario_id=?"
                    + " where id=?");
            
            comandoSql.setString(1, pessoa.getNome());
            comandoSql.setString(2, pessoa.getCpf());
            comandoSql.setString(3, pessoa.getEmail());
            comandoSql.setString(4, pessoa.getTelefone());
            comandoSql.setInt(5, pessoa.getIdade());
            comandoSql.setString(6, pessoa.getGenero());
            comandoSql.setInt(7, pessoa.getUsuario().getId());
            comandoSql.setInt(8, pessoa.getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // buscar todos
    public List<Pessoa> buscarTodasPessoas() {
        List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from pessoa");
            ResultSet rs = comandoSql.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setIdade(rs.getInt("idade"));
                pessoa.setGenero(rs.getString("genero"));
                // Fetch the associated Usuario object.
                UsuarioDao usuarioDao = new UsuarioDao();
                int usuarioId = rs.getInt("usuario_id");
                Usuario usuario = usuarioDao.buscarUsuarioPorId(usuarioId);
                pessoa.setUsuario(usuario);
                listaPessoas.add(pessoa);
            }
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPessoas;
    }

    // buscar por id
    public Pessoa buscarPessoaPorId(int id) {
        Pessoa pessoa = new Pessoa();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from pessoa where id = ?");
            comandoSql.setInt(1, id);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setIdade(rs.getInt("idade"));
                pessoa.setGenero(rs.getString("genero"));
                // Fetch the associated Usuario object.
                UsuarioDao usuarioDao = new UsuarioDao();
                int usuarioId = rs.getInt("usuario_id");
                Usuario usuario = usuarioDao.buscarUsuarioPorId(usuarioId);
                pessoa.setUsuario(usuario);
            }
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoa;
    }
    

    public int buscarId() {
        var id = 0;
        try {
            conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            var sql = "select Max(id) from pessoa";
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
