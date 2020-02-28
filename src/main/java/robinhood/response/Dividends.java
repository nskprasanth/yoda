package robinhood.response;

import java.io.Serializable;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Dividends implements Serializable {

    private List<Dividend> results;

    private static final String DIVIDEND_PAID_STATE = "paid";

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

        // paid or void dividend
        private String state;

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
            return isPaid() ? amount : 0;
        }

        public boolean isPaid() { return state.equalsIgnoreCase(DIVIDEND_PAID_STATE); }

        public Date getRecord_date() throws ParseException {
            return new SimpleDateFormat("yyyy-mm-dd").parse(record_date);
        }

        public Date getPayable_date() throws ParseException {
            return new SimpleDateFormat("yyyy-mm-dd").parse(payable_date);
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
         * @return instrumentId corresponding to this dividend
         */
        public String getInstrumentId() {
            String[] tokens = instrument.toString().split("/");
            return tokens[tokens.length-1];
        }
    }
}
