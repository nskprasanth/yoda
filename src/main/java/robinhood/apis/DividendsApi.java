package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.Dividends;

import java.net.URL;

public class DividendsApi extends RobinhoodApi {

    public DividendsApi() {
        super("Dividends");

        setRequestType(RequestType.GET);
        setAuthToken();

        setHeaderParams("Accept","application/json");

        setResponseType(Dividends.class);
        setUrlBase(APIEndpoints.DIVIDENDS.toString());
    }
}
