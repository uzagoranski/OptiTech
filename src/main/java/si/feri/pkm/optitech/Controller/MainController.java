package si.feri.pkm.optitech.Controller;

import org.json.JSONObject;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import si.feri.pkm.optitech.Database.*;
import si.feri.pkm.optitech.Entity.Drive;
import si.feri.pkm.optitech.Entity.FuelType;
import si.feri.pkm.optitech.Entity.Vehicle;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Controller
public class MainController {

    static String user;
    static String email;
    static String name;
    static String image;
    static String authority;
    static String id;
    static String locale;
    static boolean verified_email;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model, OAuth2Authentication authentication) throws Exception {

        if (authentication != null) {
            LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();

            email = (String) properties.get("email");
            name = (String) properties.get("name");
            image = (String) properties.get("picture");
            user = name;

        } else {
            user = "anonymousUser";
        }
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("image", image);

        return "index";
    }

    @RequestMapping(value = {"/account"}, method = RequestMethod.GET)
    public String account(Model model, OAuth2Authentication authentication) throws Exception {

        if (authentication != null) {
            LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();

            email = (String) properties.get("email");
            name = (String) properties.get("name");
            image = (String) properties.get("picture");
            authority = (String) properties.get("authority");
            id = (String) properties.get("id");
            locale = (String) properties.get("locale");
            verified_email = (boolean) properties.get("verified_email");
            user = name;

        } else {
            user = "anonymousUser";
        }
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("image", image);
        model.addAttribute("authority", authority);
        model.addAttribute("id", id);
        model.addAttribute("locale", locale);
        model.addAttribute("verified_email", verified_email);

        return "account";
    }

    @RequestMapping(value = {"/carsList"}, method = RequestMethod.GET)
    public String seznamVozil(Model model, @RequestParam(value = "id", required = false) Integer id, OAuth2Authentication authentication) throws ParseException, IOException {

        if (authentication != null) {
            LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();

            email = (String) properties.get("email");
            name = (String) properties.get("name");
            image = (String) properties.get("picture");
            user = name;

        } else {
            user = "anonymousUser";
        }
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("image", image);


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

        model.addAttribute("idCar", id);
        model.addAttribute("slika", linkSlika);
        model.addAttribute("fuel", fuel);
        model.addAttribute("drive", drive);
        model.addAttribute("vehicle", vehicle);

        return "carsList";
    }

    @RequestMapping(value = {"/carsList"}, method = RequestMethod.POST)
    public String seznamVozilPost(Model model, @RequestParam(value = "id", required = false) Integer id, OAuth2Authentication authentication) throws ParseException, IOException {

        if (authentication != null) {
            LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();

            email = (String) properties.get("email");
            name = (String) properties.get("name");
            image = (String) properties.get("picture");
            user = name;

        } else {
            user = "anonymousUser";
        }
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("image", image);


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

        return "carsList";
    }

    @RequestMapping(value = {"/carDetails"}, method = RequestMethod.GET)
    public String carDetails(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "sliderValue", required = false) String sliderValue, OAuth2Authentication authentication) throws ParseException, IOException {

        if (authentication != null) {
            LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();

            email = (String) properties.get("email");
            name = (String) properties.get("name");
            image = (String) properties.get("picture");
            user = name;

        } else {
            user = "anonymousUser";
        }
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("image", image);


        Vehicle vehicle = SQLCarsDatabase.getSelectedVehicle(id);
        String linkImage = "";
        String fuel = "";
        String drive = "";

        JSONObject jsonSpeed = SQLDriveData.vssAvgSpeedForSelectedCar(id, "2017-01-01", "2020-01-01");
        JSONObject sliderRange = SQLDriveData.sliderRange(id);

        if (vehicle != null) {
            linkImage = SQLCarImage.getCarImage(id);
            fuel = loadFuel(vehicle);
            drive = loadDrive(vehicle);
        }

        model.addAttribute("idCar", id);
        model.addAttribute("sliderValue", sliderValue);
        model.addAttribute("linkImage", linkImage);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("fuel", fuel);
        model.addAttribute("drive", drive);
        model.addAttribute("jsonSpeed", jsonSpeed);
        model.addAttribute("sliderRange", sliderRange);
        model.addAttribute("user", user);

        return "carDetails";
    }

    @RequestMapping(value = {"/carDetails"}, method = RequestMethod.POST)
    public String carDetailsPost(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "sliderValue", required = false) String sliderValue, OAuth2Authentication authentication) throws ParseException, IOException {

        if (authentication != null) {
            LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();

            email = (String) properties.get("email");
            name = (String) properties.get("name");
            image = (String) properties.get("picture");
            user = name;

        } else {
            user = "anonymousUser";
        }
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("image", image);


        String[] dates = sliderValue.split(",");
        String from = dates[0];
        String to = dates[1];

        Vehicle vehicle = SQLCarsDatabase.getSelectedVehicle(id);
        String linkImage = "";
        String fuel = "";
        String drive = "";

        JSONObject jsonSpeed = SQLDriveData.vssAvgSpeedForSelectedCar(id, from, to);
        JSONObject sliderRange = SQLDriveData.sliderRange(id);

        if (vehicle != null) {
            linkImage = SQLCarImage.getCarImage(id);
            fuel = loadFuel(vehicle);
            drive = loadDrive(vehicle);
        }

        model.addAttribute("idCar", id);
        model.addAttribute("sliderValue", sliderValue);
        model.addAttribute("linkImage", linkImage);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("fuel", fuel);
        model.addAttribute("drive", drive);
        model.addAttribute("jsonSpeed", jsonSpeed);
        model.addAttribute("sliderRange", sliderRange);
        return "carDetails";
    }

    @RequestMapping(value = {"/comparison"}, method = RequestMethod.GET)
    public String comparison(Model model, OAuth2Authentication authentication) throws IOException {

        if (authentication != null) {
            LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();

            email = (String) properties.get("email");
            name = (String) properties.get("name");
            image = (String) properties.get("picture");
            user = name;

        } else {
            user = "anonymousUser";
        }
        model.addAttribute("user", user);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("image", image);


        ArrayList<Vehicle> vehicles = SQLCarsDatabase.getInsertedVehicles();

        model.addAttribute("vehicles", vehicles);

        return "comparison";
    }

    @RequestMapping(value = {"/statsAI"}, method = RequestMethod.GET)
    public String statsAI(Model model, OAuth2Authentication authentication) throws IOException {

        if (authentication != null) {
            LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();

            email = (String) properties.get("email");
            name = (String) properties.get("name");
            image = (String) properties.get("picture");
            user = name;

        } else {
            user = "anonymousUser";
        }

        model.addAttribute("user", user);
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        model.addAttribute("image", image);

        ArrayList<Vehicle> vehicles = SQLCarsDatabase.getInsertedVehicles();

        model.addAttribute("vehicles", vehicles);

        return "statsAI";
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

    public static String loadDrive(Vehicle vehicle) {

        String drive = "";
        ArrayList<Drive> drives = SQLDrive.getAllDriveTypes();

        for (Drive d : drives) {
            if (d.getId() == vehicle.getFuelTypeId()) {
                drive = d.getType();
            }
        }
        return drive;
    }
}