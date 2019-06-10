package si.feri.pkm.optitech.Controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import si.feri.pkm.optitech.Database.*;
import si.feri.pkm.optitech.Entity.*;
import si.feri.pkm.optitech.Entity.Error;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static si.feri.pkm.optitech.Database.SQLCarsDatabase.getErrorsOnSelectedCar;

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

        ArrayList<Error> err=  SQLCarsDatabase.getErrorsOnSelectedCar(id,"2017-01-01", "2020-01-01");

        Vehicle vehicle = SQLCarsDatabase.getSelectedVehicle(id);
        String linkImage = "";
        String fuel = "";
        String drive = "";


        DriveData dd = SQLDriveData.getScoreDataForAllCars();
        VehicleForScore vehicleForScore = SQLDriveData.getScoreDataForSelectedCar(id);

        int score = vehicleForScore.calculateScore(
                (int) Math.round(100 - dd.koefRpmMax() * (vehicleForScore.getRpmMax() - dd.getRpmMaxMIN())),
                (int) Math.round(100 - dd.koefRpmAvg() * (vehicleForScore.getRpmAvg() - dd.getRpmAvgMIN())),
                (int) Math.round(100 - dd.koefVssMax() * (vehicleForScore.getVssMax() - dd.getVssMaxMIN())),
                (int) Math.round(100 - dd.koefVssAvg() * (vehicleForScore.getVssAvg() - dd.getVssAvgMIN())),
                (int) Math.round(100 - dd.koefDrvDist() * (vehicleForScore.getDrvDist() - dd.getDrvDistMIN())),
                (int) Math.round(100 - dd.koefDrvTime() * (vehicleForScore.getDrvTime() - dd.getDrvTimeMIN())),
                (int) Math.round(100 - dd.koefDrvStartStopCnt() * (vehicleForScore.getDrvStartStopCnt() - dd.getDrvStartStopMIN())),
                (int) Math.round(100 - dd.koefFuelCons() * (vehicleForScore.getFuelCons() - dd.getFuelConsMIN())),
                SQLDriveData.getTotalScoreForSelectedCar(id)
        );

        JSONObject jsonSpeed = SQLDriveData.vssAvgSpeedForSelectedCar(id, "2017-01-01", "2020-01-01");
        JSONObject jsonRpm = SQLDriveData.rpmAvgSpeedForSelectedCar(id, "2017-01-01", "2020-01-01");
        JSONObject sliderRange = SQLDriveData.sliderRange(id);

        if (vehicle != null) {
            linkImage = SQLCarImage.getCarImage(id);
            fuel = loadFuel(vehicle);
            drive = loadDrive(vehicle);
        }
        String color;
        if (score > 34 && score < 67)
            color = "rgba(248, 148, 6, 1)";
        else if (score > 66) {
            color = "rgba(38, 194, 129, 1)";
        } else {
            color = "rgba(214, 69, 65, 1)";
        }

        model.addAttribute("errors", err);
        model.addAttribute("color", color);
        model.addAttribute("score", score);
        model.addAttribute("idCar", id);
        model.addAttribute("sliderValue", sliderValue);
        model.addAttribute("linkImage", linkImage);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("fuel", fuel);
        model.addAttribute("drive", drive);
        model.addAttribute("jsonSpeed", jsonSpeed);
        model.addAttribute("jsonRpm", jsonRpm);
        model.addAttribute("sliderRange", sliderRange);
        model.addAttribute("user", user);

        return "carDetails";
    }


    @RequestMapping(value = {"/carDetails"}, method = RequestMethod.POST)
    public ResponseEntity<?> carDetailsPost(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "sliderValue", required = false) String sliderValue, OAuth2Authentication authentication) throws ParseException, IOException {
        String[] dates = sliderValue.split(",");
        String from = dates[0];
        String to = dates[1];

        JSONArray jsonArray = new JSONArray();
        JSONObject vssAvg = SQLDriveData.vssAvgSpeedForSelectedCar(id, from, to);
        JSONObject rpmAvg = SQLDriveData.rpmAvgSpeedForSelectedCar(id,from, to);
        jsonArray.put(vssAvg);
        jsonArray.put(rpmAvg);
        return ResponseEntity.ok(jsonArray.toString());
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

    @RequestMapping(value = {"/errorPrediction"}, method = RequestMethod.GET)
    public String errorPrediction(Model model, OAuth2Authentication authentication) throws IOException {

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

        return "errorPrediction";
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

    @RequestMapping(value = {"/statsAI"}, method = RequestMethod.POST)
    public ResponseEntity<?> statsAIPost(Model model, @RequestParam(value = "id") int id , OAuth2Authentication authentication) throws ParseException, IOException {
        JSONObject json = new JSONObject();
        Vehicle v = SQLCarsDatabase.getSelectedVehicle(id);
        json.put("year", v.getCarModelYear());
        json.put("fuel", v.getFuelType());
        json.put("enginePower", v.getEnginePower());
        json.put("engineSize", v.getEngineSize());
        json.put("drive", v.getDrivenWheels());
        json.put("id", v.getVehicleId());
        return ResponseEntity.ok(json.toString());
    }
}