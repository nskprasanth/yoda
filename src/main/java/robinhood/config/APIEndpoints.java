package robinhood.config;

public enum APIEndpoints {

    LOGIN("https://api.robinhood.com/oauth2/token/"),
    LOGOUT("https://api.robinhood.com/oauth2/revoke_token/"),
    INVESTMENT_PROFILE("https://api.robinhood.com/user/investment_profile/"),
    ACCOUNTS("https://api.robinhood.com/accounts/"),
    ACH_IAV_AUTH("https://api.robinhood.com/ach/iav/auth/"),
    ACH_RELATIONSHIPS("https://api.robinhood.com/ach/relationships/"),
    ACH_TRANSFERS("https://api.robinhood.com/ach/transfers/"),
    APPLICATIONS("https://api.robinhood.com/applications/"),
    DIVIDENDS("https://api.robinhood.com/dividends/"),
    DOCUMENTS("https://api.robinhood.com/documents/"),
    INSTRUMENTS("https://api.robinhood.com/instruments/"),
    MARGIN_UPGRADES("https://api.robinhood.com/margin/upgrades/"),
    MARKETS("https://api.robinhood.com/markets/"),
    NOTIFICATIONS("https://api.robinhood.com/notifications/"),
    ORDERS("https://api.robinhood.com/orders/"),
    PASSWORD_RESET("https://api.robinhood.com/password_reset/request/"),
    PORTFOLIOS("https://api.robinhood.com/portfolios/"),
    POSITIONS("https://api.robinhood.com/positions/"),
    QUOTES("https://api.robinhood.com/quotes/"),
    HISTORICALS("https://api.robinhood.com/quotes/historicals/"),
    DOCUMENT_REQUESTS("https://api.robinhood.com/upload/document_requests/"),
    USER("https://api.robinhood.com/user/"),
    WATCHLISTS("https://api.robinhood.com/watchlists/"),
    NEWS("https://api.robinhood.com/midlands/news/"),
    FUNDAMENTALS("https://api.robinhood.com/fundamentals/");

    private String uri;

    APIEndpoints(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return uri;
    }
}
