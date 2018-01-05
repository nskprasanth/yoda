package robinhood.response;

import java.util.List;

public class Portfolios {

    List<Portfolio> results;

    public Portfolio getPortfolio() {
        return (results.isEmpty() ? null : results.get(0));
    }

    public class Portfolio {
        float market_value;
        float extended_hours_market_value;
        float equity;
        float extended_hours_equity;
        float equity_previous_close;
        float adjusted_equity_previous_close;
        String start_date;

        public float getMarket_value() {
            return market_value;
        }

        public float getExtended_hours_market_value() {
            return extended_hours_market_value;
        }

        public float getEquity() {
            return equity;
        }

        public float getExtended_hours_equity() {
            return extended_hours_equity;
        }

        public float getEquity_previous_close() {
            return equity_previous_close;
        }

        public float getAdjusted_equity_previous_close() {
            return adjusted_equity_previous_close;
        }

        public String getStart_date() {
            return start_date;
        }
    }
}
