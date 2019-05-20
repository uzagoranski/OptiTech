package si.feri.pkm.optitech.Database;

import si.feri.pkm.optitech.Entity.Drive;
import si.feri.pkm.optitech.Entity.FuelType;

import java.sql.*;
import java.util.ArrayList;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLDrive {
    public static ArrayList<Drive> getAllDriveTypes(){
        ResultSet resultSet;
        ArrayList<Drive> drives= new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM OptiTech.reg.DrivenWheels;";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                Drive d = new Drive(resultSet.getInt(1), resultSet.getString(2));
                drives.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drives;
    }

}
