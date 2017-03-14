package client.stone.hackathon.cmepclient.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kanda on 14/03/2017.
 */

public class Menu implements Serializable {
    public Menu() {
    }

    @SerializedName("foto")
    private String foto;
    @SerializedName("nome")
    private String nome;
    @SerializedName("preco")
    private long preco;
    @SerializedName("descricao")
    private String descricao;


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getPreco() {
        return preco;
    }

    public void setPreco(long preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "foto='" + foto + '\'' +
                ", nome='" + nome + '\'' +
                ", preco='" + preco + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
