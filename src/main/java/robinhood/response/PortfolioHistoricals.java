package robinhood.response;

import common.DateUtils;

import java.io.Serializable;
import java.util.*;

public class PortfolioHistoricals implements Serializable {

    private List<Entry> equity_historicals;

    private boolean dataMapped = false;

    private Map<Date, Double> history = new TreeMap<>();

    public Map<Date, Double> getPortflioHistoricals() {

        if (!dataMapped) {
            for (Entry cur : equity_historicals) {
                if (cur.getOpen() > 0) {
                    history.put(cur.getBeginDate(), cur.getOpen());
                }
            }
            dataMapped = true;
        }
        return history;
    }

    private static class Entry implements Serializable {

        Date begins_at;
        double open_equity;
        double close_equity;

        Date getBeginDate() {
            return DateUtils.removeTime(begins_at);
        }

        double getOpen() {
            return open_equity;
        }

        public double getClose() {
            return close_equity;
        }
    }
}
