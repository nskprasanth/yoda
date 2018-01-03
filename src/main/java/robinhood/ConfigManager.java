package robinhood;

public class ConfigManager {

    private String authToken = null;
    private String accountNumber = null;

    private static ConfigManager manager = null;

    public static ConfigManager getInstance() {
        if (manager == null) {
            manager = new ConfigManager();
        }
        return manager;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
