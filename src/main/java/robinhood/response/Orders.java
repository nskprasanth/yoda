package robinhood.response;

import java.io.Serializable;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {

    private List<Order> results;

    public List<Order> getOrders() {
        return results;
    }

    public class Order implements Serializable {

        private String id;
        private URL instrument;
        private float cumulative_quantity;
        private float average_price;
        private String side;
        private String state;
        private String type;
        private String trigger;
        private String last_transaction_at;

        public String getId() {
            return id;
        }

        public URL getInstrument() {
            return instrument;
        }

        public float getQuantity() {
            return cumulative_quantity;
        }

        public float getPrice() {
            return average_price;
        }

        public String getSide() {
            return side;
        }

        public String getState() {
            return state;
        }

        public String getType() {
            return type;
        }

        public String getTrigger() {
            return trigger;
        }

        public Date getTimestamp() throws ParseException {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
                    .parse(this.last_transaction_at);
        }

    }

}
