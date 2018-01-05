package robinhood.response;

import java.io.Serializable;
import java.net.URL;

public class Fundamentals implements Serializable {

    private String description;
    private URL instrument;

    private float volume;
    private float average_volume;

    private float open;
    private float high;
    private float low;

    private float high_52_weeks;
    private float low_52_weeks;

    private float market_cap;
    private float pe_ratio;
    private float dividend_yield;

    public String getDescription() {
        return description;
    }

    public URL getInstrument() {
        return instrument;
    }

    public float getVolume() {
        return volume;
    }

    public float getAverage_volume() {
        return average_volume;
    }

    public float getOpen() {
        return open;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getHigh_52_weeks() {
        return high_52_weeks;
    }

    public float getLow_52_weeks() {
        return low_52_weeks;
    }

    public float getMarket_cap() {
        return market_cap;
    }

    public float getPe_ratio() {
        return pe_ratio;
    }

    public float getDividend_yield() {
        return dividend_yield;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstrument(URL instrument) {
        this.instrument = instrument;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setAverage_volume(float average_volume) {
        this.average_volume = average_volume;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public void setHigh_52_weeks(float high_52_weeks) {
        this.high_52_weeks = high_52_weeks;
    }

    public void setLow_52_weeks(float low_52_weeks) {
        this.low_52_weeks = low_52_weeks;
    }

    public void setMarket_cap(float market_cap) {
        this.market_cap = market_cap;
    }

    public void setPe_ratio(float pe_ratio) {
        this.pe_ratio = pe_ratio;
    }

    public void setDividend_yield(float dividend_yield) {
        this.dividend_yield = dividend_yield;
    }
}
