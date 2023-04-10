import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        // пример строкдля ввода
//        2 3
//        {"offers": [{"offer_id": "offer1", "market_sku": 10846332, "price": 1490}, {"offer_id": "offer2", "market_sku": 682644, "price": 499}]}
//        {"offers": [{"offer_id": "offer3", "market_sku": 832784, "price": 14000}, {"offer_id": "offer4", "market_sku": 3234, "price": 100}]}

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = reader.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArrayResult = new JSONArray();
            boolean stop = false;

            for (int i = 1; i <= n; i++) {
                String currentFeed = reader.readLine();
                JSONObject jsonObj = (JSONObject) parser.parse(currentFeed);
                JSONArray jsonArr = (JSONArray) jsonObj.get("offers");
                for (int j = 0; j < jsonArr.size(); j++) {
                    jsonArrayResult.add(jsonArr.get(j));
                    if (jsonArrayResult.size() >= m) {
                        stop = true;
                        break;
                    }
                }
                if (stop) {
                    break;
                }
            }

            JSONObject jsonObjectResult = new JSONObject();
            jsonObjectResult.put("offers", jsonArrayResult);
            System.out.println(jsonObjectResult.toJSONString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
