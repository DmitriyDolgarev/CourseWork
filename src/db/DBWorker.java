package db;

import model.Cryptocurrency;

import java.sql.*;
import java.util.ArrayList;

public class DBWorker {
    private static final String URL = "jdbc:postgresql://localhost:5432/cryptocurrencies?user=postgres&password=1";
    private static Connection connection;

    public static void initDB()
    {
        try
        {
            connection = DriverManager.getConnection(URL);
            if (connection!=null)
            {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getDriverName());
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void closeDB()
    {
        try {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static int getFavouritesRowCount()
    {
        int count = 0;
        try
        {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM favourites;");

            while(resultSet.next())
            {
                ++count;
            }
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return count;
    }
    public static void deleteFavourites()
    {
        try
        {
            if (getFavouritesRowCount() != 0)
            {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM favourites;");
                statement.execute();
                statement.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void getFavourites(ArrayList<Cryptocurrency> coins)
    {
        if (getFavouritesRowCount() != 0)
        {
            try
            {
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM favourites;");

                while (resultSet.next())
                {
                    for (int i = 0; i < coins.size(); ++i)
                    {
                        if (coins.get(i).getName().equals(resultSet.getString(2)))
                        {
                            System.out.println("Нашлось");
                            coins.get(i).setFavourite(true);
                        }
                    }
                }

                statement.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void addFavourites(ArrayList<Cryptocurrency> coins)
    {
        int count = 0;

        for (int i = 0; i < coins.size(); ++i)
        {
            if (coins.get(i).isFavourite())
            {
                ++count;
                addCryptocurrency(coins.get(i).getName(), count);
            }
        }
    }
    public static void addCryptocurrency(String name, int id)
    {
        try
        {
            PreparedStatement statement;

            statement = connection.prepareStatement("INSERT INTO favourites VALUES(?, ?);");

            statement.setInt(1, id);
            statement.setString(2, name);

            statement.execute();
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}