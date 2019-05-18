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
            String selectSql = "select Vehicles.carModelId, carMakerId,carModel,vehicleSubtypeId,countryID,carMaker,vehicleId,vin,vehicleTitle,regNumber,carModelYear,fuelTypeId,drivenWheelsId,engineSize,enginePower,dateRegStart,dateRegEnd from (select prvi.carModelId, carMakerId, carModel, vehicleSubtypeId, countryID, carMaker  from (select carModel, vehicleSubtypeId, carMakerId, carModelId from optitech.reg.carModels where carModelId IN (select carModelId from OptiTech.biz.Vehicles Where vehicleId IN  (select distinct vehicleId from optitech.tlm.DriveData))) as prvi left join (select carModelId,countryId,carMaker from optitech.reg.carModels LEFT JOIN optitech.reg.CarMakers on optitech.reg.CarMakers.carMakerId = optitech.reg.CarModels.carMakerId) as drugi on prvi.carModelId = drugi.carModelId) AS tabela LEFT join  OptiTech.biz.Vehicles  ON tabela.carModelId = optitech.biz.vehicles.carModelId WHERE engineSize != 0 AND countryID != 'XY' AND vehicleId != 1357;";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            int i = 0;
            while (resultSet.next()) {
                i++;
                    Vehicle v = new Vehicle(resultSet.getLong(1),
                                            resultSet.getLong(2),
                                            resultSet.getString(3),
                                            resultSet.getInt(4),
                                            resultSet.getString(5),
                                            resultSet.getString(6),
                                            resultSet.getInt(7),
                                            resultSet.getString(8),
                                            resultSet.getString(9),
                                            resultSet.getString(10),
                                            resultSet.getInt(11),
                                            resultSet.getInt(12),
                                            resultSet.getInt(13),
                                            resultSet.getInt(14),
                                            resultSet.getInt(15),
                                            resultSet.getDate(16),
                                            resultSet.getDate(17));
                avti.add(v);
//                System.out.println(resultSet.getLong(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avti;
    }
}
