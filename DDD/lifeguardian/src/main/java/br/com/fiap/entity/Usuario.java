package br.com.fiap.entity;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.fiap.enums.UsuarioAutenticado;

@XmlRootElement

public class Usuario {
    private int id;
    private String email;
    private String login;
    private String senha;
    private UsuarioAutenticado autenticado;

    // Constructors, getters, setters, and other methods can be added as needed

    public Usuario() {
    	
    }
    
    public Usuario(int id, String email, String login, String senha, UsuarioAutenticado autenticado) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.autenticado = autenticado;
    }
    
    // ToString
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", autenticado=" + autenticado +
                '}';
    }

    // Getter and setter methods for each attribute

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioAutenticado getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(UsuarioAutenticado autenticado) {
        this.autenticado = autenticado;
    }
}

