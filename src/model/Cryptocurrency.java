package model;

import exchange.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class Cryptocurrency {
    private String name;
    private String pairName;
    private HashMap<String, String> APIurls;
    private boolean favourite;
    private Binance binance;
    private HTX htx;
    private Bybit bybit;
    private KuCoin kuCoin;
    private EXMO exmo;
    public Cryptocurrency(String name, String pairName)
    {
        binance = new Binance();
        htx = new HTX();
        bybit = new Bybit();
        kuCoin = new KuCoin();
        exmo = new EXMO();

        this.name = name;
        this.pairName = pairName;
        APIurls = new HashMap<String, String>();
        this.favourite = false;

        binance.putApi(this.APIurls, this.pairName);
        htx.putApi(this.APIurls, this.pairName);
        bybit.putApi(this.APIurls, this.pairName);
        kuCoin.putApi(this.APIurls, this.pairName);
    }
    public String getName()
    {
        return this.name;
    }
    public String getPairName()
    {
        return this.pairName;
    }
    public HashMap<String, String> getAPIurl()
    {
        return this.APIurls;
    }
    public boolean isFavourite()
    {
        return this.favourite;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPairName(String pairName) {
        this.pairName = pairName;
    }
    public void setAPIurl(HashMap<String, String> APIurls) {
        this.APIurls = APIurls;
    }
    public void setFavourite(boolean fav)
    {
        this.favourite = fav;
    }
    public double findPrice(String market)
    {
        double price=0;
        try {
            String url = getAPIurl().get(market);

            if (url == "default")
            {
                price = 0;
            }
            else
            {
                URLConnection connection = new URL(url).openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String json = reader.readLine();

                switch (market)
                {
                    case "Binance":
                        price = binance.getPrice(json);
                        break;
                    case "HTX":
                        price = htx.getPrice(json);
                        break;
                    case "Bybit":
                        price = bybit.getPrice(json);
                        break;
                    case "KuCoin":
                        price = kuCoin.getPrice(json);
                        break;
                    case "EXMO":
                        price = exmo.getPrice(json, this.pairName);
                        break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return price;
    }
}
