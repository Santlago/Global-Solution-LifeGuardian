package br.com.fiap.bo;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.fiap.dao.PessoaDao;
import br.com.fiap.entity.Pessoa;

import java.util.List;

public class PessoaBo {
	private PessoaDao pessoaDao;

	public PessoaBo() {
		this.pessoaDao = new PessoaDao();
	}

	public void inserir(Pessoa pessoa) {
		if (isValidCPF(pessoa.getCpf()) && isValidAge(pessoa.getIdade())) {
			pessoa.setId(pessoaDao.buscarId());
			pessoaDao.inserir(pessoa);
		} else {
			// Handle invalid CPF
			System.out.println("Invalid CPF. The pessoa will not be inserted.");
		}
	}

	private boolean isValidAge(int age) {
		if (age >= 18) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isValidCPF(String cpf) {
		CPFValidator cpfValidator = new CPFValidator(); 
		List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);
		if (erros.size() > 0) {
			System.out.println(erros);
			return false;
		} else {
			return true;
		}
	}

	public void atualizar(Pessoa pessoa) {
		pessoaDao.atualizar(pessoa);
	}

	public List<Pessoa> buscarTodasPessoas() {
		return pessoaDao.buscarTodasPessoas();
	}

	public Pessoa buscarPessoaPorId(int id) {
		return pessoaDao.buscarPessoaPorId(id);
	}
}
