package robinhood.app;

import common.Api;
import common.RequestManager;
import robinhood.apis.*;
import robinhood.config.ConfigManager;
import robinhood.response.*;
import robinhood.response.Portfolios.Portfolio;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Robinhood {

    private static RequestManager requestManager = null;
    private static ConfigManager configManager = null;

    public Robinhood() {
        requestManager = RequestManager.getInstance();
        configManager = ConfigManager.getInstance();
    }

    // TODO: Add exceptions
    public boolean login(String user, String pwd, String deviceToken) {
        LoginApi loginApi = new LoginApi(user, pwd, deviceToken);
        requestManager.callAPI(loginApi);

        Scanner scanner = new Scanner(System.in);
        String mfaCode = scanner.next();
        scanner.close();

        loginApi.setMfaCode(mfaCode);
        LoginResponse token = requestManager.callAPI(loginApi);
        if (token.getToken() == null) {
            return false;
        }
        configManager.setAuthToken(token.getToken());
        return true;
    }

    public Fundamentals getFundamentals(String ticker) {
        Api fundamentalsApi = new FundamentalsApi(ticker);
        return requestManager.callAPI(fundamentalsApi);
    }

    public Portfolio getPortfolio() {
        Api portfoliosApi = new PortfoliosApi();
        return requestManager.callAPI(portfoliosApi);
    }

    public List<Positions.Position> getPositions() {
        Api positionsApi = new PositionsApi();
        Positions positions = requestManager.callAPI(positionsApi);
        return positions.getPositions();
    }

    public Instruments getInstrumentDetails(String id) {
        Api instrumentsApi = new InstrumentsApi(id);
        return requestManager.callAPI(instrumentsApi);
    }

    public Instruments getInstrumentDetails(URL url) {
        Api instrumentsApi = new InstrumentsApi(url);
        return requestManager.callAPI(instrumentsApi);
    }

    public Quote getQuote(String symbol) {
        Api quotesApi = new QuotesApi(symbol);
        return requestManager.callAPI(quotesApi);
    }

    public Quote getQuote(URL url) {
        Api quotesApi = new QuotesApi(url);
        return requestManager.callAPI(quotesApi);
    }

    public Dividends getAllDividends() {
        Api dividendsApi = new DividendsApi();
        return requestManager.callAPI(dividendsApi);
    }

    public List<Orders.Order> getOrdersHistory() {
        Api ordersApi = new OrdersApi();
        Orders orders = requestManager.callAPI(ordersApi);
        return orders.getOrders();
    }

    public Map<Date, Double> getTransfersHistory() {
        Api transfersApi = new TransfersApi();
        Transfers transfers = requestManager.callAPI(transfersApi);
        return transfers.getTransfers();
    }

    public Map<Date, Double> getPortfolioHistoricals(String account) {
        Api accountsApi = new PortfolioHistoricalsApi(account);
        PortfolioHistoricals historicals = requestManager.callAPI(accountsApi);
        return historicals.getPortflioHistoricals();
    }
}
