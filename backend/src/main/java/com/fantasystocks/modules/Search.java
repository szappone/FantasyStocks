import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Search {

    public static void main(String[] args) throws Exception {
        Map<String, String> data = make_stock_data_base();
        String s = "AAPL";
        String[] stock = stock_data(data, s);
        System.out.println(stock[0] + " " + stock[1]) ;
    }

    public static Map<String, String> make_stock_data_base() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        BufferedReader in = new BufferedReader(new FileReader("src/main/resources/StockDataBase"));
        String line;
        while ((line = in.readLine()) != null) {
            String[] parts = line.split("\t");
            data.put(parts[0], parts[1]);
        }
        in.close();
        //System.out.println(data.toString());
        return data;
    }

    public static String[] stock_data(Map<String, String> data, String n) {
        String[] stock = new String[2];
        if (data.containsKey(n)){
            stock[0] = n;
            stock[1] = data.get(n);
        }
        else {
            stock[0] = "X";
            stock[1] = "X";
        }
        return stock;
    }
}


