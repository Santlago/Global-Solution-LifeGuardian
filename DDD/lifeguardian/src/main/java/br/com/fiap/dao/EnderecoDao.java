package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Usuario;

public class EnderecoDao {
    
    // attributes
    private Connection conexao;

    // methods
    
    // Insert a new Endereco record
    public void inserir(Endereco endereco) {
        try {
            Connection conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            String sql = "insert into endereco (id, cep, rua, numero, bairro, cidade, uf, pessoa_id)"
                    + "values (?, ?, ?, ?, ?, ?, ?, ?)";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setInt(1, endereco.getId());
            comandoSql.setString(2, endereco.getCep());
            comandoSql.setString(3, endereco.getRua());
            comandoSql.setString(4, endereco.getNumero());
            comandoSql.setString(5, endereco.getBairro());
            comandoSql.setString(6, endereco.getCidade());
            comandoSql.setString(7, endereco.getUf());
            comandoSql.setInt(8, endereco.getPessoa().getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problema no EnderecoDao");
        }
    }

    // Update an existing Endereco record
    public void atualizar(Endereco endereco) {
        Connection conexao = DBManager.getConnection();
        try {
            PreparedStatement comandoSql = conexao.prepareStatement(
                    "update endereco set cep=?, rua=?, numero=?, bairro=?, cidade=?, uf=?, pessoa_id=? where id=?");

            comandoSql.setString(1, endereco.getCep());
            comandoSql.setString(2, endereco.getRua());
            comandoSql.setString(3, endereco.getNumero());
            comandoSql.setString(4, endereco.getBairro());
            comandoSql.setString(5, endereco.getCidade());
            comandoSql.setString(6, endereco.getUf());
            comandoSql.setInt(7, endereco.getPessoa().getId());
            comandoSql.setInt(8, endereco.getId());
            comandoSql.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all Endereco records
    public List<Endereco> buscarTodosEnderecos() {
        List<Endereco> listaEnderecos = new ArrayList<>();
        Connection conexao = DBManager.getConnection();
        try {
            PreparedStatement comandoSql = conexao.prepareStatement("select * from endereco");
            ResultSet rs = comandoSql.executeQuery();
            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setCep(rs.getString("cep"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                // Fetch the associated Usuario object.
                PessoaDao pessoaDao = new PessoaDao();
                int pessoaId = rs.getInt("pessoa_id");
                Pessoa pessoa = pessoaDao.buscarPessoaPorId(pessoaId);
                endereco.setPessoa(pessoa);
                listaEnderecos.add(endereco);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEnderecos;
    }

    // Retrieve an Endereco record by its ID
    public Endereco buscarEnderecoPorId(int id) {
        Endereco endereco = new Endereco();
        Connection conexao = DBManager.getConnection();
        try {
            PreparedStatement comandoSql = conexao.prepareStatement("select * from endereco where id = ?");
            comandoSql.setInt(1, id);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                endereco.setId(rs.getInt("id"));
                endereco.setCep(rs.getString("cep"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setUf(rs.getString("uf"));
                // Fetch the associated Usuario object.
                PessoaDao pessoaDao = new PessoaDao();
                int pessoaId = rs.getInt("pessoa_id");
                Pessoa pessoa = pessoaDao.buscarPessoaPorId(pessoaId);
                endereco.setPessoa(pessoa);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }


    public int buscarId() {
        var id = 0;
        try {
            conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            var sql = "select Max(id) from endereco";
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
