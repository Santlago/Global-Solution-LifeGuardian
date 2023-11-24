package br.com.fiap.bo;

import br.com.fiap.dao.MedicoDao;
import br.com.fiap.entity.Medico;
import br.com.fiap.enums.MedicoAutenticado;
import br.com.fiap.exception.AuthenticationException;

import java.sql.SQLException;
import java.util.List;

public class MedicoBo {
    private MedicoDao medicoDao;

    public MedicoBo() {
        this.medicoDao = new MedicoDao();
    }

    public void cadastrar(Medico medico) {
        try {
            if (medico.getAutenticado() == MedicoAutenticado.ATIVO) {
                medicoDao.inserir(medico);
            } else {
                // Throw AuthenticationException for inactive doctors
                throw new AuthenticationException("Cannot register an inactive doctor.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the database exception appropriately
        }
    }

    public void atualizar(Medico medico) {
        medicoDao.atualizar(medico);
    }

    public List<Medico> buscarTodosMedicos() {
        return medicoDao.buscarTodosMedicos();
    }

    public Medico buscarMedicoPorCrm(String crm) {
        return medicoDao.buscarMedicoPorCrm(crm);
    }
}
