package br.com.fiap.entity;

import br.com.fiap.enums.Plano;
import br.com.fiap.enums.Status;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Seguro {
    
    // attributes
    private int id;
    private Plano plano;
    private Status status;
    private Usuario usuario;

    // getters and setters
    public Plano getPlano() {
        return plano;
    }
    public void setPlano(Plano planos) {
        this.plano = planos;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
