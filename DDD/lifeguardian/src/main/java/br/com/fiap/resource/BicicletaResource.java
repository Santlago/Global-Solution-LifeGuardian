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

import br.com.fiap.bo.BicicletaBo;
import br.com.fiap.entity.Bicicleta;

@Path("/bicicleta")
public class BicicletaResource {

    private BicicletaBo bicicletaBo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bicicleta> listar() {
        bicicletaBo = new BicicletaBo();
        return bicicletaBo.buscarTodasBicicletas();
    }

    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Bicicleta bicicleta, @Context UriInfo uriInfo) {
        bicicletaBo = new BicicletaBo();
        bicicletaBo.inserir(bicicleta);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(bicicleta.getId()));

        return Response.created(builder.build()).build();
    }

    // BuscarPorId
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Bicicleta buscarPorId(@PathParam("id") int id) {
        bicicletaBo = new BicicletaBo();
        return bicicletaBo.buscarBicicletaPorId(id);
    }

    // Alterar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Bicicleta bicicleta, @Context UriInfo uriInfo) {
        bicicletaBo = new BicicletaBo();
        bicicletaBo.atualizar(bicicleta);
        return Response.ok().build();
    }
}
