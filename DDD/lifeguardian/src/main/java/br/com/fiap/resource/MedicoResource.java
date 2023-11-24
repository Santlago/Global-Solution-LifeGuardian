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

import br.com.fiap.bo.MedicoBo;
import br.com.fiap.entity.Medico;

@Path("/medico")
public class MedicoResource {
    
    private MedicoBo medicoBo;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Medico> listar(){
        medicoBo = new MedicoBo();
        return medicoBo.buscarTodosMedicos();
    }
    
    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Medico medico, @Context UriInfo uriInfo) {
        medicoBo = new MedicoBo();
        medicoBo.cadastrar(medico);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(medico.getCrm());
        
        return Response.created(builder.build()).build();
    }
    
    // BuscarPorId
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Medico buscarPorId(@PathParam("id") String crm){
        medicoBo = new MedicoBo();
        return medicoBo.buscarMedicoPorCrm(crm);
    }
    
    // Alterar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Medico medico, @Context UriInfo uriInfo) {
        medicoBo = new MedicoBo();
        medicoBo.atualizar(medico);
        return Response.ok().build();
    }
}
