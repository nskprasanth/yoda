package robinhood.response;

import common.DateUtils;

import java.io.Serializable;
import java.util.*;

public class Transfers implements Serializable {

    // For deposits greater than default deposit, default deposit is submitted
    // on the initiated day and the remaining on the landing date
    private static final double DEFAULT_DEPOSIT = 1000;

    private List<Transfers.Transfer> results;

    private boolean dataMapped = false;

    private Map<Date, Double> transfers = new TreeMap<>();

    public Map<Date, Double> getTransfers() {

        if (!dataMapped) {
            results.forEach((v) -> {
                if(v.isComplete()) {
                    double curAmount = v.getAmount();
                    if (!v.isDeposit()) {
                        transfers.put(v.getTransferCreatedDate(), v.getAmount());
                    } else if(curAmount > DEFAULT_DEPOSIT) {
                        transfers.put(v.getTransferCreatedDate(), DEFAULT_DEPOSIT);
                        transfers.put(v.getTransferLandingDate(), v.getAmount() - DEFAULT_DEPOSIT);
                    } else {
                        transfers.put(v.getTransferCreatedDate(), v.getAmount());
                    }
                }
            });

            dataMapped = true;
        }
        return transfers;
    }

    private static class Transfer implements Serializable {

        private double amount;
        private Date created_at;
        private Date expected_landing_date;
        private String state;
        private String direction;

        double getAmount() {
            return isDeposit() ? amount : -amount;
        }

        Date getTransferCreatedDate() {
            return DateUtils.removeTime(created_at);
        }

        Date getTransferLandingDate() {
            return DateUtils.removeTime(expected_landing_date);
        }

        boolean isComplete() {
            return state.equalsIgnoreCase("completed");
        }

        boolean isDeposit() {
            return direction.equalsIgnoreCase("deposit");
        }

    }
}
