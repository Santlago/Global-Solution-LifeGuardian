package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Ticket;

public class TicketDao {

    private Connection conexao;

    public void inserir(Ticket ticket) throws SQLException {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        String sql = "insert into gs_ticket (ticket_id, assunto, comentario)"
                + "values (?, ?, ?)";
        comandoSql = conexao.prepareStatement(sql);
        comandoSql.setInt(1, ticket.getId());
        comandoSql.setString(2, ticket.getAssunto());
        comandoSql.setString(3, ticket.getComentario());
        comandoSql.executeUpdate();
        conexao.close();
        comandoSql.close();
    }

    public void atualizar(Ticket ticket) {
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("update gs_ticket set assunto=?, comentario=?"
						+ " where ticket_id=?");
            
            comandoSql.setString(1, ticket.getAssunto());
            comandoSql.setString(2, ticket.getComentario());
            comandoSql.setInt(3, ticket.getId());
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> buscarTodosTickets(){
        List<Ticket> listaTickets = new ArrayList<>();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from gs_ticket");
            ResultSet rs = comandoSql.executeQuery();
            while(rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt(1));
                ticket.setAssunto(rs.getString(2));
                ticket.setComentario(rs.getString(3));
                listaTickets.add(ticket);
            }
            conexao.close();
            comandoSql.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return listaTickets;
    }

    public Ticket buscarTicketPorId(int id){
        Ticket ticket = new Ticket();
        conexao = DBManager.getConnection();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("select * from gs_ticket where ticket_id = ?");
            comandoSql.setInt(1, id);
            ResultSet rs = comandoSql.executeQuery();
            if(rs.next()) {
                ticket.setId(rs.getInt(1));
                ticket.setAssunto(rs.getString(2));
                ticket.setComentario(rs.getString(3));
            }
            conexao.close();
            comandoSql.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public int buscarId() {
        var id = 0;
        try {
            conexao = DBManager.getConnection();
            PreparedStatement comandoSql = null;
            var sql = "select Max(ticket_id) from gs_ticket";
            comandoSql = conexao.prepareStatement(sql);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            comandoSql.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id + 1;
    }
}
