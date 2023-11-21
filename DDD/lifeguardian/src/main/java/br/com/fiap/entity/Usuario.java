package br.com.fiap.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Usuario {
    
    // attributes
    private int id;
    private String login;
    private String senha;

    // methods
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + "]";
    }

    // getters and setters
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
