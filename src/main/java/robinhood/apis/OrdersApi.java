package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.Orders;

public class OrdersApi extends RobinhoodApi {

    public OrdersApi() {
        this(APIEndpoints.ORDERS.toString());
    }

    public OrdersApi(String url) {
        super("Orders");

        setRequestType(RequestType.GET);
        setAuthToken();

        setHeaderParams("Accept","application/json");

        setResponseType(Orders.class);
        setUrlBase(url);
    }
}
