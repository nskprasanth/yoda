package robinhood.app;

import common.Api;
import robinhood.apis.LoginApi;
import common.RequestManager;
import robinhood.config.ConfigManager;
import robinhood.response.LoginResponse;

public class Robinhood {

    private static RequestManager requestManager = null;
    private static ConfigManager configManager = null;

    public Robinhood() {
        requestManager = RequestManager.getInstance();
        configManager = ConfigManager.getInstance();
    }

    // TODO: Add exceptions
    public boolean login(String user, String pwd) {
        Api loginApi = new LoginApi(user, pwd);
        LoginResponse token = requestManager.callAPI(loginApi);
        if (token.getToken() == null) {
            return false;
        }
        configManager.setAuthToken(token.getToken());
        return true;
    }
}
