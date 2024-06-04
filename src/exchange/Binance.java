package exchange;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Objects;

public class Binance extends CryptocurrencyExchange {
    public void putApi(HashMap<String, String> urls, String pairName)
    {
        switch (pairName)
        {
            case "BTC_USDT":
                urls.put("Binance", "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT");
                break;
            case "ETH_USDT":
                urls.put("Binance", "https://api.binance.com/api/v3/ticker/price?symbol=ETHUSDT");
                break;
            case "DOGE_USDT":
                urls.put("Binance", "https://api.binance.com/api/v3/ticker/price?symbol=DOGEUSDT");
                break;
            case "SOL_USDT":
                urls.put("Binance", "https://api.binance.com/api/v3/ticker/price?symbol=SOLUSDT");
                break;
            case "PEPE_USDT":
                urls.put("Binance", "https://api.binance.com/api/v3/ticker/price?symbol=PEPEUSDT");
                break;
            case "MANTA_USDT":
                urls.put("Binance", "https://api.binance.com/api/v3/ticker/price?symbol=MANTAUSDT");
                break;
            case "XRP_USDT":
                urls.put("Binance", "https://api.binance.com/api/v3/ticker/price?symbol=XRPUSDT");
                break;
            case "ADA_USDT":
                urls.put("Binance", "https://api.binance.com/api/v3/ticker/price?symbol=ADAUSDT");
                break;
            case "TRB_USDT":
                urls.put("Binance", "https://api.binance.com/api/v3/ticker/price?symbol=TRBUSDT");
                break;
            default:
                urls.put("Binance", "default");
                break;
        }
    }
    public double getPrice(String json)
    {
        HashMap type = new HashMap<String, Objects>();
        Gson gson = new Gson();

        type = gson.fromJson(json, type.getClass());

        Object price = type.get("price");
        if (price == null) {
            return 0;
        }
        return Double.parseDouble(price.toString());
    }
}
