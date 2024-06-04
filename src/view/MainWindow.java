package view;

import model.Cryptocurrency;
import db.DBWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class MainWindow extends JFrame {
    private ArrayList<Cryptocurrency> coins;
    private ArrayList<JButton> cryptocurrencyButtons;
    private HashMap<Cryptocurrency, JButton> hashMap;
    private JPanel favoriteButtons;
    private CryptocurrencyPanel favCryptocurrencyPanel;
    private JPanel allButtons;
    private CryptocurrencyPanel usualCryptocurrencyPanel;
    private JScrollPane allCryptoScrollPane;
    private JScrollPane favoriteCryptoScrollPane;
    private JTabbedPane jTabbedPane;
    private JPanel mainPanel;
    public MainWindow()
    {
        hashMap = new HashMap<>();

        cryptocurrencyButtons = new ArrayList<>();

        Cryptocurrency bitcoin = new Cryptocurrency("Bitcoin", "BTC_USDT");
        Cryptocurrency etherium = new Cryptocurrency("Etherium", "ETH_USDT");
        Cryptocurrency dogecoin = new Cryptocurrency("Dogecoin", "DOGE_USDT");
        Cryptocurrency solana = new Cryptocurrency("Solana", "SOL_USDT");
        Cryptocurrency pepe = new Cryptocurrency("PEPE", "PEPE_USDT");
        Cryptocurrency manta = new Cryptocurrency("MANTA", "MANTA_USDT");
        Cryptocurrency xrp = new Cryptocurrency("XRP", "XRP_USDT");
        Cryptocurrency ada = new Cryptocurrency("ADA", "ADA_USDT");
        Cryptocurrency trb = new Cryptocurrency("TRB", "TRB_USDT");

        coins = new ArrayList<>();

        coins.add(bitcoin);
        coins.add(etherium);
        coins.add(dogecoin);
        coins.add(solana);
        coins.add(pepe);
        coins.add(manta);
        coins.add(xrp);
        coins.add(ada);
        coins.add(trb);

        DBWorker.initDB();
        DBWorker.getFavourites(coins);

        mainPanel = new JPanel();
        allButtons = new JPanel();
        favoriteButtons = new JPanel();
        jTabbedPane = new JTabbedPane();

        JPanel allCryptocurrenciesPanel = new JPanel();
        allCryptocurrenciesPanel.setLayout(new BorderLayout());

        JPanel favoriteCryptocurrenciesPanel = new JPanel();
        favoriteCryptocurrenciesPanel.setLayout(new BorderLayout());

        jTabbedPane.addTab("Все криптовалюты", allCryptocurrenciesPanel);
        jTabbedPane.addTab("Избранное", favoriteCryptocurrenciesPanel);

        usualCryptocurrencyPanel = new CryptocurrencyPanel();
        favCryptocurrencyPanel = new CryptocurrencyPanel();

        makeAllButtons();

        makeFavouriteButtons();

        jTabbedPane.addChangeListener(e -> {
            if (jTabbedPane.getSelectedIndex() == 1)
            {
                favoriteButtons.removeAll();
                makeFavouriteButtons();

                if (getFavouritesCount() == 0)
                {
                    //favCryptocurrencyPanel = new CryptocurrencyPanel();
                    favCryptocurrencyPanel.clear();
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DBWorker.deleteFavourites();
                DBWorker.addFavourites(coins);
                DBWorker.closeDB();
            }
        });

        allButtons.setLayout(new GridLayout(0, 1));
        favoriteButtons.setLayout(new GridLayout(0, 1));

        allCryptoScrollPane = new JScrollPane(allButtons);
        favoriteCryptoScrollPane = new JScrollPane(favoriteButtons);

        mainPanel.setLayout(new BorderLayout());

        allCryptocurrenciesPanel.add(allCryptoScrollPane, BorderLayout.WEST);
        allCryptocurrenciesPanel.add(usualCryptocurrencyPanel, BorderLayout.CENTER);

        favoriteCryptocurrenciesPanel.add(favoriteCryptoScrollPane, BorderLayout.WEST);
        favoriteCryptocurrenciesPanel.add(favCryptocurrencyPanel, BorderLayout.CENTER);

        mainPanel.add(jTabbedPane, BorderLayout.CENTER);

        add(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setVisible(true);

    }
    public void makeAllButtons()
    {
        for (int i = 0; i<coins.size(); ++i)
        {
            JButton btn = new JButton(coins.get(i).getName());
            Cryptocurrency cryptocurrency = coins.get(i);

            hashMap.put(cryptocurrency, btn);

            cryptocurrencyButtons.add(btn);

            allButtons.add(btn);

            btn.addActionListener(e -> {
                usualCryptocurrencyPanel.makePanel(cryptocurrency);
            });
        }
    }
    public void makeFavouriteButtons()
    {
        for (Cryptocurrency cryptocurrency: hashMap.keySet())
        {
            if (cryptocurrency.isFavourite())
            {
                JButton btn = new JButton(cryptocurrency.getName());

                favoriteButtons.add(btn);
                btn.addActionListener(e -> {
                    favCryptocurrencyPanel.makePanel(cryptocurrency);
                });
            }
        }
    }
    public int getFavouritesCount()
    {
        int count = 0;

        for (Cryptocurrency cryptocurrency: hashMap.keySet())
        {
            if (cryptocurrency.isFavourite())
            {
                ++count;
            }
        }

        return count;
    }
}
