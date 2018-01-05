package robinhood.response;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class Positions implements Serializable {

    private List<Position> results;

    public List<Position> getPositions() {
        return results;
    }

    public class Position {

        private float quantity;
        private float average_buy_price;

        private float shares_held_for_buys;
        private float shares_held_for_sells;

        // URL to access instruments
        private URL instrument;

        // URL to access account
        private URL account;

        public float getQuantity() {
            return quantity;
        }

        public float getAverage_buy_price() {
            return average_buy_price;
        }

        public float getShares_held_for_buys() {
            return shares_held_for_buys;
        }

        public float getShares_held_for_sells() {
            return shares_held_for_sells;
        }

        public URL getAccountURL() {
            return account;
        }

        public URL getInstrumentURL() {
            return instrument;
        }

        public float getInvestedCapital() {
            return quantity*average_buy_price;
        }
    }
}
