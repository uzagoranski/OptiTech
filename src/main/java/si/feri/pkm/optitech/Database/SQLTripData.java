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

    public static JSONObject getTripDataRPM(int carId, String date) {
        ResultSet resultSet;
        JSONObject json = new JSONObject();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Integer> rpmAvg = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "select Convert(varchar(8), dateMsg, 108) as time, RpmAvg from OptiTech.tlm.driveData where vehicleId="+carId+" AND Convert(varchar(11), dateMsg, 23) = \'"+date+"\' AND VssAvg != 0 ORDER BY dateMsg;";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                dates.add(resultSet.getString(1));
                rpmAvg.add(resultSet.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        json.put("date2", dates);
        json.put("rpmAvg", rpmAvg);
        return json;

    }

    public static void neKliciTega(int carId, String date) {
        ResultSet resultSet;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "select Convert(varchar(11), dateMsg, 23), MIN(dateMsg) as firstTime, MAX(dateMsg) as lastTime, MAX(userID) as userID, MAX(DrvAbsDistOdo) as lastDistance, MIN(DrvAbsDistOdo) as firstDistance, AVG(ScorePPAL) as scorePPAL, AVG(ScoreRPOL) as scoreRPOL, AVG(ScoreRVAR) as scoreRVAR, AVG(ScoreTotal) as scoreTotal, AVG(RpmAvg) as rpmAvg, MAX(RpmMax) as rpmMax, AVG(VssAvg) as vssAvg, MAX(VssMax) as vssMax from OptiTech.tlm.driveData where vehicleId = "+carId+" AND Convert(varchar(11), dateMsg, 23) = \'"+date+"\' GROUP BY Convert(varchar(11), dateMsg, 23);";

            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
