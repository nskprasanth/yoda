package robinhood;

import robinhood.apis.Api;
import robinhood.apis.LoginUser;
import robinhood.data.AuthToken;

public class Robinhood {

    private static RequestManager requestManager = null;
    private static ConfigManager configManager = null;

    public Robinhood() {
        requestManager = RequestManager.getInstance();
        configManager = ConfigManager.getInstance();
    }

    // TODO: Add exceptions
    public boolean login(String user, String pwd) {
        Api loginApi = new LoginUser(user, pwd);
        AuthToken token = requestManager.callAPI(loginApi);
        if (token.getToken() == null) {
            return false;
        }
        configManager.setAuthToken(token.getToken());
        return true;
    }
}
