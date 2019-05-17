package si.feri.pkm.optitech.Entity;

public class Vehicle {
    long carModelId;
    long carMakerId;
    String carModel;
    int vehicleSubtypeId;
    String countryId;
    String carMaker;

    public Vehicle(long carModelId, long carMakerId, String carModel, int vehicleSubtypeId, String countryId, String carMaker) {
        this.carModelId = carModelId;
        this.carMakerId = carMakerId;
        this.carModel = carModel;
        this.vehicleSubtypeId = vehicleSubtypeId;
        this.countryId = countryId;
        this.carMaker = carMaker;
    }

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

    @Override
    public String toString() {
        return "carModel: " + carModel + ", ID: " + carModelId + "\nCarMaker: " + carMaker + ", ID: " + carMakerId + "\n-----------";
    }
}
