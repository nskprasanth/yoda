package instruments;

public interface Instrument {

    String getSymbol();

    String getCountry();

    String getName();

    double getPrice();

    String getId();

    Fundamentals getFundamentals();

    Quote getQuote();
}
