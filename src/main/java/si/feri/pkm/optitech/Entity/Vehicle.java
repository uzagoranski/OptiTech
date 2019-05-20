package si.feri.pkm.optitech.Entity;

import si.feri.pkm.optitech.Database.SQLCarsDatabase;

import java.sql.Date;

public class Vehicle {
    long carModelId;
    long carMakerId;
    String carModel;
    int vehicleSubtypeId;
    String countryId;
    String carMaker;
    int vehicleId;
    String vin;
    String vehicleTitle;
    String regNumber;
    int carModelYear;
    int fuelTypeId;
    int drivenWheelsId;
    int engineSize;
    int enginePower;
    Date dateRegStarted;
    Date dateRegEnd;

    public Vehicle(){

    }

    public Vehicle(long carModelId, long carMakerId, String carModel, int vehicleSubtypeId, String countryId, String carMaker, int vehicleId, String vin, String vehicleTitle, String regNumber, int carModelYear, int fuelTypeId, int drivenWheelsId, int engineSize, int enginePower, Date dateRegStarted, Date dateRegEnd) {
        this.carModelId = carModelId;
        this.carMakerId = carMakerId;
        this.carModel = carModel;
        this.vehicleSubtypeId = vehicleSubtypeId;
        this.countryId = countryId;
        this.carMaker = carMaker;
        this.vehicleId = vehicleId;
        this.vin = vin;
        this.vehicleTitle = vehicleTitle;
        this.regNumber = regNumber;
        this.carModelYear = carModelYear;
        this.fuelTypeId = fuelTypeId;
        this.drivenWheelsId = drivenWheelsId;
        this.engineSize = engineSize;
        this.enginePower = enginePower;
        this.dateRegStarted = dateRegStarted;
        this.dateRegEnd = dateRegEnd;
    }

//    public Vehicle getSelectedVehicle(int id){
//
//        return ;
//    }

    public long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(long carModelId) {
        this.carModelId = carModelId;
    }

    public long getCarMakerId() {
        return carMakerId;
    }

    public void setCarMakerId(long carMakerId) {
        this.carMakerId = carMakerId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getVehicleSubtypeId() {
        return vehicleSubtypeId;
    }

    public void setVehicleSubtypeId(int vehicleSubtypeId) {
        this.vehicleSubtypeId = vehicleSubtypeId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCarMaker() {
        return carMaker;
    }

    public void setCarMaker(String carMaker) {
        this.carMaker = carMaker;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVehicleTitle() {
        return vehicleTitle;
    }

    public void setVehicleTitle(String vehicleTitle) {
        this.vehicleTitle = vehicleTitle;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getCarModelYear() {
        return carModelYear;
    }

    public void setCarModelYear(int carModelYear) {
        this.carModelYear = carModelYear;
    }

    public int getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(int fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public int getDrivenWheelsId() {
        return drivenWheelsId;
    }

    public void setDrivenWheelsId(int drivenWheelsId) {
        this.drivenWheelsId = drivenWheelsId;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public Date getDateRegStarted() {
        return dateRegStarted;
    }

    public void setDateRegStarted(Date dateRegStarted) {
        this.dateRegStarted = dateRegStarted;
    }

    public Date getDateRegEnd() {
        return dateRegEnd;
    }

    public void setDateRegEnd(Date dateRegEnd) {
        this.dateRegEnd = dateRegEnd;
    }

    @Override
    public String toString() {
        return "Maker id:"+carMakerId+"\nMaker:"+carMaker+"\nModelId:"+carModelId+"\nModel:"+carModel+"\nCountryId:"+countryId+"\nVehicleSubtype:"+vehicleSubtypeId+"\nModelYear:"+carModelYear+"\nDrivenWheels:"+drivenWheelsId+"\nEngine size:"+engineSize+"\nEngine power:"+enginePower+"\n"+dateRegStarted+"\n"+"\n";

    }
}
