package br.com.fiap.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.bo.TicketBo;
import br.com.fiap.entity.Ticket;

@Path("/ticket")
public class TicketResource {
    
    private TicketBo ticketBo;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> listar(){
        ticketBo = new TicketBo();
        return ticketBo.buscarTodosTickets();
    }
    
    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Ticket ticket, @Context UriInfo uriInfo) {
        ticketBo = new TicketBo();
        ticketBo.cadastrar(ticket);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(ticket.getId()));
        
        return Response.created(builder.build()).build();
    }
    
    // BuscarPorId
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ticket buscarPorId(@PathParam("id") int id){
        ticketBo = new TicketBo();
        return ticketBo.buscarTicketPorId(id);
    }
    
    // Alterar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Ticket ticket, @Context UriInfo uriInfo) {
        ticketBo = new TicketBo();
        ticketBo.atualizar(ticket);
        return Response.ok().build();
    }
}
