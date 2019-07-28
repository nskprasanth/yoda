package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.Transfers;

public class TransfersApi extends RobinhoodApi {

    public TransfersApi () {
        super("transfers");

        setRequestType(RequestType.GET);
        setAuthToken();

        setHeaderParams("Accept","application/json");

        setResponseType(Transfers.class);
        setUrlBase(APIEndpoints.ACH_TRANSFERS.toString());
    }
}
