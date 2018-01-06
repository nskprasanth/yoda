package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.Dividends;
import robinhood.response.Historicals;

public class HistoricalsApi extends RobinhoodApi {

    public HistoricalsApi() {
        super("Historicals");

        setRequestType(RequestType.GET);
        setAuthToken();

        setHeaderParams("Accept","application/json");

        setResponseType(Historicals.class);
        setUrlBase(APIEndpoints.HISTORICALS.toString().concat("5RZ51344/"));
        setRequestParams("symbol", "DPZ");
        setRequestParams("interval", "5minute");
        setRequestParams("span", "day");

    }
}
