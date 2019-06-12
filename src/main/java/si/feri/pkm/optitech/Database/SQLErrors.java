package si.feri.pkm.optitech.Database;

import org.json.JSONObject;
import si.feri.pkm.optitech.Entity.ErrorOccurrence;

import java.sql.*;
import java.util.ArrayList;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLErrors {

    //Function that returns ArrayList of ArrayLists, containing all the information for our AI algorithm.
    public static ArrayList<ArrayList<String>> getDataForAI() {
        ResultSet resultSet;
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);

             Statement statement = connection.createStatement()) {

            String selectSql = "SELECT DrvTime, DrvDist,VssAvg, RpmAvg,DrvAbsDistOdo, dtcDescription FROM (SELECT Optitech.tlm.DtcInfo.vehicleId, DrvTime, DrvDist, VssAvg, RpmAvg, DrvAbsDistOdo, dtc FROM Optitech.tlm.DtcInfo RIGHT JOIN (select max(VehicleId) as vehicleId, MAX(DrvAbsDistOdo) as DrvAbsDistOdo, SUM(DrvTime) as DrvTime, SUM(DrvDist) as DrvDist, AVG(RpmAvg) as RpmAvg, AVG(VssAvg) as VssAvg, sessionId from OptiTech.tlm.DriveData WHERE DrvAbsDistOdo != 0 GROUP BY sessionId) AS prva ON Optitech.tlm.DtcInfo.sessionId = prva.sessionId) AS p LEFT JOIN OptiTech.reg.DtcCodes ON p.dtc = optitech.reg.DtcCodes.dtc WHERE Optitech.reg.DtcCodes.dtc != 'null';";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                ArrayList<String> a = new ArrayList<>();
                a.add(Integer.toString(resultSet.getInt(1)));
                a.add(Integer.toString(resultSet.getInt(2)));
                a.add(Integer.toString(resultSet.getInt(3)));
                a.add(Integer.toString(resultSet.getInt(4)));
                a.add(Integer.toString(resultSet.getInt(5)));
                a.add(resultSet.getString(6));
                data.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }


    //Function for errors, which returns when all errors occurred for the first and last time with description and
    // dtcCode in a JSON object.
    public static JSONObject getFirstAndLastOccurrence(int carId) {
        ResultSet resultSet;
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> from = new ArrayList<>();
        ArrayList<String> to = new ArrayList<>();
        ArrayList<String> codes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "SELECT dtcDescription, min(dateMsg) as firstOccurrence, max(dateMsg) as lastOccurrence, DtcCode FROM (SELECT * FROM (SELECT Convert(varchar(11), dateMsg, 23) AS dateMsg, optitech.tlm.DtcInfo.dtc, DtcCode, dtcDescription FROM optitech.tlm.dtcinfo LEFT JOIN (SELECT dtcDescription, dtc FROM optitech.reg.DtcCodes) AS prvi ON optitech.tlm.dtcinfo.dtc = prvi.dtc WHERE vehicleId != 0 AND dtcDescription != 'null' AND vehicleId = "+carId+" AND dateMsg > Convert(datetime, '2017-01-01') AND dateMsg < Convert(datetime, '2027-01-01') GROUP BY DtcInfo.dtc, Convert(varchar(11), dateMsg, 23), DtcCode, DtcInfo.dtc, dtcDescription) AS p) AS tabela GROUP BY DtcCode, dtcDescription, dtc;";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                descriptions.add(resultSet.getString(1));
                from.add(resultSet.getString(2));
                to.add(resultSet.getString(3));
                codes.add(resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject();
        json.put("description", descriptions);
        json.put("from", from);
        json.put("to", to);
        json.put("code", codes);

        return json;
    }
}
