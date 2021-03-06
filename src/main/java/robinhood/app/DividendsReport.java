package robinhood.app;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import robinhood.response.Dividends;
import robinhood.response.Instruments;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.*;

public class DividendsReport {

    private static List<Map.Entry<String, Float>> getSortedEntryList(Map<String, Float> dividendsBySymbol) {
        List<Map.Entry<String, Float> > entries = new LinkedList<>(dividendsBySymbol.entrySet());
        Collections.sort(entries, new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return o2.getValue().compareTo(o1.getValue()); // descending order
            }
        });
        return entries;
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Robinhood trader = new Robinhood();
        Config config = ConfigFactory.parseReader(new FileReader(
                "src/main/resources/robinhood_config.json"));
        String user = config.getString("user");
        String pwd = config.getString("password");
        String deviceToken = config.getString("device-token");

        // Login
        trader.login(user, pwd, deviceToken);

        Map<String, String> instrumentMap = new HashMap<>();
        // Dividends tracked by ticker symbol and by year
        Map<Integer, Map<String, Float>> dividendsByYearSymbol = new TreeMap<>();
        Dividends dividends = trader.getAllDividends();

        for (Dividends.Dividend dividend : dividends.getDividends()) {

            String instrumentId = dividend.getInstrumentId();
            String symbol;
            if (instrumentMap.containsKey(instrumentId)) {
                symbol = instrumentMap.get(instrumentId);
            } else {
                // Fetch the ticker symbol only if it's not found already
                Instruments instr = trader.getInstrumentDetails(dividend.getInstrument());
                instrumentMap.put(instr.getId(), instr.getSymbol());
                symbol = instr.getSymbol();
            }

            Date dividendDate = dividend.getPayable_date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dividendDate);
            int year = calendar.get(Calendar.YEAR);

            Map<String, Float> dividendsBySymbol;
            if (dividendsByYearSymbol.containsKey(year)) {
                dividendsBySymbol = dividendsByYearSymbol.get(year);
            } else {
                dividendsBySymbol = new HashMap<>();
                dividendsByYearSymbol.put(year, dividendsBySymbol);
            }

            float currentDividend = dividendsBySymbol.containsKey(symbol) ? dividendsBySymbol.get(symbol) : 0;
            float newDividend = currentDividend + dividend.getAmount();
            dividendsBySymbol.put(symbol, newDividend);
        }

        // Report the Dividend Metrics
        dividendsByYearSymbol.forEach((year, dividendsBySymbol) -> {
            System.out.println("Dividends for " + year + " are: ");
            Float totalDividends = 0f;
           List<Map.Entry<String, Float>> sortedEntries = getSortedEntryList(dividendsBySymbol);
           for (Map.Entry<String, Float> entry: sortedEntries) {
                System.out.println(String.format("%s : %.2f", entry.getKey(), entry.getValue()));
                totalDividends += entry.getValue();
            }
            System.out.println(String.format("Total Dividends for %d: %.2f" , year, totalDividends));
            System.out.println("-----------------------------------------");
        });
    }

}
