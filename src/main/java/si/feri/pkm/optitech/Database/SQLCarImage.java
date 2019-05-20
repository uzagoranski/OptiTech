package si.feri.pkm.optitech.Database;

import si.feri.pkm.optitech.Entity.FuelType;

import java.sql.*;
import java.util.ArrayList;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLCarImage {

    public static String getCarImage(int carId){
        ResultSet resultSet;
        String naslovSlike ="";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT ImageLink FROM OptiTech.tlm.CarImage WHERE VehicleID="+carId+";";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
            naslovSlike = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return naslovSlike;
    }
}

