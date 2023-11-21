package br.com.fiap.bo;

import br.com.fiap.dao.EnderecoDao;
import br.com.fiap.entity.Endereco;

import java.util.List;

public class EnderecoBo {
    private EnderecoDao enderecoDao;

    public EnderecoBo() {
        this.enderecoDao = new EnderecoDao();
    }

    public void inserir(Endereco endereco) {
    	endereco.setId(enderecoDao.buscarId());
        enderecoDao.inserir(endereco);
    }

    public void atualizar(Endereco endereco) {
        enderecoDao.atualizar(endereco);
    }

    public List<Endereco> buscarTodosEnderecos() {
        return enderecoDao.buscarTodosEnderecos();
    }

    public Endereco buscarEnderecoPorId(int id) {
        return enderecoDao.buscarEnderecoPorId(id);
    }
}
