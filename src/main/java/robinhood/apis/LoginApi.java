package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.LoginResponse;

public class LoginApi extends RobinhoodApi {

    public LoginApi(String user, String pwd, String deviceToken) {
        super("Login");

        setUrlBase(APIEndpoints.LOGIN.toString());

        setRequestType(RequestType.POST);

        setHeaderParams("Accept","application/json");
        setHeaderParams("Content-Type","application/x-www-form-urlencoded");

        setRequestParams("username", user);
        setRequestParams("password", pwd);
        setRequestParams("grant_type", "password");
        setRequestParams("client_id", "c82SH0WZOsabOXGP2sxqcj34FxkvfnWRZBKlBjFS");
        setRequestParams("device_token", deviceToken);

        setResponseType(LoginResponse.class);
    }

    public void setMfaCode(String code) {
        setRequestParams("mfa_code", code);
    }
}
