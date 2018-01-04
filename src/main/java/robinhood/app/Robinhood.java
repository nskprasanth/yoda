package robinhood;

import robinhood.apis.RobinhoodApi;
import robinhood.apis.LoginApi;
import robinhood.apis.response.LoginResponse;

public class Robinhood {

    private static RequestManager requestManager = null;
    private static ConfigManager configManager = null;

    public Robinhood() {
        requestManager = RequestManager.getInstance();
        configManager = ConfigManager.getInstance();
    }

    // TODO: Add exceptions
    public boolean login(String user, String pwd) {
        RobinhoodApi loginApi = new LoginApi(user, pwd);
        LoginResponse token = requestManager.callAPI(loginApi);
        if (token.getToken() == null) {
            return false;
        }
        configManager.setAuthToken(token.getToken());
        return true;
    }
}
