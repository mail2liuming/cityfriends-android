package nz.co.liuming.cityfriends.users.events;

/**
 * Created by liuming on 22/06/16.
 */
public class LoginEvent {

    public final String errorMsg;

    public LoginEvent() {
        this.errorMsg = null;
    }

    public LoginEvent(String message) {
        this.errorMsg = message;
    }
}
