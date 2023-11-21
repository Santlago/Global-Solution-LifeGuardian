package br.com.fiap.bo;

import br.com.fiap.dao.SeguroDao;
import br.com.fiap.entity.Seguro;

import java.util.List;

public class SeguroBo {
    private SeguroDao seguroDao;

    public SeguroBo() {
        this.seguroDao = new SeguroDao();
    }

    public void inserir(Seguro seguro) {
    	seguro.setId(seguroDao.buscarId());
        seguroDao.inserir(seguro);
    }

    public void atualizar(Seguro seguro) {
        seguroDao.atualizar(seguro);
    }

    public List<Seguro> buscarTodosSeguros() {
        return seguroDao.buscarTodosSeguros();
    }

    public Seguro buscarSeguroPorId(int id) {
        return seguroDao.buscarSeguroPorId(id);
    }
}
