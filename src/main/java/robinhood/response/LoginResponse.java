package robinhood.apis.response;

public class LoginResponse {

    private String token = null;

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return getToken();
    }
}