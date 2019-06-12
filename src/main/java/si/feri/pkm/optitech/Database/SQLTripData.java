package si.feri.pkm.optitech.Database;

import org.json.JSONObject;
import si.feri.pkm.optitech.Entity.TripData;

import java.sql.*;
import java.util.ArrayList;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLTripData {

    public static JSONObject getVssAvg(int carId, String date) {
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

    public static JSONObject getRpmAvg(int carId, String date) {
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

    public static TripData neKliciTega(int carId, String date) {
        ResultSet resultSet;
        TripData td = new TripData();

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "select MIN(Convert(varchar(8), dateMsg, 108)) as firstTime, MAX(Convert(varchar(8), dateMsg, 108)) as lastTime, MAX(userID) as userID, MAX(DrvAbsDistOdo) as lastDistance, MIN(DrvAbsDistOdo) as firstDistance, AVG(ScorePPAL) as scorePPAL, AVG(ScoreRPOL) as scoreRPOL, AVG(ScoreRVAR) as scoreRVAR, AVG(ScoreTotal) as scoreTotal, AVG(RpmAvg) as rpmAvg, MAX(RpmMax) as rpmMax, AVG(VssAvg) as vssAvg, MAX(VssMax) as vssMax from OptiTech.tlm.driveData where vehicleId = "+carId+" AND Convert(varchar(11), dateMsg, 23) = \'"+date+"\' GROUP BY Convert(varchar(11), dateMsg, 23);";

            resultSet = statement.executeQuery(selectSql);
            int score =0;
            int stevec =0;
            while (resultSet.next()) {
                for(int i=6;i<10;i++){
                    if(resultSet.getInt(i) != 0){
                        score += resultSet.getInt(i);
                        stevec+=1;
                    }
                }

            td = new TripData(
              resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    ((resultSet.getInt(4)-resultSet.getInt(5))/1000),
                    (score/stevec),
                    resultSet.getInt(10),
                    resultSet.getInt(11),
                    resultSet.getInt(12),
                    resultSet.getInt(13)
            );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return td;
    }
}
