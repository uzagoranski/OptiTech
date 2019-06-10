package si.feri.pkm.optitech.Database;

import si.feri.pkm.optitech.Entity.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import si.feri.pkm.optitech.Entity.Error;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLCarsDatabase {

    public static ArrayList<Vehicle> getInsertedVehicles() {

        ResultSet resultSet;
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            String selectSql = "select Vehicles.carModelId, carMakerId,carModel,vehicleSubtypeId,countryID,carMaker,vehicleId,vin,vehicleTitle,regNumber,carModelYear,fuelTypeId,drivenWheelsId,engineSize,enginePower,dateRegStart,dateRegEnd from (select prvi.carModelId, carMakerId, carModel, vehicleSubtypeId, countryID, carMaker  from (select carModel, vehicleSubtypeId, carMakerId, carModelId from optitech.reg.carModels where carModelId IN (select carModelId from OptiTech.biz.Vehicles Where vehicleId IN  (select distinct vehicleId from optitech.tlm.DriveData))) as prvi left join (select carModelId,countryId,carMaker from optitech.reg.carModels LEFT JOIN optitech.reg.CarMakers on optitech.reg.CarMakers.carMakerId = optitech.reg.CarModels.carMakerId) as drugi on prvi.carModelId = drugi.carModelId) AS tabela LEFT join  OptiTech.biz.Vehicles  ON tabela.carModelId = optitech.biz.vehicles.carModelId WHERE engineSize != 0 AND countryID != 'XY' AND vehicleId != 1357;";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                Vehicle vehicle = null;
                vehicle = createVehicleFromDb(resultSet);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public static Vehicle getSelectedVehicle(int carID) {
        Vehicle vehicle = null;
        ResultSet resultSet;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            String selectSql = "SELECT Vehicles.carModelId, carMakerId,carModel,vehicleSubtypeId,countryID,carMaker,vehicleId,vin,vehicleTitle,regNumber,carModelYear,fuelTypeId,drivenWheelsId,engineSize,enginePower,dateRegStart,dateRegEnd from (select prvi.carModelId, carMakerId, carModel, vehicleSubtypeId, countryID, carMaker  from (select carModel, vehicleSubtypeId, carMakerId, carModelId from optitech.reg.carModels where carModelId IN (select carModelId from OptiTech.biz.Vehicles Where vehicleId IN  (select distinct vehicleId from optitech.tlm.DriveData))) as prvi left join (select carModelId,countryId,carMaker from optitech.reg.carModels LEFT JOIN optitech.reg.CarMakers on optitech.reg.CarMakers.carMakerId = optitech.reg.CarModels.carMakerId) as drugi on prvi.carModelId = drugi.carModelId) AS tabela LEFT join  OptiTech.biz.Vehicles  ON tabela.carModelId = optitech.biz.vehicles.carModelId WHERE vehicleId =" + carID + ";";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                vehicle = createVehicleFromDb(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    public static Vehicle createVehicleFromDb(ResultSet resultSet) {

        Vehicle vehicle = null;

        try {
            vehicle = new Vehicle(resultSet.getLong(1),
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vehicle;
    }

    public static int getMaxSpeedForSelectedCar(int carId) {
        ResultSet resultSet;

        int maxSpeed = -1;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "select MAX(VssMax) from Optitech.tlm.DriveData where vehicleId=" + carId + ";";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                maxSpeed = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxSpeed;
    }

    public static int getMaxRpmForSelectedCar(int carId) {
        ResultSet resultSet;

        int rpmSpeed = -1;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "select MAX(RpmMax) from Optitech.tlm.DriveData where vehicleId=" + carId + ";";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                rpmSpeed = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rpmSpeed;
    }

    public static ArrayList<Error> getErrorsOnSelectedCar(int carId, String from, String to) {
        ResultSet resultSet;

        ArrayList<Error> errors = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "    SELECT dateMsg, userID,DtcInfo.dtc,dtcCode, timeLog,dtcDescription FROM optitech.tlm.dtcinfo LEFT JOIN (SELECT dtcDescription,dtc FROM optitech.reg.DtcCodes) AS prvi ON optitech.tlm.dtcinfo.dtc = prvi.dtc WHERE vehicleId!=0 AND dtcDescription != 'null' AND vehicleId ="+ carId + " AND dateMsg > Convert(datetime, \'"+ from +"\') AND dateMsg < Convert(datetime, \'"+ to +"\')  ORDER BY dateMsg";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                Error e = new Error(
                resultSet.getString(6),resultSet.getInt(4),resultSet.getDate(1));
                errors.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return errors;
    }

}