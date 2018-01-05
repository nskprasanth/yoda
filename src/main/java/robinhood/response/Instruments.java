package robinhood.response;

import java.io.Serializable;
import java.net.URL;

public class Instruments implements Serializable {

    private URL quote;
    private URL fundamentals;
    private URL splits;
    private URL market;

    private String symbol;
    private String type;
    private String name;
    private String simple_name;
    private String country;

    private boolean tradeable;

    public URL getQuoteURL() {
        return quote;
    }

    public URL getFundamentalsURL() {
        return fundamentals;
    }

    public URL getSplitsURL() {
        return splits;
    }

    public URL getMarketURL() {
        return market;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getSimpleName() {
        return simple_name;
    }

    public String getCountry() {
        return country;
    }

    public boolean isTradeable() {
        return tradeable;
    }

}
