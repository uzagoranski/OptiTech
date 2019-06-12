package si.feri.pkm.optitech.Database;

import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLTripData {

    public static JSONObject getTripData(int carId, String date) {
        ResultSet resultSet;
        JSONObject json = new JSONObject();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Integer> data = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "select Convert(varchar(8), dateMsg, 108) as time, VssAvg from OptiTech.tlm.driveData where vehicleId="+carId+" AND Convert(varchar(11), dateMsg, 23) = \'"+date+"\' AND VssAvg != 0 ORDER BY dateMsg;";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                dates.add(resultSet.getString(1));
                data.add(resultSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        json.put("date", dates);
        json.put("vssAvg", data);
        return json;

    }
}
