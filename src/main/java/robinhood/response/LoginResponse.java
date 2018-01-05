package robinhood.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String token = null;

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return getToken();
    }
}