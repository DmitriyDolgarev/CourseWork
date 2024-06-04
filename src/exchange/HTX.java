package exchange;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Objects;

public class HTX extends CryptocurrencyExchange {
    public void putApi(HashMap<String, String> urls, String pairName)
    {
        switch (pairName)
        {
            case "BTC_USDT":
                urls.put("HTX", "https://api.huobi.pro/market/detail/merged?symbol=btcusdt");
                break;
            case "ETH_USDT":
                urls.put("HTX", "https://api.huobi.pro/market/detail/merged?symbol=ethusdt");
                break;
            case "DOGE_USDT":
                urls.put("HTX", "https://api.huobi.pro/market/detail/merged?symbol=dogeusdt");
                break;
            case "SOL_USDT":
                urls.put("HTX", "https://api.huobi.pro/market/detail/merged?symbol=solusdt");
                break;
            case "PEPE_USDT":
                urls.put("HTX", "https://api.huobi.pro/market/detail/merged?symbol=pepeusdt");
                break;
            case "MANTA_USDT":
                urls.put("HTX", "https://api.huobi.pro/market/detail/merged?symbol=mantausdt");
                break;
            case "XRP_USDT":
                urls.put("HTX", "https://api.huobi.pro/market/detail/merged?symbol=xrpusdt");
                break;
            case "ADA_USDT":
                urls.put("HTX", "https://api.huobi.pro/market/detail/merged?symbol=adausdt");
                break;
            case "TRB_USDT":
                urls.put("HTX", "https://api.huobi.pro/market/detail/merged?symbol=trbusdt");
                break;
            default:
                urls.put("HTX", "default");
                break;
        }
    }
    public double getPrice(String json)
    {
        Gson gson = new Gson();

        HashMap type = new HashMap<String, Objects>();

        type = gson.fromJson(json, type.getClass());

        HashMap<String, Objects> tick = new HashMap<String, Objects>();
        tick = gson.fromJson(type.get("tick").toString(), tick.getClass());

        Object price = tick.get("close");
        if (price == null) {
            return 0;
        }
        return Double.parseDouble(price.toString());
    }
}
