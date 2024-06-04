package exchange;

import com.google.gson.Gson;

import java.util.HashMap;

public class Bybit extends CryptocurrencyExchange {
    public void putApi(HashMap<String, String> urls, String pairName)
    {
        switch (pairName)
        {
            case "BTC_USDT":
                urls.put("Bybit", "https://api.bybit.com/v2/public/tickers?symbol=BTCUSDT");
                break;
            case "ETH_USDT":
                urls.put("Bybit", "https://api.bybit.com/v2/public/tickers?symbol=ETHUSDT");
                break;
            case "DOGE_USDT":
                urls.put("Bybit", "https://api.bybit.com/v2/public/tickers?symbol=DOGEUSDT");
                break;
            case "SOL_USDT":
                urls.put("Bybit", "https://api.bybit.com/v2/public/tickers?symbol=SOLUSDT");
                break;
            case "MANTA_USDT":
                urls.put("Bybit", "https://api.bybit.com/v2/public/tickers?symbol=MANTAUSDT");
                break;
            case "XRP_USDT":
                urls.put("Bybit", "https://api.bybit.com/v2/public/tickers?symbol=XRPUSDT");
                break;
            case "ADA_USDT":
                urls.put("Bybit", "https://api.bybit.com/v2/public/tickers?symbol=ADAUSDT");
                break;
            case "TRB_USDT":
                urls.put("Bybit", "https://api.bybit.com/v2/public/tickers?symbol=TRBUSDT");
                break;
            default:
                urls.put("Bybit", "default");
                break;
        }
    }
    public double getPrice(String json)
    {
        String text = json.substring(json.indexOf('[')+1, json.indexOf(']'));

        HashMap result = new HashMap<String, String>();
        Gson gson = new Gson();

        result = gson.fromJson(text, result.getClass());

        Object price = result.get("index_price");

        if (price == null) {
            return 0;
        }
        return Double.parseDouble(price.toString());
    }
}
