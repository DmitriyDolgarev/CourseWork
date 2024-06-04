package exchange;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Objects;

public class KuCoin extends CryptocurrencyExchange {
    public void putApi(HashMap<String, String> urls, String pairName)
    {
        switch (pairName)
        {
            case "BTC_USDT":
                urls.put("KuCoin", "https://api.kucoin.com/api/v1/market/orderbook/level1?symbol=BTC-USDT");
                break;
            case "ETH_USDT":
                urls.put("KuCoin", "https://api.kucoin.com/api/v1/market/orderbook/level1?symbol=ETH-USDT");
                break;
            case "DOGE_USDT":
                urls.put("KuCoin", "https://api.kucoin.com/api/v1/market/orderbook/level1?symbol=DOGE-USDT");
                break;
            case "SOL_USDT":
                urls.put("KuCoin", "https://api.kucoin.com/api/v1/market/orderbook/level1?symbol=SOL-USDT");
                break;
            case "PEPE_USDT":
                urls.put("KuCoin", "https://api.kucoin.com/api/v1/market/orderbook/level1?symbol=PEPE-USDT");
                break;
            case "MANTA_USDT":
                urls.put("KuCoin", "https://api.kucoin.com/api/v1/market/orderbook/level1?symbol=MANTA-USDT");
                break;
            case "XRP_USDT":
                urls.put("KuCoin", "https://api.kucoin.com/api/v1/market/orderbook/level1?symbol=XRP-USDT");
                break;
            case "ADA_USDT":
                urls.put("KuCoin", "https://api.kucoin.com/api/v1/market/orderbook/level1?symbol=ADA-USDT");
                break;
            case "TRB_USDT":
                urls.put("KuCoin", "https://api.kucoin.com/api/v1/market/orderbook/level1?symbol=TRB-USDT");
                break;
            default:
                urls.put("KuCoin", "default");
                break;
        }
    }
    public double getPrice(String json)
    {
        HashMap type = new HashMap<String, Objects>();
        Gson gson = new Gson();

        type = gson.fromJson(json, type.getClass());

        HashMap result = new HashMap<String, String>();

        String text = gson.toJson(type.get("data"));

        result = gson.fromJson(text, result.getClass());

        Object price = result.get("price");
        if (price == null) {
            return 0;
        }
        return Double.parseDouble(price.toString());
    }
}
