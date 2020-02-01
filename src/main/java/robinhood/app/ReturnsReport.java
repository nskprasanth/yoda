package robinhood.app;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import robinhood.response.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

import static robinhood.app.InstrumentOrders.*;

public class ReturnsReport {

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Robinhood trader = new Robinhood();

        Config config = ConfigFactory.parseReader(new FileReader(
                "src/main/resources/robinhood_config.json"));
        String user = config.getString("user");
        String pwd = config.getString("password");
        String deviceToken = config.getString("device-token");

        // Login
        trader.login(user, pwd, deviceToken);

        Map<String, InstrumentReturns> instrumentReturnsMap = new HashMap<>();
        Map<String, String> instrumentMap = new HashMap<>();
        Map<URL, String> instrumentURLMap = new HashMap<>();

        List<Positions.Position> positions = trader.getPositions();
        // Positions --> Instruments --> Quotes
        for (Positions.Position p : positions) {

            Instruments instr = trader.getInstrumentDetails(p.getInstrumentURL());
            instrumentMap.put(instr.getId(), instr.getSymbol());
            instrumentURLMap.put(p.getInstrumentURL(), instr.getSymbol());

            if (p.getQuantity() == 0) {
                // not holding any positions now, skip
                continue;
            }

            Quote q = trader.getQuote(instr.getQuoteURL());

            InstrumentReturns instrumentReturns = new InstrumentReturns(instr.getSymbol());
            instrumentReturns.setAvgBuyPrice(p.getAverage_buy_price());
            instrumentReturns.setCurrentPrice(q.getLastPrice());
            instrumentReturns.setQuantity(p.getQuantity());
            instrumentReturnsMap.put(instr.getSymbol(), instrumentReturns);
        }

        // Dividends
        Map<String, Float> dividendsBySymbol = new HashMap<>();
        Dividends dividends = trader.getAllDividends();

        for (Dividends.Dividend dividend : dividends.getDividends()) {
            String symbol = instrumentMap.get(dividend.getInstrumentId());
            float newDividend = dividendsBySymbol.getOrDefault(symbol, 0F) + dividend.getAmount();
            dividendsBySymbol.put(symbol, newDividend);
            if (instrumentReturnsMap.containsKey(symbol)) {
                instrumentReturnsMap.get(symbol).setDividendsGained(newDividend);
            }
        }

        // Orders
        Map<String, InstrumentOrders> symbolToOrdersMap = new HashMap<>();
        for (Orders.Order order : trader.getOrdersHistory()) {
            if (!instrumentURLMap.containsKey(order.getInstrumentURL())) {
                continue; // not holding any positions, skip
            }
            if (order.getState().equals("filled")) { // only look at filled orders
                String symbol = instrumentURLMap.get(order.getInstrumentURL());
                if (!symbolToOrdersMap.containsKey(symbol)) {
                    symbolToOrdersMap.put(symbol, new InstrumentOrders());
                }
                symbolToOrdersMap.get(symbol).appendOrder(
                        new InstrumentOrder(order.getQuantity(), order.getTimestamp(), order.getSide()));
            }
        }

        symbolToOrdersMap.forEach((k, v) -> {
            if (instrumentReturnsMap.containsKey(k)) {
                // set the duration of a position to wtd average duration of holdings
                instrumentReturnsMap.get(k).setDuration(v.getAverageDuration());
            }
        });

        Set<InstrumentReturns> returns = new TreeSet<>(instrumentReturnsMap.values());
        returns.forEach(v -> {
            System.out.println(
                    String.format("%s, %.2f, %.2f, %.2f, %.2f",
                            v.getSymbol(), 100 * v.getCapitalGains(),
                            100 * v.getDividendYield(), 100 * v.getTotalGains(),
                            100 * v.getAnnualizedGains()));
        });
    }
}
