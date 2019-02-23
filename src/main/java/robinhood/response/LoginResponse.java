package robinhood.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String access_token = null;

    public String getToken() {
        return access_token;
    }

    @Override
    public String toString() {
        return getToken();
    }
}