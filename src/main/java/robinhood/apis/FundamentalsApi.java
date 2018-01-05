package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.Fundamentals;

public class FundamentalsApi extends RobinhoodApi {

    public FundamentalsApi(String ticker) {
        super("Fundamentals");

        setUrlBase(APIEndpoints.FUNDAMENTALS.toString() + ticker + "/");

        setRequestType(RequestType.GET);

        setHeaderParams("Accept","application/json");

        setResponseType(Fundamentals.class);
    }
}
