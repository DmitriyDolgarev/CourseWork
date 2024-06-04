package view;

import model.Cryptocurrency;

import javax.swing.*;
import java.awt.*;

public class CryptocurrencyPanel extends JPanel {
    private JLabel name;
    private JButton favourite;
    private JTable table;
    private JPanel header;
    private JScrollPane jScrollPane;
    public CryptocurrencyPanel()
    {
        setLayout(new BorderLayout());
        header = new JPanel();
        header.setLayout(new GridLayout(1, 2));

        name = new JLabel("Выберите криптовалюту");
        favourite = new JButton();
        favourite.setVisible(false);

        table = new JTable();
        table.setVisible(false);
        jScrollPane = new JScrollPane(table);

        header.add(name);
        header.add(favourite);

        add(header, BorderLayout.NORTH);
        add(jScrollPane, BorderLayout.CENTER);
    }
    public void makePanel(Cryptocurrency cryptocurrency)
    {
        name.setText(cryptocurrency.getName());

        header.remove(favourite);
        jScrollPane.remove(table);
        remove(jScrollPane);

        favourite = new JButton();
        favourite.setVisible(true);
        setBtnText(cryptocurrency);

        System.out.println("Начало запроса");

        table = new JTable();
        table.setModel(new CryptocurrencyTableModel(cryptocurrency));

        for (int i=1; i < table.getColumnCount(); ++i)
        {
            table.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
        }

        System.out.println("Конец запроса");

        jScrollPane = new JScrollPane(table);

        header.add(favourite);
        add(jScrollPane, BorderLayout.CENTER);

        favourite.addActionListener(e -> {
            if (favourite.getText() == "Добавить в избранное")
            {
                cryptocurrency.setFavourite(true);
            }
            else {
                cryptocurrency.setFavourite(false);
            }
            setBtnText(cryptocurrency);
        });

    }
    public void setBtnText(Cryptocurrency cryptocurrency)
    {
        if (cryptocurrency.isFavourite())
        {
            favourite.setText("Убрать из избранного");
        }
        else
        {
            favourite.setText("Добавить в избранное");
        }
    }
    public void clear()
    {
        name.setText("Выберите криптовалюту");
        favourite.setVisible(false);
        jScrollPane.setVisible(false);
    }
}
