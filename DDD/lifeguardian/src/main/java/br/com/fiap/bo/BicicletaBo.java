package br.com.fiap.bo;

import br.com.fiap.dao.BicicletaDao;
import br.com.fiap.entity.Bicicleta;

import java.util.List;

public class BicicletaBo {
	private BicicletaDao bicicletaDao;

	public BicicletaBo() {
		this.bicicletaDao = new BicicletaDao();
	}

	public void inserir(Bicicleta bicicleta) {
		if (isValidYear(bicicleta.getAnoFabricacao())) {
			bicicleta.setId(bicicletaDao.buscarId());
			bicicletaDao.inserir(bicicleta);			
		} else {
			System.out.println("Ano de fabricação inválido");
		}
	}

	private boolean isValidYear(String year) {
		try {
			int yearValue = Integer.parseInt(year);
			return yearValue >= 2000;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void atualizar(Bicicleta bicicleta) {
		bicicletaDao.atualizar(bicicleta);
	}

	public List<Bicicleta> buscarTodasBicicletas() {
		return bicicletaDao.buscarTodasBicicletas();
	}

	public Bicicleta buscarBicicletaPorId(int id) {
		return bicicletaDao.buscarBicicletaPorId(id);
	}
}
