package si.feri.pkm.optitech.Database;

import java.sql.*;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLCarImage {

    // Function, which returns link to an image from our database.
    public static String getCarImage(int carId) {
        ResultSet resultSet;
        String imageLink = "";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            String selectSql = "SELECT ImageLink FROM OptiTech.tlm.CarImage WHERE VehicleID=" + carId + ";";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                imageLink = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageLink;
    }
}

