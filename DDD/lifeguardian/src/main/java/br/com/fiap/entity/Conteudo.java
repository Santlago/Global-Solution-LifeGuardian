package br.com.fiap.entity;

public class Conteudo {
    private int id;
    private String imagem;
    private String texto;
    private Medico autor;

    // Constructors, getters, setters, and other methods can be added as needed

    public Conteudo() {
    	
    }
    
    public Conteudo(int id, String image, String text, Medico autor) {
        this.id = id;
        this.imagem = image;
        this.texto = text;
        this.autor = autor;
    }
    
    // ToString
    @Override
    public String toString() {
        return "Conteudo{" +
                "id=" + id +
                ", image='" + imagem + '\'' +
                ", text='" + texto + '\'' +
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

    public String getImage() {
        return imagem;
    }

    public void setImage(String image) {
        this.imagem = image;
    }

    public String getText() {
        return texto;
    }

    public void setText(String text) {
        this.texto = text;
    }

    public Medico getAutor() {
        return autor;
    }

    public void setAutor(Medico autor) {
        this.autor = autor;
    }
}