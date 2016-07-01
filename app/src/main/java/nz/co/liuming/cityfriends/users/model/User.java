package nz.co.liuming.cityfriends.users.model;

import nz.co.liuming.cityfriends.common.rest.model.BaseModel;

/**
 * Created by liuming on 17/06/16.
 */
public class User implements BaseModel{

    /**
     * id : 1
     * name : Example User
     * email : example@railstutorial.org
     * token : bzQ-YX_0FtBuAzdO6J8RBw
     */

    private int id;
    private String name;
    private String email;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
