import view.MainWindow;

public class Main {
    public static void main(String[] args) {

        MainWindow mainWindow = new MainWindow();

        //СОЗДАТЬ КЛАССЫ ДЛЯ БИРЖ И ЗАКИНУТЬ ТУДА ЛОГИКУ, ЗАМЕНИТЬ АБСТРАКТНЫЙ КЛАСС ОБЫЧНЫМ И ЧЕРЕЗ НЕГО СОЗДАВАТЬ ВАЛЮТЫ

        //Bitcoin bitcoin = new Bitcoin();

        //double binance = bitcoin.findPrice("Binance");
        //double htx = bitcoin.findPrice("HTX");
        //double bybit = bitcoin.findPrice("Bybit");
        //double exmo = bitcoin.findPrice("EXMO");

        /*
        System.out.println("Binance: "+ binance + "\t" +
                findPercents(binance, binance) + "\t" +findPercents(binance, htx) + "\t" +
                findPercents(binance, bybit) + "\t" + findPercents(binance, exmo));
        System.out.println("HTX: "+ htx + "\t" +
                findPercents(htx, binance) + "\t" +findPercents(htx, htx) + "\t" +
                findPercents(htx, bybit) + "\t" + findPercents(htx, exmo));
        System.out.println("Bybit: "+ bybit + "\t" +
                findPercents(bybit, binance) + "\t" +findPercents(bybit, htx) + "\t" +
                findPercents(bybit, bybit) + "\t" + findPercents(bybit, exmo));
        System.out.println("EXMO: "+ exmo + "\t" +
                findPercents(exmo, binance) + "\t" +findPercents(exmo, htx) + "\t" +
                findPercents(exmo, bybit) + "\t" + findPercents(exmo, exmo));

         */

        /*
        Cryptocurrency bitcoin = new Cryptocurrency("Bitcoin", "BTC_USDT");

        System.out.println("Binance " + bitcoin.findPrice("Binance"));
        System.out.println("HTX  " + bitcoin.findPrice("HTX"));
        System.out.println("Bybit " + bitcoin.findPrice("Bybit"));
        System.out.println("EXMO " + bitcoin.findPrice("EXMO"));
         */

        /*
        try
        {
            String url = "https://api.kucoin.com/api/v1/market/orderbook/level1?symbol=BTC-USDT";
            URLConnection connection = new URL(url).openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String json = reader.readLine();
            System.out.println("json: " + json);

            HashMap type = new HashMap<String, Objects>();
            Gson gson = new Gson();

            type = gson.fromJson(json, type.getClass());

            HashMap result = new HashMap<String, String>();

            String text = gson.toJson(type.get("data"));

            result = gson.fromJson(text, result.getClass());

            Object price = result.get("price");
            System.out.println(Double.parseDouble(price.toString()));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

         */
    }
}
