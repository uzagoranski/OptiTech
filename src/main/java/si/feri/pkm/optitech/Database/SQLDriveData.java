package si.feri.pkm.optitech.Database;

import org.json.JSONObject;
import si.feri.pkm.optitech.Entity.DriveData;
import si.feri.pkm.optitech.Entity.VehicleForScore;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import static si.feri.pkm.optitech.Database.SQLDatabaseConnection.connectionUrl;

public class SQLDriveData {

    public static ArrayList<Integer> getDriveData(int carID) {
        ResultSet resultSet;
        ArrayList<Integer> driveData = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT AVG(DrvDist) as averageDistance, AVG(DrvTime) as averageDriveTime, AVG(RpmAvg) as averageRPM, AVG(RpmMax) as averageRPMMax, avg(VssMax) as averageVssMax FROM OptiTech.tlm.DriveData WHERE vehicleId=" + carID + " AND DrvTime != 0 AND DrvDist > 400;";
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
                driveData.add(averageRPM);
                driveData.add(averageRPMMax);
                driveData.add(averageVssMax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driveData;
    }


    public static JSONObject vssAvgSpeedForSelectedCar(int carID, String first, String last) {
        ResultSet resultSet;
        ResultSet resultSetZaStevilo;
        ArrayList<Integer> vssAvgSpeedForSelectedCar = new ArrayList<>();
        ArrayList<Date> dateForSelectedCar = new ArrayList<>();
        String selectSql;
        int numberOfElements = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String countSQL = "SELECT count(VssAvg)FROM OptiTech.tlm.DriveData WHERE VssAvg > 5 AND vehicleId = " + carID + " AND dateMsg > Convert(datetime, \'" + first + "\') AND dateMsg < Convert(datetime, \'" + last + "\');";
            resultSetZaStevilo = statement.executeQuery(countSQL);
            while(resultSetZaStevilo.next()) {
                numberOfElements = resultSetZaStevilo.getInt(1);
            }
            if (numberOfElements > 100) {
                selectSql = "SELECT AVG(VssAvg), CONVERT(varchar(11),dateMsg,23) AS date FROM OptiTech.tlm.DriveData WHERE VssAvg > 5 AND vehicleId = "+carID+" AND dateMsg > Convert(datetime, \'" + first + "\') AND dateMsg < Convert(datetime, \'" + last + "\') GROUP BY CONVERT(varchar(11),dateMsg,23) ORDER BY date ;";

            } else {
                selectSql = "SELECT VssAvg, dateMsg FROM OptiTech.tlm.DriveData WHERE VssAvg > 5 AND vehicleId = " + carID + " AND dateMsg > Convert(datetime, \'" + first + "\') AND dateMsg < Convert(datetime, \'" + last + "\') ORDER BY dateMsg;";

            }
            resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                int vssAvg = resultSet.getInt(1);
                Date dateMsg = resultSet.getDate(2);
                vssAvgSpeedForSelectedCar.add(vssAvg);
                dateForSelectedCar.add(dateMsg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("date", dateForSelectedCar);
        json.put("vssAvg", vssAvgSpeedForSelectedCar);

        return json;
    }

    public static JSONObject rpmAvgSpeedForSelectedCar(int carID, String first, String last) {
        ResultSet resultSet;
        ArrayList<Integer> rpmAvg = new ArrayList<>();
        ArrayList<Date> date = new ArrayList<>();
        ResultSet resultSetZaStevilo;
        String selectSql;
        int numberOfElements = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String countSQL = "SELECT count(VssAvg)FROM OptiTech.tlm.DriveData WHERE VssAvg > 5 AND vehicleId = " + carID + " AND dateMsg > Convert(datetime, \'" + first + "\') AND dateMsg < Convert(datetime, \'" + last + "\');";
            resultSetZaStevilo = statement.executeQuery(countSQL);
            while(resultSetZaStevilo.next()) {
                numberOfElements = resultSetZaStevilo.getInt(1);
            }
            if (numberOfElements > 100) {
                selectSql = "SELECT AVG(rpmAvg), CONVERT(varchar(11),dateMsg,23) AS date FROM OptiTech.tlm.DriveData WHERE VssAvg > 5 AND vehicleId = "+carID+" AND dateMsg > Convert(varchar(11),  \'" + first + "\',23) AND dateMsg < Convert(varchar(11),  \'" + last + "\',23) GROUP BY CONVERT(varchar(11),dateMsg,23) ORDER BY date ;";

            } else {

                selectSql = "select rpmAvg, CONVERT(varchar(11),dateMsg,23) AS date from OptiTech.tlm.DriveData WHERE vehicleId=" + carID + " AND (DrvTime != 0 OR DrvDist > 400) AND dateMsg > Convert(varchar(11), \'" + first + "\',23) AND dateMsg < CONVERT(varchar(11), \'" + last + "\',23) ORDER BY date;";
            }
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                int rpmAvgFromDB = resultSet.getInt(1);
                Date dateMsg = resultSet.getDate(2);
                date.add(dateMsg);
                rpmAvg.add(rpmAvgFromDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("date2", date);
        json.put("rpmAvg", rpmAvg);

        return json;
    }


    public static JSONObject sliderRange(int carID) {
        ArrayList<Date> rangeDates = new ArrayList<>();
        ResultSet resultSet;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "SELECT DISTINCT dateMsg from OptiTech.tlm.DriveData WHERE vehicleId=" + carID + " AND (DrvTime != 0 OR DrvDist > 400) ;";

            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                Date date = resultSet.getDate(1);
                if (!rangeDates.contains(date)) {
                    rangeDates.add(date);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("rangeDates", rangeDates);

        return json;
    }

    public static DriveData getScoreDataForAllCars() {
        ResultSet resultSet;
        DriveData allCarsMaxAndMin = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "SELECT MAX(AvgRpmMax) AS rpmMaxMAX, MIN(AvgRpmMax) AS rpmMaxMIN, MAX(avgRpmAvg) AS rpmAvgMAX, MIN(avgRpmAvg) AS rpmAvgMIN, MAX(avgVssMax) AS vssMaxMAX, MIN(avgVssMax) AS vssMaxMIN, MAX(vssAvg) AS vssAvgMAX, MIN(vssAvg) AS vssAvgMIN, MAX(avgDrvDist) AS drvDistMAX, MIN(avgDrvDist) AS drvDistMIN, MAX(avgDrvTime) AS drvTimeMAX, MIN(avgDrvTime) AS drvTimeMIN, MAX(avgDrvStartStopCnt) AS drvStartStopMAX, MIN(avgDrvStartStopCnt) AS drvStartStopMIN, MAX(avgFuelConsAvg) AS FuelConsMAX, MIN(avgFuelConsAvg) AS FuelConsMIN from (SELECT vehicleId, COUNT(vehicleId) as numberOfInputs, avg(rpmMax) AS AvgRpmMax, MAX(RpmMax) as maxRpmMax, AVG(RpmAvg) as avgRpmAvg, AVG(vssMax) as avgVssMax, AVG(VssAvg) as vssAvg, Avg(DrvDist) as avgDrvDist, avg(DrvTime) as avgDrvTime, AVG(DrvStartStopCnt) AS avgDrvStartStopCnt, AVG(FuelConsAvg) as avgFuelConsAvg from OptiTech.tlm.DriveData WHERE vehicleId IN (SELECT vehicleId FROM ( SELECT prvi.carModelId, carMakerId, carModel, vehicleSubtypeId, status, countryID, carMaker FROM ( SELECT * FROM optitech.reg.carModels WHERE carModelId IN ( SELECT carModelId FROM OptiTech.biz.Vehicles WHERE vehicleId IN ( SELECT DISTINCT vehicleId FROM optitech.tlm.DriveData))) as prvi LEFT JOIN ( SELECT carModelId, countryId, carMaker FROM optitech.reg.carModels LEFT JOIN optitech.reg.CarMakers ON optitech.reg.CarMakers.carMakerId = optitech.reg.CarModels.carMakerId ) AS drugi ON prvi.carModelId = drugi.carModelId ) AS tabela LEFT JOIN OptiTech.biz.Vehicles ON tabela.carModelId = optitech.biz.vehicles.carModelId WHERE engineSize != 0 AND countryID != 'XY' AND vehicleId != '1357') GROUP BY vehicleId)  AS prva WHERE avgFuelConsAvg < 8900;";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {

                allCarsMaxAndMin = new DriveData(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getInt(11),
                        resultSet.getInt(12),
                        resultSet.getInt(13),
                        resultSet.getInt(14),
                        resultSet.getInt(15),
                        resultSet.getInt(16)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCarsMaxAndMin;
    }

    public static VehicleForScore getScoreDataForSelectedCar(int carId) {
        ResultSet resultSet;
        VehicleForScore vehicle = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "SELECT AVG(rpmMax) AS AvgRpmMax, AVG(RpmAvg) AS avgRpmAvg, AVG(vssMax) AS avgVssMax, AVG(VssAvg) as vssAvg, Avg(DrvDist) as avgDrvDist, avg(DrvTime) as avgDrvTime, AVG(DrvStartStopCnt) AS avgDrvStartStopCnt, AVG(FuelConsAvg) as avgFuelConsAvg from OptiTech.tlm.DriveData WHERE VehicleId =" + carId + ";";
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next()) {
                vehicle = new VehicleForScore(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }


    public static int getTotalScoreForSelectedCar(int carId) {
        ResultSet resultSet;
        int stevilo = 0;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "    SELECT  AVG(ScoreTotal) FROM  Optitech.tlm.DriveData WHERE vehicleId =" + carId + ";";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                stevilo = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stevilo;
    }

    //EMPTY FOR COPYING
    public static void neKliciTega() {
        ResultSet resultSet;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            String selectSql = "";
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

