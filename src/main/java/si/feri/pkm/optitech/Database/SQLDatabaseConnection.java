package si.feri.pkm.optitech.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLDatabaseConnection {
    static String connectionUrl =
            "jdbc:sqlserver://DESKTOP-24FQM30:55791;databaseName=OptiTech;integratedSecurity=true";
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static ArrayList<String> getInsertedVehicles() {


        ResultSet resultSet;
        ArrayList<String> avti = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT vehicleId ,vin, vehicleTitle, vehicleName, regNumber, carModelId, fuelTypeId, engineSize, enginePower, enginePowerHP FROM OptiTech.biz.Vehicles  WHERE vehicleTitle != 'Null' AND vehicleName != 'Null' AND vin != 'Null' AND regNumber != 'Null' AND vehicleTitle != 'Car' AND enginePowerHP != 0;";
            resultSet = statement.executeQuery(selectSql);


            // Print results from select statement
            while (resultSet.next()) {
                avti.add(resultSet.getString(4));
//                System.out.println(resultSet.getString(4));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    return avti;
    }
}