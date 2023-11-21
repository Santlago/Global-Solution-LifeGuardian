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

import br.com.fiap.bo.EnderecoBo;
import br.com.fiap.entity.Endereco;

@Path("/endereco")
public class EnderecoResource {

    private EnderecoBo enderecoBo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Endereco> listar() {
        enderecoBo = new EnderecoBo();
        return enderecoBo.buscarTodosEnderecos();
    }

    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Endereco endereco, @Context UriInfo uriInfo) {
        enderecoBo = new EnderecoBo();
        enderecoBo.inserir(endereco);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(endereco.getId()));

        return Response.created(builder.build()).build();
    }

    // BuscarPorId
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Endereco buscarPorId(@PathParam("id") int id) {
        enderecoBo = new EnderecoBo();
        return enderecoBo.buscarEnderecoPorId(id);
    }

    // Alterar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Endereco endereco, @Context UriInfo uriInfo) {
        enderecoBo = new EnderecoBo();
        enderecoBo.atualizar(endereco);
        return Response.ok().build();
    }
}
