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

import br.com.fiap.bo.SeguroBo;
import br.com.fiap.entity.Seguro;

@Path("/seguro")
public class SeguroResource {

    private SeguroBo seguroBo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Seguro> listar() {
        seguroBo = new SeguroBo();
        return seguroBo.buscarTodosSeguros();
    }

    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Seguro seguro, @Context UriInfo uriInfo) {
        seguroBo = new SeguroBo();
        seguroBo.inserir(seguro);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(seguro.getId()));

        return Response.created(builder.build()).build();
    }

    // BuscarPorId
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Seguro buscarPorId(@PathParam("id") int id) {
        seguroBo = new SeguroBo();
        return seguroBo.buscarSeguroPorId(id);
    }

    // Alterar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Seguro seguro, @Context UriInfo uriInfo) {
        seguroBo = new SeguroBo();
        seguroBo.atualizar(seguro);
        return Response.ok().build();
    }
}
