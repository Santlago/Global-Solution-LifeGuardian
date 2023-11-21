package br.com.fiap.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bicicleta {

    // attributes
    private int id;
    private String anoFabricacao;
    private String marca;
    private String modelo;
    private String cor;
    private String numeroSerie;
    private Usuario usuario;

    // getters and setters
    public String getAnoFabricacao() {
        return anoFabricacao;
    }
    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getNumeroSerie() {
        return numeroSerie;
    }
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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
