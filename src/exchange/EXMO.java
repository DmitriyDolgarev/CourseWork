package exchange;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Objects;

public class EXMO {
    private static String allPairs;
    public static void findAllPairs()
    {
        try
        {
            String url = "https://api.exmo.com/v1/ticker";
            URLConnection connection = new URL(url).openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String json = reader.readLine();

            allPairs = json;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void putApi(HashMap<String, String> urls, String pairName)
    {
        HashMap type = new HashMap<String, Objects>();
        Gson gson = new Gson();

        type = gson.fromJson(allPairs, type.getClass());

        if (type.keySet().contains(pairName))
        {
            urls.put("EXMO", "https://api.exmo.com/v1/ticker");
        }
        else
        {
            urls.put("EXMO", "default");
        }
    }
    public double getPrice(String json, String pairName)
    {
        HashMap type = new HashMap<String, Objects>();
        Gson gson = new Gson();

        type = gson.fromJson(json, type.getClass());

        HashMap result = new HashMap<String, String>();

        String text = gson.toJson(type.get(pairName));

        result = gson.fromJson(text, result.getClass());

        Object price = result.get("buy_price");

        if (price == null) {
            return 0;
        }
        return Double.parseDouble(price.toString());
    }
}
