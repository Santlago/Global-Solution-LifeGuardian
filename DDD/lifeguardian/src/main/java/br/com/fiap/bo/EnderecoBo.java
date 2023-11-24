package br.com.fiap.bo;

import br.com.fiap.dao.EnderecoDao;
import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.EnderecoViaCep;
import br.com.fiap.service.ConsultaCepService;

import java.util.List;

public class EnderecoBo {
    private EnderecoDao enderecoDao;
    private ConsultaCepService consultaCepService;

    public EnderecoBo() {
        this.enderecoDao = new EnderecoDao();
        this.consultaCepService = new ConsultaCepService();
    }

    public void inserir(Endereco endereco) {
        try {
        	EnderecoViaCep enderecoViaCep = consultaCepService.buscaEndereco(endereco.getCep());
            // Fetch address information from ViaCEP based on the provided CEP
            // Update the Endereco object with information from ViaCEP
        	endereco.setCep(enderecoViaCep.cep());
            endereco.setRua(enderecoViaCep.rua());
            // Assuming 'numero' in Endereco class corresponds to 'number' in EnderecoViaCep
            endereco.setBairro(enderecoViaCep.bairro());
            endereco.setCidade(enderecoViaCep.cidade());
            endereco.setUf(enderecoViaCep.uf());

            // Set the ID and insert the address into the database
            endereco.setId(enderecoDao.buscarId());
            enderecoDao.inserir(endereco);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately (log, rethrow, etc.)
        }
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
