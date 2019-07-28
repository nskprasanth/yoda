package robinhood.app;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import common.DateUtils;

import java.io.FileReader;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class PortfolioReturnsReport {

    private static Date findPrevDate(Map<Date, Double> history, Date date) {

        if (history == null || date == null) {
            return null;
        }

        do {
            date = DateUtils.minusDays(date, 1);
        } while(!history.containsKey(date));

        return date;
    }

    public static void main(String[] args) throws Exception {
        Robinhood trader = new Robinhood();

        Config config = ConfigFactory.parseReader(new FileReader("src/main/resources/robinhood_config.json"));
        String user = config.getString("user");
        String pwd = config.getString("password");
        String deviceToken = config.getString("device-token");
        String account = config.getString("account");

        // Login
        trader.login(user, pwd, deviceToken);

        // Transfer history (deposits & withdrawals)
        Map<Date, Double> transfers = trader.getTransfersHistory();

        // Portfolio history (daily portfolio values since inception)
        Map<Date, Double> portfolioHistory = trader.getPortfolioHistoricals(account);

        Iterator<Map.Entry<Date, Double> > iter = transfers.entrySet().iterator();
        if (!iter.hasNext()) {
            System.out.println("No transfers found");
            return;
        }

        Map.Entry<Date, Double> inception = iter.next();
        double transferAmount = inception.getValue();

        double portfolioDuration = 0;
        double twrMultiple = 1, annualizedReturns;

        double beginValue = 0, endValue;
        int yearCounter = 1;

        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(1);

        while (iter.hasNext()) {
            Map.Entry<Date, Double> transferEntry = iter.next();

            Date endDate = findPrevDate(portfolioHistory, transferEntry.getKey());
            endValue = portfolioHistory.get(endDate);
            twrMultiple *= endValue/(beginValue + transferAmount);

            portfolioDuration = DateUtils.getDiffYears(endDate, inception.getKey());
            if (portfolioDuration > yearCounter) {
                System.out.print(String.format("Return after %d years: ",yearCounter) +
                        percentFormat.format(twrMultiple - 1));
                annualizedReturns = Math.pow(twrMultiple, 1.0/portfolioDuration) - 1;
                System.out.println(" | Annualized return: " + percentFormat.format(annualizedReturns));
                ++yearCounter;
            }

            beginValue = endValue;
            transferAmount = transferEntry.getValue();
        }
        annualizedReturns = Math.pow(twrMultiple, 1.0/portfolioDuration) - 1;
        System.out.println("Total portfolio returns: " + percentFormat.format(twrMultiple - 1)
                + " | Annualized returns: " + percentFormat.format(annualizedReturns));
    }
}
