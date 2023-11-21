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

import br.com.fiap.bo.PessoaBo;
import br.com.fiap.entity.Pessoa;

@Path("/pessoa")
public class PessoaResource {
	
	private PessoaBo pessoaBo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pessoa> listar(){
		pessoaBo = new PessoaBo();
		return pessoaBo.buscarTodasPessoas();
	}
	
	// Inserir
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Pessoa pessoa, @Context UriInfo uriInfo) {
		pessoaBo = new PessoaBo();
		pessoaBo.inserir(pessoa);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(pessoa.getId()));
		
		return Response.created(builder.build()).build();
	}
	
	// BuscarPorId
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa buscarPorId(@PathParam("id") int id){
		pessoaBo = new PessoaBo();
		return pessoaBo.buscarPessoaPorId(id);
	}
	
	// Alterar
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(Pessoa pessoa, @Context UriInfo uriInfo) {
		pessoaBo = new PessoaBo();
		pessoaBo.atualizar(pessoa);
		return Response.ok().build();
	}
}
