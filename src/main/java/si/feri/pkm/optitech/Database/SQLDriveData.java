package si.feri.pkm.optitech.Database;

import si.feri.pkm.optitech.Entity.FuelType;

import java.sql.*;
import java.util.ArrayList;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLDriveData {

    public static ArrayList<Integer> getDriveData(int carID){
        ResultSet resultSet;
        ArrayList<Integer> driveData= new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT AVG(DrvDist) as averageDistance, AVG(DrvTime) as averageDriveTime, AVG(RpmAvg) as averageRPM, AVG(RpmMax) as averageRPMMax, avg(VssMax) as averageVssMax FROM OptiTech.tlm.DriveData WHERE vehicleId="+carID+" AND DrvTime != 0 AND DrvDist > 400;";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                int averageDistance = resultSet.getInt(1);
                int averageDriveTime = resultSet.getInt(2);
                int averageRPM = resultSet.getInt(3);
                int averageRPMMax = resultSet.getInt(4);
                int averageVssMax = resultSet.getInt(5);
                driveData.add(averageDistance);
                driveData.add(averageDriveTime);
                driveData.add(averageRPM );
                driveData.add(averageRPMMax);
                driveData.add(averageVssMax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driveData;
    }

}
