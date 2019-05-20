package si.feri.pkm.optitech.Database;

import si.feri.pkm.optitech.Entity.FuelType;

import java.sql.*;
import java.util.ArrayList;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLDriveData {

    public static ArrayList<Integer> getAllFuelTypes(int carID){
        ResultSet resultSet;
        ArrayList<Integer> driveData= new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT AVG(DrvDist) as povprecna_Prevozena_Razdalja, AVG(DrvTime) as povprecen_Cas_Voznje, AVG(RpmAvg) as povprecen_RPM_Povprecna, AVG(RpmMax) as povprecen_RPM_Max, avg(VssMax) as povprecna_hitrost_max FROM OptiTech.tlm.DriveData WHERE vehicleId="+carID+" AND DrvTime != 0 AND DrvDist > 400;";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                int povprecna_Prevozena_Razdalja = resultSet.getInt(1);
                int povprecen_Cas_Voznje = resultSet.getInt(2);
                int povprecen_RPM_Povprecna = resultSet.getInt(3);
                int povprecen_RPM_Max = resultSet.getInt(4);
                int povprecna_hitrost_Max = resultSet.getInt(5);
                driveData.add(povprecna_Prevozena_Razdalja);
                driveData.add(povprecen_Cas_Voznje);
                driveData.add(povprecen_RPM_Povprecna);
                driveData.add(povprecen_RPM_Max);
                driveData.add(povprecna_hitrost_Max);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driveData;
    }

}
