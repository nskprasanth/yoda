package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.Quote;

import java.net.URL;

public class QuotesApi extends RobinhoodApi {

    public QuotesApi(String symbol) {
        super("Quotes");
        init();
        setUrlBase(APIEndpoints.QUOTES.toString() + symbol);
    }

    public QuotesApi(URL url) {
        super("Quotes");
        init();
        setUrlBase(url.toString());
    }

    private void init() {
        setRequestType(RequestType.GET);

        setHeaderParams("Accept","application/json");

        setResponseType(Quote.class);
    }

}
