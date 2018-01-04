package robinhood.app;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestMain {
    public static void main(String[] args) throws FileNotFoundException {
        Robinhood trader = new Robinhood();

        Config config = ConfigFactory.parseReader(new FileReader("src/main/resources/robinhood_config.json"));
        String user = config.getString("user");
        String pwd = config.getString("password");

        boolean login = trader.login(user, pwd);
        System.out.println(login ? "SUCCESS" : "FAIL");
    }
}
