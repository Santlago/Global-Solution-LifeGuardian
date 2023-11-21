package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.entity.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioBo {
    private UsuarioDao usuarioDao;

    public UsuarioBo() {
        this.usuarioDao = new UsuarioDao();
    }

    public void cadastrar(Usuario usuario) {
        try {
            if (isValidPassword(usuario.getSenha())) {
                usuario.setId(usuarioDao.buscarId());
                usuarioDao.inserir(usuario);
            } else {
                // Handle password validation failure
                System.out.println("Password does not meet the criteria: at least 6 characters, 1 letter, and 1 number.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the database exception appropriately
        }
    }

    private boolean isValidPassword(String senha) {
        // Check if the password has at least 6 characters, 1 letter, and 1 number
        return senha != null && senha.length() >= 6 && senha.matches(".*[a-zA-Z].*") && senha.matches(".*\\d.*");
    }

    public void atualizar(Usuario usuario) {
        usuarioDao.atualizar(usuario);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioDao.buscarTodosUsuarios();
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDao.buscarUsuarioPorId(id);
    }
}
