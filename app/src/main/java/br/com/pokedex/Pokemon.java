package br.com.pokedex;

import java.util.ArrayList;

public class Pokemon {
    private int id;
    private String name;
    private String img;
    private String num;
    private String wight;
    private String height;
    private ArrayList<String> type = new ArrayList<>();;

    public Pokemon() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return img;
    }

    public void setImageUrl(String img) {
        this.img = img;
    }

    public String getNumero() {
        return num;
    }

    public void setNumero(String num) {
        this.num = num;
    }

    public String getPeso() {
        return wight;
    }

    public void setPeso(String wight) {
        this.wight = wight;
    }

    public String getAltura() {
        return height;
    }

    public void setAltura(String height) {
        this.height = height;
    }

    public void addTipo(String type) {
        this.type.add(type);
    }

    @Override
    public String toString() {
        return "Nome=" + name + '\n' +
                ", Imagem=" + img + '\n' +
                ", Número=" + num + '\n' +
                ", Largura=" + wight + '\n' +
                ", Altura=" + height + '\n';
    }
}
