package robinhood.app;

public class InstrumentReturns implements Comparable<InstrumentReturns> {

    private String symbol;

    private float avgBuyPrice;
    private float currentPrice;
    private float dividendsGained;
    private float quantity;

    public InstrumentReturns(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public float getCapitalGains() {
        if (avgBuyPrice == 0 || currentPrice == 0) {
            return 0;
        }
        return (currentPrice/avgBuyPrice)-1;
    }

    public float getDividendYield() {
        if (avgBuyPrice == 0 || quantity == 0) {
            return 0;
        }
        return dividendsGained/(quantity * avgBuyPrice);
    }


    public float getTotalGains() {
        return getCapitalGains() + getDividendYield();
    }

    public float getQuantity() {
        return quantity;
    }

    public void setAvgBuyPrice(float avgBuyPrice) {
        this.avgBuyPrice = avgBuyPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setDividendsGained(float dividendsGained) {
        this.dividendsGained = dividendsGained;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(InstrumentReturns o) {
        return Float.compare(o.getTotalGains(), this.getTotalGains());
    }
}
