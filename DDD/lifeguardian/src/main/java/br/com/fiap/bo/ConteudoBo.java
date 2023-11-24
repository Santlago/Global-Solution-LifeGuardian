package br.com.fiap.bo;

import br.com.fiap.dao.ConteudoDao;
import br.com.fiap.entity.Conteudo;

import java.sql.SQLException;
import java.util.List;

public class ConteudoBo {
    private ConteudoDao conteudoDao;

    public ConteudoBo() {
        this.conteudoDao = new ConteudoDao();
    }

    public void cadastrar(Conteudo conteudo) {
        try {
            conteudo.setId(conteudoDao.buscarId());
            conteudoDao.inserir(conteudo);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the database exception appropriately
        }
    }

    public void atualizar(Conteudo conteudo) {
        conteudoDao.atualizar(conteudo);
    }

    public List<Conteudo> buscarTodosConteudos() {
        return conteudoDao.buscarTodosConteudos();
    }

    public Conteudo buscarConteudoPorId(int id) {
        return conteudoDao.buscarConteudoPorId(id);
    }
}
