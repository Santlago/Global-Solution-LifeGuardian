package br.com.fiap.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pessoa {
	// attributes
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private int idade;
    private String genero;
    private Usuario usuario;

    // Constructors, getters, setters, and other methods can be added as needed
    
    public Pessoa() {
    	
    }

    public Pessoa(String nome, String cpf, String telefone, int idade, String genero, Usuario usuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.idade = idade;
        this.genero = genero;
        this.usuario = usuario;
    }

    // Getter and setter methods for each attribute

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return "Pessoa{" +
        		"id='" + id + '\'' +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", idade=" + idade +
                ", genero='" + genero + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}