package robinhood.app;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import robinhood.config.OrderState;
import robinhood.response.Orders;

import java.io.FileReader;
import java.text.ParseException;
import java.util.List;

public class TestMain {

    public static void main(String[] args) throws Exception {
        Robinhood trader = new Robinhood();

        Config config = ConfigFactory.parseReader(new FileReader("src/main/resources/robinhood_config.json"));
        String user = config.getString("user");
        String pwd = config.getString("password");
        String deviceToken = config.getString("device-token");

        // Login
        trader.login(user, pwd, deviceToken);
    }

}
