package robinhood.apis;

import robinhood.APIEndpoints;
import robinhood.data.AuthToken;

public class LoginUser extends Api {

    public LoginUser(String user, String pwd) {
        super("loginUser");

        setUrlBase(APIEndpoints.LOGIN.toString());

        setRequestType(RequestType.POST);

        setHeaderParams("Accept","application/json");
        setHeaderParams("Content-Type","application/x-www-form-urlencoded");

        setRequestParams("username", user);
        setRequestParams("password", pwd);

        setReturnType(AuthToken.class);
    }
}
