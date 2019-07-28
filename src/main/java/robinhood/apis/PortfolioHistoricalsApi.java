package robinhood.apis;

import common.RequestType;
import robinhood.config.APIEndpoints;
import robinhood.response.PortfolioHistoricals;

public class PortfolioHistoricalsApi extends RobinhoodApi {

    public enum Interval {
        DAILY  ("day"),
        WEEKLY ("week"),
        MONTHLY ("month");

        Interval(String interval) {
            this.interval = interval;
        }

        public String toString() {
            return interval;
        }

        private String interval;
    }

    public enum Span {
        // TODO: Add longer spans once available
        YEAR ("year"),
        FIVE_YEAR ("5year");

        Span(String span) {
            this.span = span;
        }

        public String toString() { return span; }

        private String span;
    }

    public PortfolioHistoricalsApi(String account) {
        this(account, Interval.DAILY);
    }

    public PortfolioHistoricalsApi(String account, Interval interval) {
        super("portfolio_historicals");

        setRequestType(RequestType.GET);
        setAuthToken();

        setHeaderParams("Accept","application/json");

        setResponseType(PortfolioHistoricals.class);
        setUrlBase(APIEndpoints.PORTFOLIO_HISTORICALS.toString());

        setAccountNumber(account);
        setInterval(interval);
        setSpan(Span.FIVE_YEAR);
    }

    public void setAccountNumber(String account) {
        setRequestParams("account_number", account);
    }

    public void setInterval(Interval interval) {
        setRequestParams("interval", interval.toString());
    }

    public void setSpan(Span span) {
        setRequestParams("span", span.toString());
    }

}
