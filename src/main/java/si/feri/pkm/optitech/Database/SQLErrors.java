package si.feri.pkm.optitech.Database;

import java.sql.*;
import java.util.ArrayList;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLErrors {

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
}
