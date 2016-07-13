package nz.co.liuming.cityfriends.common.rest.model;

/**
 * Created by liuming on 13/07/16.
 */
public class ResultResponse implements BaseModel {

    /**
     * success : false
     * message : no user
     * status : 501
     */


    private boolean success;
    private String message;
    private int status;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
