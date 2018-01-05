package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.Instruments;

import java.net.URL;

public class InstrumentsApi extends RobinhoodApi {

    public InstrumentsApi(String id) {
        super("Instruements");
        init();
        setUrlBase(APIEndpoints.INSTRUMENTS.toString() + id);
    }

    public InstrumentsApi(URL url) {
        super("Instruments");
        init();
        setUrlBase(url.toString());
    }

    private void init() {
        setRequestType(RequestType.GET);

        setHeaderParams("Accept","application/json");

        setResponseType(Instruments.class);
    }

}
