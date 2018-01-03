package robinhood.data;

public class AuthToken {

    private String token = null;

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return getToken();
    }
}
