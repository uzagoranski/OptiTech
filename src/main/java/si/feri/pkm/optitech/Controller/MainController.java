package si.feri.pkm.optitech.Controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import si.feri.pkm.optitech.Database.*;
import si.feri.pkm.optitech.Entity.Drive;
import si.feri.pkm.optitech.Entity.FuelType;
import si.feri.pkm.optitech.Entity.Vehicle;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;

@Controller
public class MainController {

    static String user;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model, Principal principal) {

        if(principal != null) {
            user = principal.getName();
        } else {
            user = "anonymousUser";
        }

        model.addAttribute("user", user);

        return "index";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(Model model) {

        user = "anonymousUser";

        model.addAttribute("user", user);

        return "index";
    }

    @RequestMapping(value = {"/carsList"}, method = RequestMethod.GET)
    public String seznamVozil(Model model, @RequestParam(value = "id", required = false) Integer id, Principal principal) throws ParseException {

        if(principal != null) {
            user = principal.getName();
        } else {
            user = "anonymousUser";
        }

        // V vehicles maš hranjene vse avte, ki jih dobim nazaj tipa Vehicle,
        // pol pa z getterji pa setterji pridobivaj podatke ki jih rabiš za izpis.
        ArrayList<Vehicle> vehicles = SQLCarsDatabase.getInsertedVehicles();

        model.addAttribute("vehicles", vehicles);

        Vehicle vehicle = null;
        String linkSlika = "";
        String fuel = "";
        String drive = "";

        if (id == null) {
            id = 217;
        }

        vehicle = SQLCarsDatabase.getSelectedVehicle(id);

        if (vehicle != null) {
            linkSlika = SQLCarImage.getCarImage(id);
            fuel = loadFuel(vehicle);
            drive = loadDrive(vehicle);
        }

        model.addAttribute("slika", linkSlika);
        model.addAttribute("fuel", fuel);
        model.addAttribute("drive", drive);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("user", user);

        return "carsList";
    }

    @RequestMapping(value = {"/carDetails"}, method = RequestMethod.GET)
    public String carDetails(Model model, @RequestParam(value = "id") int id, Principal principal) throws ParseException {

        if(principal != null) {
            user = principal.getName();
        } else {
            user = "anonymousUser";
        }

        Vehicle vehicle = SQLCarsDatabase.getSelectedVehicle(id);
        String linkImage = "";
        String fuel = "";
        String drive = "";

        JSONObject jsonSpeed = SQLDriveData.vssAvgSpeedForSelectedCar(id);
        JSONObject sliderRange = SQLDriveData.sliderRange(id);

        if(vehicle != null){
             linkImage = SQLCarImage.getCarImage(id);
             fuel = loadFuel(vehicle);
             drive = loadDrive(vehicle);
        }

        model.addAttribute("linkImage", linkImage);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("fuel", fuel);
        model.addAttribute("drive", drive);
        model.addAttribute("jsonSpeed", jsonSpeed);
        model.addAttribute("sliderRange", sliderRange);
        model.addAttribute("user", user);

        return "carDetails";
    }

    public static String loadFuel(Vehicle vehicle) {

        String fuel = "";
        ArrayList<FuelType> fuelTypes = SQLFuelType.getAllFuelTypes();

            for (FuelType f : fuelTypes) {
                if (f.getId() == vehicle.getFuelTypeId()) {
                    fuel = f.getType();
                }
            }
        return fuel;
    }

    public static String loadDrive(Vehicle vehicle){

        String drive = "";
        ArrayList<Drive> drives = SQLDrive.getAllDriveTypes();

            for (Drive d : drives) {
                if (d.getId() == vehicle.getFuelTypeId()) {
                    drive = d.getType();
                }
            }
        return drive;
    }

    //public String getJSON()
}