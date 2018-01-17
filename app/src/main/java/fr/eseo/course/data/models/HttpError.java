package fr.eseo.course.data.models;

/**
 * Created by thibaud on 06/12/2017.
 */

public class HttpError {
    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}
