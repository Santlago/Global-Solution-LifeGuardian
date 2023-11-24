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

import br.com.fiap.bo.ConteudoBo;
import br.com.fiap.entity.Conteudo;

@Path("/conteudo")
public class ConteudoResource {
    
    private ConteudoBo conteudoBo;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conteudo> listar(){
        conteudoBo = new ConteudoBo();
        return conteudoBo.buscarTodosConteudos();
    }
    
    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Conteudo conteudo, @Context UriInfo uriInfo) {
        conteudoBo = new ConteudoBo();
        conteudoBo.cadastrar(conteudo);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(conteudo.getId()));
        
        return Response.created(builder.build()).build();
    }
    
    // BuscarPorId
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Conteudo buscarPorId(@PathParam("id") int id){
        conteudoBo = new ConteudoBo();
        return conteudoBo.buscarConteudoPorId(id);
    }
    
    // Alterar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Conteudo conteudo, @Context UriInfo uriInfo) {
        conteudoBo = new ConteudoBo();
        conteudoBo.atualizar(conteudo);
        return Response.ok().build();
    }
}
