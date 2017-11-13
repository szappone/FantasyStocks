import com.jimmoores.quandl.*;
import com.jimmoores.quandl.classic.ClassicQuandlSession;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import java.util.Date;
import java.util.HashMap;


public class priceCalculator {
    public static void main(String[] args) {

        HashMap<String, String> x = new HashMap<String, String>();
        x.put("AAPL","Long");
        x.put("AMZN","Long");
        x.put("GM","Long");
        x.put("TRIP","Short");
        x.put("AMT","Short");
        x.put("LUK","Short");
        HashMap<String, Double> scorer = score(x);
        for (String key : scorer.keySet()){
            System.out.println("For stock " + key + ", value is " + scorer.get(key) + "%");
        }

    }
    public static HashMap<String, Double> score (HashMap<String, String> input) {
        HashMap<String, Double> scorer = new HashMap<String, Double>();
        ClassicQuandlSession session = ClassicQuandlSession.create();
        double sum = 0;
        int findctr = 0;
        int find_monday = 0;
        for (String key : input.keySet()){
            System.out.println(key+" "+input.get(key));
            TabularResult tabularResult = session.getDataSet(
                    DataSetRequest.Builder
		    .of("WIKI/"+key)
		    .withFrequency(Frequency.DAILY)
		    .withMaxRows(5)
		    .withColumn(4)
		    .build());
            TabularResult tabularResult2 = session.getDataSet(
                    DataSetRequest.Builder
		    .of("WIKI/"+key)
		    .withFrequency(Frequency.DAILY)
		    .withMaxRows(5)
		    .withColumn(1)
		    .build());
            if (findctr == 0) {
                for(find_monday = 0; find_monday < 5; find_monday++) {
                    Row row = tabularResult.get(find_monday);
                    LocalDate date = row.getLocalDate("Date");
                    DayOfWeek dow = date.getDayOfWeek();
                    if (dow.toString().equalsIgnoreCase("Monday")) {
                        findctr++;
                        if (find_monday == 5) {
                            scorer.put("isFriday", 1.0);
                        }
                        else {
                            scorer.put("isFriday", 0.0);
                        }
                        break;
                    }
                }
            }

            Row row1 = tabularResult.get(0);
            Row row2 = tabularResult2.get(find_monday);

            Double value = row1.getDouble(1);
            Double value2 = row2.getDouble(1);
            //System.out.println(tabularResult.toPrettyPrintedString());
            if (input.get(key).equalsIgnoreCase("Long")) {
                sum = sum + (value/value2 - 1)*100;
                scorer.put(key,(value/value2 - 1)*100);
            }
            else {
                sum = sum + (value2/value - 1)*100;
                scorer.put(key,(value2/value - 1)*100);
            }
            scorer.put("Total", sum/6);

        }
        return scorer;
    }
}
