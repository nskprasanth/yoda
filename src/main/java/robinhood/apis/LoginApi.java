package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.LoginResponse;

public class LoginApi extends RobinhoodApi {

    public LoginApi(String user, String pwd) {
        super("loginUser");

        setUrlBase(APIEndpoints.LOGIN.toString());

        setRequestType(RequestType.POST);

        setHeaderParams("Accept","application/json");
        setHeaderParams("Content-Type","application/x-www-form-urlencoded");

        setRequestParams("username", user);
        setRequestParams("password", pwd);

        setResponseType(LoginResponse.class);
    }
}
