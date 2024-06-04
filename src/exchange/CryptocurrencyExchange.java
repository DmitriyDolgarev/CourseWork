package exchange;

import java.util.HashMap;

public abstract class CryptocurrencyExchange {
    public abstract void putApi(HashMap<String, String> urls, String pairName);
    public abstract double getPrice(String json);
}
