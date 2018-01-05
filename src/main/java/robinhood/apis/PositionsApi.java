package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.Positions;

public class PositionsApi extends RobinhoodApi {
    public PositionsApi() {
        super("Positions");

        setUrlBase(APIEndpoints.POSITIONS.toString());

        setAuthToken();

        setRequestType(RequestType.GET);

        setHeaderParams("Accept","application/json");

        setResponseType(Positions.class);
    }
}
