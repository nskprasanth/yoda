package robinhood.app;

import common.DateUtils;

import java.util.*;

public class InstrumentOrders {

    private static final String ORDER_SIDE_BUY = "buy";
    private static final String ORDER_SIDE_SELL = "sell";

    static class InstrumentOrder {
        private float quantity;
        private Date txnDate;
        private String side;

        public InstrumentOrder(float qty, Date date, String side) {
            quantity = qty;
            txnDate = date;
            this.side = side;
        }

        public float getQuantity() {
            return quantity;
        }

        public Date getTxnDate() {
            return txnDate;
        }

        public String getSide() {
            return side;
        }

        public void resetQuantity(float qty) {
            quantity = qty;
        }
    }

    private LinkedList<InstrumentOrder> instrumentOrders = new LinkedList<>();

    public void appendOrder(InstrumentOrder io) {
        instrumentOrders.add(io);
    }

    public int size() {
        return instrumentOrders.size();
    }

    public InstrumentOrder getFirst() {
        return instrumentOrders.getFirst();
    }

    public float getAverageDuration() {
        LinkedList<InstrumentOrder> adjustedOrders = new LinkedList<>();

        Iterator<InstrumentOrder> iter = instrumentOrders.descendingIterator();
        while (iter.hasNext()) {
            InstrumentOrder order = iter.next();

            if (order.getSide().equals(ORDER_SIDE_BUY)) {
                adjustedOrders.add(order);
            } else if (order.getSide().equals(ORDER_SIDE_SELL)) {
                float sellQty = order.getQuantity();
                while (sellQty > 0 && !adjustedOrders.isEmpty()) {
                    InstrumentOrder first = adjustedOrders.getFirst();
                    float firstQty = first.getQuantity();
                    if (firstQty <= sellQty) {
                        adjustedOrders.removeFirst();
                        sellQty -= firstQty;
                    } else {
                        first.resetQuantity(firstQty - sellQty);
                        sellQty = 0;
                    }
                }
            }
        }
        float weightedDuration = 0;
        int quantity = 0;
        for (InstrumentOrder order : adjustedOrders) {
            weightedDuration += order.getQuantity() *
                    DateUtils.getDiffYears(Calendar.getInstance().getTime(), order.getTxnDate());
            quantity += order.getQuantity();
        }
        return (quantity > 0) ? (weightedDuration / quantity) : 0;
    }
}
