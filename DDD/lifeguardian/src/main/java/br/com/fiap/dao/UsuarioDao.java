package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Usuario;
import br.com.fiap.enums.UsuarioAutenticado;

public class UsuarioDao {
    
    // attributes
    private Connection conexao;

    // methods

    // inserir
    public void inserir(Usuario usuario) throws SQLException {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        String sql = "insert into gs_usuario (usuario_id, email, login, senha, autenticado)"
        + "values (?, ?, ?, ?, ?)";
        comandoSql = conexao.prepareStatement(sql);
        comandoSql.setInt(1, usuario.getId());
        comandoSql.setString(2, usuario.getEmail());
        comandoSql.setString(3, usuario.getLogin());
        comandoSql.setString(4, usuario.getSenha());
        comandoSql.setString(5, usuario.getAutenticado().toString());
        comandoSql.executeUpdate();
        conexao.close();
        comandoSql.close();
    }

    // alterar
    public void atualizar(Usuario usuario) {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("update gs_usuario set email=?, login=?, senha=?, autenticado=?"
						+ " where usuario_id=?");
            
            	comandoSql.setString(1, usuario.getEmail());
                comandoSql.setString(2, usuario.getLogin());
                comandoSql.setString(3, usuario.getSenha());
                comandoSql.setString(4, usuario.getAutenticado().toString());
                comandoSql.setInt(5, usuario.getId());
				comandoSql.executeUpdate();
				conexao.close();
				comandoSql.close();
        } catch (SQLException e) {
				e.printStackTrace();
		}
    }

    //buscarTodos
	public List<Usuario> buscarTodosUsuarios(){
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		conexao = DBManager.getConnection();
		PreparedStatement comandoSql = null;
		try {
			comandoSql = conexao.prepareStatement("select * from gs_usuario");
			ResultSet rs = comandoSql.executeQuery();
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt(1));
				usuario.setEmail(rs.getString(2));
				usuario.setLogin(rs.getString(3));
				usuario.setSenha(rs.getString(4));
				String autenticado = rs.getString(5);
				UsuarioAutenticado autenticadoEnum = UsuarioAutenticado.valueOf(autenticado);
				usuario.setAutenticado(autenticadoEnum);
				listaUsuarios.add(usuario);
			}
			conexao.close();
			comandoSql.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaUsuarios;
	}

    //buscarPorId
	public Usuario buscarUsuarioPorId(int id){
		Usuario usuario = new Usuario();
		conexao = DBManager.getConnection();
		PreparedStatement comandoSql = null;
		try {
			comandoSql = conexao.prepareStatement("select * from gs_usuario where usuario_id = ?");
			comandoSql.setInt(1, id);
			ResultSet rs = comandoSql.executeQuery();
			if(rs.next()) {
				usuario.setId(rs.getInt(1));
				usuario.setEmail(rs.getString(2));
				usuario.setLogin(rs.getString(3));
				usuario.setSenha(rs.getString(4));
				String autenticado = rs.getString(5);
				UsuarioAutenticado autenticadoEnum = UsuarioAutenticado.valueOf(autenticado);
				usuario.setAutenticado(autenticadoEnum);
			}
			conexao.close();
			comandoSql.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public int buscarId() {
        var id = 0;
        try {
            conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            var sql = "select Max(usuario_id) from gs_usuario";
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
