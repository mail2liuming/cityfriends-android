package nz.co.liuming.cityfriends.users.model;

/**
 * Created by liuming on 28/09/16.
 */

public class UserRequest {

    /**
     * name : july
     * email : july@gmail.com
     * password : 123456
     * password_confirmation : 123456
     */

    private String name;
    private String email;
    private String password;
    private String password_confirmation;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
}
