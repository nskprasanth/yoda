package robinhood.response;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class Dividends implements Serializable {

    private List<Dividend> results;

    public List<Dividend> getDividends() {
        return results;
    }

    public class Dividend {

        // dividend per share
        private float rate;

        // number of shares
        private float position;

        // total dividends
        private float amount;

        private String record_date;
        private String payable_date;

        private URL instrument;
        private URL account;
        private URL url; // ???

        private String id;

        public float getRate() {
            return rate;
        }

        public float getPosition() {
            return position;
        }

        public float getAmount() {
            return amount;
        }

        public String getRecord_date() {
            return record_date;
        }

        public String getPayable_date() {
            return payable_date;
        }

        public URL getInstrument() {
            return instrument;
        }

        public URL getAccount() {
            return account;
        }

        public URL getUrl() {
            return url;
        }

        public String getId() {
            return id;
        }

        /**
         * Example instrument URL with instrument id ed793902-693a-40b3-800e-cc8b3f7ea7b1
         * "https://api.robinhood.com/instruments/ed793902-693a-40b3-800e-cc8b3f7ea7b1/"
         *
         * @return
         */
        public String getInstrumentId() {
            String[] tokens = instrument.toString().split("/");
            return tokens[tokens.length-1];
        }
    }
}
