package si.feri.pkm.optitech.Database;

import si.feri.pkm.optitech.Entity.FuelType;

import java.sql.*;
import java.util.ArrayList;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLFuelType {

    // Function that returns all the possible FuelTypes we have in our database.
    public static ArrayList<FuelType> getAllFuelTypes() {
        ResultSet resultSet;
        ArrayList<FuelType> fuelTypes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            String selectSql = "SELECT * FROM OptiTech.reg.FuelTypes;";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                FuelType f = new FuelType(resultSet.getInt(1), resultSet.getString(2));
                fuelTypes.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fuelTypes;
    }

}
