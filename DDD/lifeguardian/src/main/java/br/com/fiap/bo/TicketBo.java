package br.com.fiap.bo;

import br.com.fiap.dao.TicketDao;
import br.com.fiap.entity.Ticket;

import java.sql.SQLException;
import java.util.List;

public class TicketBo {
    private TicketDao ticketDao;

    public TicketBo() {
        this.ticketDao = new TicketDao();
    }

    public void cadastrar(Ticket ticket) {
        try {
            ticket.setId(ticketDao.buscarId());
            ticketDao.inserir(ticket);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the database exception appropriately
        }
    }

    public void atualizar(Ticket ticket) {
        ticketDao.atualizar(ticket);
    }

    public List<Ticket> buscarTodosTickets() {
        return ticketDao.buscarTodosTickets();
    }

    public Ticket buscarTicketPorId(int id) {
        return ticketDao.buscarTicketPorId(id);
    }
}
