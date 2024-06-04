package view;

import model.Cryptocurrency;

import javax.swing.table.AbstractTableModel;

public class CryptocurrencyTableModel extends AbstractTableModel {
    private double binanceValue;
    private double bybitValue;
    private double htxValue;
    private double kuCoinValue;
    public CryptocurrencyTableModel(Cryptocurrency cryptocurrency)
    {
        binanceValue = cryptocurrency.findPrice("Binance");
        //binanceValue = 10;
        System.out.println("binanceValue is created: " + binanceValue);

        bybitValue = cryptocurrency.findPrice("Bybit");
        //bybitValue = 3;
        System.out.println("bybitValue is created: " + bybitValue);

        htxValue = cryptocurrency.findPrice("HTX");
        //htxValue = 0.5;
        System.out.println("htxValue is created: " + htxValue);

        kuCoinValue = cryptocurrency.findPrice("KuCoin");
        //kuCoinValue = 2;
        System.out.println("kuCoinValue is created: " + kuCoinValue);
    }
    @Override
    public int getRowCount() {
        return 4;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            case 0:
                switch (rowIndex)
                {
                    case 0:
                        return "Binance: " + binanceValue;
                    case 1:
                        return "Bybit: " + bybitValue;
                    case 2:
                        return "HTX: " + htxValue;
                    case 3:
                        return "KuCoin: " + kuCoinValue;
                    default:
                        return "default";
                }
            case 1:
                switch (rowIndex)
                {
                    case 0:
                        if (binanceValue == 0)
                        {
                            return "Отсутствует";
                        }
                        else {
                            return "0%";
                        }
                    case 1:

                        return getPercent(bybitValue, binanceValue);
                    case 2:
                        return getPercent(htxValue, binanceValue);
                    case 3:
                        return getPercent(kuCoinValue, binanceValue);
                    default:
                        return "default";
                }
            case 2:
                switch (rowIndex)
                {
                    case 0:
                        return getPercent(binanceValue, bybitValue);
                    case 1:
                        if (bybitValue == 0)
                        {
                            return "Отсутствует";
                        }
                        else {
                            return "0%";
                        }
                    case 2:
                        return getPercent(htxValue, bybitValue);
                    case 3:
                        return getPercent(kuCoinValue, bybitValue);
                    default:
                        return "default";
                }
            case 3:
                switch (rowIndex)
                {
                    case 0:
                        return getPercent(binanceValue, htxValue);
                    case 1:
                        return getPercent(bybitValue, htxValue);
                    case 2:
                        if (htxValue == 0)
                        {
                            return "Отсутствует";
                        }
                        else {
                            return "0%";
                        }
                    case 3:
                        return getPercent(kuCoinValue, htxValue);
                    default:
                        return "default";
                }
            case 4:
                switch (rowIndex)
                {
                    case 0:
                        return getPercent(binanceValue, kuCoinValue);
                    case 1:
                        return getPercent(bybitValue, kuCoinValue);
                    case 2:
                        return getPercent(htxValue, kuCoinValue);
                    case 3:
                        if (kuCoinValue == 0)
                        {
                            return "Отсутствует";
                        }
                        else {
                            return "0%";
                        }
                    default:
                        return "default";
                }
            default:
                return "0%";
        }
    }
    @Override
    public String getColumnName(int column)
    {
        switch (column)
        {
            case 0:
                return "";
            case 1:
                return "Binance: " + binanceValue;
            case 2:
                return "Bybit: " + bybitValue;
            case 3:
                return "HTX: " + htxValue;
            case 4:
                return "KuCoin: " + kuCoinValue;
        }
        return "";
    }
    public String getPercent(double buy, double sale)
    {
        double percents;
        String result;

        if (buy == 0 || sale == 0)
        {
            result = "Отсутствует";
        }
        else
        {
            percents = (100 / buy * sale) - 100;

            result = percents + "%";
        }

        return  result;
    }
}