package robinhood.app;

public class InstrumentReturns implements Comparable<InstrumentReturns> {

    private String symbol;

    private float avgBuyPrice;
    private float currentPrice;
    private float dividendsGained;
    private float quantity;

    InstrumentReturns(String symbol) {
        this.symbol = symbol;
    }

    String getSymbol() {
        return symbol;
    }

    float getCapitalGains() {
        if (avgBuyPrice == 0 || currentPrice == 0) {
            return 0;
        }
        return (currentPrice/avgBuyPrice)-1;
    }

    float getDividendYield() {
        if (avgBuyPrice == 0 || quantity == 0) {
            return 0;
        }
        return dividendsGained/(quantity * avgBuyPrice);
    }


    float getTotalGains() {
        return getCapitalGains() + getDividendYield();
    }

    public float getQuantity() {
        return quantity;
    }

    void setAvgBuyPrice(float avgBuyPrice) {
        this.avgBuyPrice = avgBuyPrice;
    }

    void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    void setDividendsGained(float dividendsGained) {
        this.dividendsGained = dividendsGained;
    }

    void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(InstrumentReturns o) {
        return Float.compare(o.getTotalGains(), this.getTotalGains());
    }
}
