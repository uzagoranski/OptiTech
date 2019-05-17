package si.feri.pkm.optitech.Database;

import si.feri.pkm.optitech.Entity.Vehicle;

import java.sql.*;
import java.util.ArrayList;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLCarsDatabase {

    public static ArrayList<Vehicle> getInsertedVehicles() {

        ResultSet resultSet;
        ArrayList<Vehicle> avti = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            //DA TA QUERY DELUJE JE POTREBNO NAREDITI VIEW S TO KODO:
            //Create view nekaj as (select Distinct carModelId from OptiTech.biz.Vehicles Where vehicleId IN  (select DISTINCT vehicleId from optitech.tlm.DriveData))
            String selectSql = "select * from (select * from optitech.reg.carModels where carModelId IN (select * from OptiTech.dbo.nekaj)) as prvi left join (select carModelId,countryId,carMaker from optitech.reg.carModels LEFT JOIN optitech.reg.CarMakers on optitech.reg.CarMakers.carMakerId = optitech.reg.CarModels.carMakerId) as drugi on prvi.carModelId = drugi.carModelId;";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            int i = 0;
            while (resultSet.next()) {
                i++;
                Vehicle v = new Vehicle(resultSet.getLong(1), resultSet.getLong(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(7), resultSet.getString(8));
                avti.add(v);
//                System.out.println(resultSet.getLong(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avti;
    }
}
