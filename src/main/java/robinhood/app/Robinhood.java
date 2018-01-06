package robinhood.app;

import common.Api;
import common.RequestManager;
import robinhood.apis.*;
import robinhood.config.ConfigManager;
import robinhood.response.*;
import robinhood.response.Portfolios.Portfolio;

import java.net.URL;
import java.util.List;

public class Robinhood {

    private static RequestManager requestManager = null;
    private static ConfigManager configManager = null;

    public Robinhood() {
        requestManager = RequestManager.getInstance();
        configManager = ConfigManager.getInstance();
    }

    // TODO: Add exceptions
    public boolean login(String user, String pwd) {
        Api loginApi = new LoginApi(user, pwd);
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

}
