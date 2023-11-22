package br.com.fiap.entity;

import br.com.fiap.enums.MedicoAutenticado;

public class Medico {

	private String nome;
	private String crm;
	private String especialidade;
	private String formacao;
	private Usuario usuario;
	private MedicoAutenticado autenticado;

	// Constructors, getters, setters, and other methods can be added as needed
	
	public Medico() {
    	
    }

	public Medico(String nome, String crm, String especialidade, String formacao, MedicoAutenticado autenticado) {
		this.nome = nome;
		this.crm = crm;
		this.especialidade = especialidade;
		this.formacao = formacao;
		this.autenticado = autenticado;
	}
	
	// ToString
	
	@Override
    public String toString() {
        return "Medico{" +
                "nome='" + nome + '\'' +
                ", crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", formacao='" + formacao + '\'' +
                ", autenticado=" + autenticado +
                '}';
    }

	// Getter and setter methods for each attribute

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public MedicoAutenticado getAutenticado() {
		return autenticado;
	}

	public void setAutenticado(MedicoAutenticado autenticado) {
		this.autenticado = autenticado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
