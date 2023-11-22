package br.com.fiap.entity;

public class Ticket {
    private int id;
    private String assunto;
    private String comentario;

    // Constructors, getters, setters, and other methods can be added as needed

    public Ticket() {
    	
    }
    
    public Ticket(int id, String assunto, String comentario) {
        this.id = id;
        this.assunto = assunto;
        this.comentario = comentario;
    }
    
    // ToString
    
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", assunto='" + assunto + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }

    // Getter and setter methods for each attribute

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}