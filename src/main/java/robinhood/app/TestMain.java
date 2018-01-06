package robinhood.app;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import instruments.Instrument;
import robinhood.response.Dividends;
import robinhood.response.Instruments;
import robinhood.response.Positions;
import robinhood.response.Quote;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) throws FileNotFoundException {
        Robinhood trader = new Robinhood();

        Config config = ConfigFactory.parseReader(new FileReader("src/main/resources/robinhood_config.json"));
        String user = config.getString("user");
        String pwd = config.getString("password");

        // Login
        trader.login(user, pwd);

        Map<String, InstrumentReturns> instrumentReturnsMap = new HashMap<>();
        Map<String, String> instrumentMap = new HashMap<>();

        List<Positions.Position> positions = trader.getPositions();
        // Positions --> Instruments --> Quotes
        for (Positions.Position p : positions) {

            Instruments instr = trader.getInstrumentDetails(p.getInstrumentURL());
            instrumentMap.put(instr.getId(), instr.getSymbol());

            if (p.getQuantity() == 0) {
                // not holding any positions now, skip
                continue;
            }

            Quote q = trader.getQuote(instr.getQuoteURL());

            InstrumentReturns returns = new InstrumentReturns(instr.getSymbol());
            returns.setAvgBuyPrice(p.getAverage_buy_price());
            returns.setCurrentPrice(q.getLastPrice());
            returns.setQuantity(p.getQuantity());
            instrumentReturnsMap.put(instr.getSymbol(),returns);

        };

        // Dividends
        Map<String, Float> dividendsBySymbol = new HashMap<>();
        Dividends dividends = trader.getAllDividends();

        for (Dividends.Dividend dividend : dividends.getDividends()) {
            String symbol = instrumentMap.get(dividend.getInstrumentId());
            float currentDividend = dividendsBySymbol.containsKey(symbol) ? dividendsBySymbol.get(symbol) : 0;
            float newDividend = currentDividend + dividend.getAmount();
            dividendsBySymbol.put(symbol, newDividend);

            if(instrumentReturnsMap.containsKey(symbol)) {
                instrumentReturnsMap.get(symbol).setDividendsGained(newDividend);
            }
        }

        instrumentReturnsMap.forEach((k,v)-> {
            System.out.println(String.format("%s, %.2f, %.2f, %.2f", k, 100*v.getCapitalGains(),
                    100*v.getDividendYield(), 100*v.getTotalGains()));
        });

    }
}
