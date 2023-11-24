package br.com.fiap.entity;

public record EnderecoViaCep(String cep, String logradouro, String numero, String bairro, String localidade, String uf) {

	public String cep() {
		return cep;
	}

	public String rua() {
		return logradouro;
	}

	public String numero() {
		return numero;
	}

	public String bairro() {
		return bairro;
	}

	public String cidade() {
		return localidade;
	}

	public String uf() {
		return uf;
	}
    
}
