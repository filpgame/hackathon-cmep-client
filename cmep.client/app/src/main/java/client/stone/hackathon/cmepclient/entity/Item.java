package client.stone.hackathon.cmepclient.entity;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kanda on 14/03/2017.
 */
@IgnoreExtraProperties
public class Item implements Serializable {
    @SerializedName("foto")
    private String foto;
    @SerializedName("nome")
    private String nome;
    @SerializedName("preco")
    private long preco;
    @SerializedName("descricao")
    private String descricao;
    @SerializedName("status")
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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
}
