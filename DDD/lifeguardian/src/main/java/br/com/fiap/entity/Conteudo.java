package br.com.fiap.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Conteudo {
    private int id;
    private String imagem;
    private String texto;
    private Medico autor;

    // Constructors, getters, setters, and other methods can be added as needed

    public Conteudo() {
    	
    }
    
    public Conteudo(int id, String imagem, String texto, Medico autor) {
        this.id = id;
        this.imagem = imagem;
        this.texto = texto;
        this.autor = autor;
    }
    
    // ToString
    @Override
    public String toString() {
        return "Conteudo{" +
                "id=" + id +
                ", imagem='" + imagem + '\'' +
                ", texto='" + texto + '\'' +
                ", autor=" + autor +
                '}';
    }
    
    // Getter and setter methods for each attribute

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Medico getAutor() {
        return autor;
    }

    public void setAutor(Medico autor) {
        this.autor = autor;
    }
}