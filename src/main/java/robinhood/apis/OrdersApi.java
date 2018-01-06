package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.Orders;

public class OrdersApi extends RobinhoodApi {

    public OrdersApi() {
        super("Orders");

        setRequestType(RequestType.GET);
        setAuthToken();

        setHeaderParams("Accept","application/json");

        setResponseType(Orders.class);
        setUrlBase(APIEndpoints.ORDERS.toString());

    }
}
