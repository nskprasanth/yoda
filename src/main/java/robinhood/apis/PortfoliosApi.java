package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.Portfolios;

public class PortfoliosApi extends RobinhoodApi {
    public PortfoliosApi() {
        super("Portfolios");

        // This API requires authentication token
        setAuthToken();

        setUrlBase(APIEndpoints.PORTFOLIOS.toString());

        setRequestType(RequestType.GET);

        setHeaderParams("Accept","application/json");

        setResponseType(Portfolios.class);
    }
}
