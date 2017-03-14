package client.stone.hackathon.cmepclient.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kanda on 14/03/2017.
 */

public class Establishment implements Serializable {
    public Establishment() {

    }

    @SerializedName("url")
    private String url;
    @SerializedName("id")
    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Establishment{" +
                "url='" + url + '\'' +
                '}';
    }
}
