package robinhood.response;

import java.io.Serializable;
import java.net.URL;

public class Quote implements Serializable {

    private float last_trade_price;
    private float previous_close;
    private float last_extended_hours_trade_price;
    private float adjusted_previous_close;
    private String previous_close_date;

    private float ask_price;
    private int ask_size;
    private float bid_price;
    private int bid_size;

    private String symbol;
    private URL instrument;

    public float getLastPrice() {
        return last_trade_price;
    }

    public float getPreviousClose() {
        return previous_close;
    }

    public float getLastExtendedHoursPrice() {
        return last_extended_hours_trade_price;
    }

    public float getAdjustedPreviousClose() {
        return adjusted_previous_close;
    }

    public String getPreviousCloseDate() {
        return previous_close_date;
    }

    public float getAskPrice() {
        return ask_price;
    }

    public int getAskSize() {
        return ask_size;
    }

    public float getBidPrice() {
        return bid_price;
    }

    public int getBidSize() {
        return bid_size;
    }

    public String getSymbol() {
        return symbol;
    }

    public URL getInstrumentURL() {
        return instrument;
    }

    public boolean hasTraded() {
        return has_traded;
    }

    private boolean has_traded;

}
