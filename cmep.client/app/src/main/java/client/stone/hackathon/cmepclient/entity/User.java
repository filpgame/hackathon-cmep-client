package client.stone.hackathon.cmepclient.entity;

import java.io.Serializable;

/**
 * Created by Kanda on 14/03/2017.
 */

public class User implements Serializable {
    private String id;
    private String name;

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
