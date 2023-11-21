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

import br.com.fiap.bo.UsuarioBo;
import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.entity.Usuario;

@Path("/usuario")
public class UsuarioResource {
	
	private UsuarioBo usuarioBo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listar(){
		usuarioBo = new UsuarioBo();
		return usuarioBo.buscarTodosUsuarios();
	}
	
	// Inserir
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Usuario usuario, @Context UriInfo uriInfo) {
		usuarioBo = new UsuarioBo();
		usuarioBo.cadastrar(usuario);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(usuario.getId()));
		
		return Response.created(builder.build()).build();
	}
	
	// BuscarPorId
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario buscarPorId(@PathParam("id") int id){
		usuarioBo = new UsuarioBo();
		return usuarioBo.buscarUsuarioPorId(id);
	}
	
	// Alterar
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(Usuario usuario, @Context UriInfo uriInfo) {
		usuarioBo = new UsuarioBo();
		usuarioBo.atualizar(usuario);
		return Response.ok().build();
	}
}
