package si.feri.pkm.optitech.Controller;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
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

import java.io.*;
import java.text.ParseException;
import java.util.*;

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
    static int count = 0;

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

        JSONObject err = SQLCarsDatabase.getErrorsOnSelectedCar(id, "2017-01-01", "2020-01-01");

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
        JSONObject rpmAvg = SQLDriveData.rpmAvgSpeedForSelectedCar(id, from, to);
        JSONObject error = SQLCarsDatabase.getErrorsOnSelectedCar(id, from, to);
        jsonArray.put(vssAvg);
        jsonArray.put(rpmAvg);
        jsonArray.put(error);
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

    @RequestMapping(value = {"/trip"}, method = RequestMethod.GET)
    public String trip(Model model, OAuth2Authentication authentication) throws Exception {

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

        return "trip";
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

    // Naive Bayes AI section
    //             _____     _____ ______ _____ _______ _____ ____  _   _
    //       /\   |_   _|   / ____|  ____/ ____|__   __|_   _/ __ \| \ | |
    //      /  \    | |    | (___ | |__ | |       | |    | || |  | |  \| |
    //     / /\ \   | |     \___ \|  __|| |       | |    | || |  | | . ` |
    //    / ____ \ _| |_    ____) | |___| |____   | |   _| || |__| | |\  |
    //   /_/    \_\_____|  |_____/|______\_____|  |_|  |_____\____/|_| \_|
    //
    //

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
        model.addAttribute("error", "");

        return "errorPrediction";
    }

    @RequestMapping(value = {"/statsAI"}, method = RequestMethod.POST)
    public ResponseEntity<?> statsAIPost(Model model, @RequestParam(value = "id") int id, OAuth2Authentication authentication) throws ParseException, IOException {
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

    private static void overrideCSV(String imeDatoteke, String[] vnos) throws IOException {

        //Branje s pomocjo knjiznice CSVReader
        CSVReader reader = new CSVReader(new FileReader(imeDatoteke), ',', '"', 0);

        List<String[]> csvBody = reader.readAll();

        csvBody.get(1)[0] = vnos[0];
        csvBody.get(1)[1] = vnos[1];
        csvBody.get(1)[2] = vnos[2];
        csvBody.get(1)[3] = vnos[3];
        csvBody.get(1)[4] = vnos[4];
        csvBody.get(1)[5] = vnos[5];

        reader.close();

        //Pisanje s pomocjo knjiznice CSVWriter
        CSVWriter writer = new CSVWriter(new FileWriter(imeDatoteke), ',');

        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }

    //Instanciramo Lista za shranjevanje vrednosti iz testne csv datoteke ter polje za hranjenje atributov
    private static List<String[]> glavniUcna = new ArrayList<>();
    private static List<String[]> pomozniUcna = new ArrayList<>();
    private static String[] atributi;

    //Metoda za branje učne množice podatkov iz CSV datoteke
    private static void branjeUcneDatoteke(String imeDatoteke) throws IOException {

        //Branje s pomocjo knjiznice CSVReader
        CSVReader reader = new CSVReader(new FileReader(imeDatoteke), ',', '"', 0);

        //Dodajanje vseh vrednosti na glavni seznam
        glavniUcna = reader.readAll();
        atributi = glavniUcna.get(0);
        reader.close();

        //Dodajanje vseh vrednosti na pomozni seznam
        for (int i = 1; i < glavniUcna.size(); i++) {
            String[] row = glavniUcna.get(i);
            pomozniUcna.add(row);
        }
    }

    //Instanciramo Lista za shranjevanje vrednosti iz testne csv datoteke
    private static List<String[]> glavniTestna = new ArrayList<>();
    private static List<String[]> pomozniTestna = new ArrayList<>();

    private static void branjeTestneDatoteke(String imeDatoteke) throws Exception {

        //Branje s pomocjo knjiznice CSVReader
        CSVReader reader = new CSVReader(new FileReader(imeDatoteke), ',', '"', 0);

        //Dodajanje vseh vrednosti na glavni seznam
        glavniTestna = reader.readAll();
        reader.close();

        //Dodajanje vseh vrednosti na pomozni seznam
        for (int i = 1; i < glavniTestna.size(); i++) {
            String[] row = glavniTestna.get(i);
            pomozniTestna.add(row);
        }
    }

    //Instanciramo ArrayListe za shranjevanje koncnih razredov, stevila razredov in verjetnosti ter spremenljivko kot stevec
    private static ArrayList<String> razredi = new ArrayList<>();
    private static ArrayList<Integer> st_razredov = new ArrayList<>();
    private static ArrayList<Double> verjetnost = new ArrayList<>();
    private static int steviloRazredov;

    //Implementirana metoda klasifikacije
    private static void klasifikator() {

        //Prebiranje stevila vrstic in stolpcev
        final int steviloVrstic = glavniUcna.size();
        final int steviloStolpcev = pomozniUcna.get(0).length;

        //Sortiranje & primerjanje elementov s pomocjo Collections.sort
        Collections.sort(pomozniUcna, new Comparator<String[]>() {
            public int compare(String[] strings, String[] otherStrings) {
                return strings[steviloStolpcev - 1].compareTo(otherStrings[steviloStolpcev - 1]);
            }
        });

        //Inicializacija spremenljivk, potrebnih za izracune
        String razred = pomozniUcna.get(0)[steviloStolpcev - 1];
        int stevilo_razredov = 0;
        int st = 1;

        for (int i = 0; i < steviloVrstic - 1; i++) {

            //Stetje stevila razredov
            if (razred.equals(pomozniUcna.get(i)[steviloStolpcev - 1])) {
                stevilo_razredov++;
            } else {
                razredi.add(razred);
                st_razredov.add(stevilo_razredov);
                razred = pomozniUcna.get(i)[steviloStolpcev - 1];
                stevilo_razredov = 1;
                st++;
            }
        }

        //Dodajanje izracunanih vrednosti
        razredi.add(razred);
        st_razredov.add(stevilo_razredov);
        steviloRazredov = st;

        //Racunanje verjetnosti za napoved
        for (int i = 0; i < razredi.size(); i++) {
            verjetnost.add(st_razredov.get(i) / ((double) steviloVrstic - 1));
        }
    }

    //Instanciramo ArrayLista za shranjevanje vrednosti atributov in stevila atributov
    private static ArrayList<String> atribut = new ArrayList<>();
    private static ArrayList<Integer> steviloAtributov = new ArrayList<>();

    //Metoda za branje vseh atributov csv datoteke
    private static void atributi() {

        //Inicializacija spremenljivk za stevilo stolpcev, vrstic, atributov in vrednost atributov
        int steviloStolpcev = glavniUcna.get(0).length;
        final int steviloVrstic = glavniUcna.size();
        int st_atr;
        String atr;

        for (int p = 0; p < steviloStolpcev - 1; p++) {

            final int o = p;
            Collections.sort(pomozniUcna, new Comparator<String[]>() {
                public int compare(String[] strings, String[] otherStrings) {
                    return strings[o].compareTo(otherStrings[o]);
                }
            });

            atr = pomozniUcna.get(0)[p];
            st_atr = 0;
            int st = 1;

            for (int k = 0; k < steviloVrstic - 1; k++) {

                if (atr.equals(pomozniUcna.get(k)[p])) {
                    st_atr++;
                } else {
                    atribut.add(atr);
                    steviloAtributov.add(st_atr);

                    atr = pomozniUcna.get(k)[p];
                    st_atr = 1;
                    st++;
                }
            }
            atribut.add(atr);
            steviloAtributov.add(st_atr);
        }
    }

    //Inicializiramo tridimenzionalno polje za shranjevanje vrednosti verjetnost atributov
    private static Double[][][] verjetnostAtributa;

    //Metoda za racunanje verjetnosti posameznega atributa
    private static void verjetnostiAtributa() {

        //Inicializacija spremenljivk za stevilo stolpcev in vrstic
        final int steviloStolpcev = glavniUcna.get(0).length;
        final int steviloVrstic = glavniUcna.size();

        //Deklaracija tridimenzionalnega polja
        verjetnostAtributa = new Double[steviloStolpcev][atribut.size()][razredi.size()];

        //Trojna vgnezdena for zanka za zapolnjevanje tridimenzionalnega polja z vrednostmi atributov
        for (int i = 0; i < razredi.size(); i++) {
            for (int j = 0; j < steviloStolpcev - 1; j++) {
                for (int k = 0; k < atribut.size(); k++) {
                    int steviloAtributov = 0;
                    for (int l = 0; l < steviloVrstic - 1; l++) {
                        if ((atribut.get(k).equals(pomozniUcna.get(l)[j])) && (razredi.get(i).equals(pomozniUcna.get(l)[steviloStolpcev - 1]))) {
                            steviloAtributov++;
                        }
                    }
                    //Dodajanje verjetnosti v tridimenzionalno polje
                    verjetnostAtributa[j][k][i] = ((double) steviloAtributov) / (double) st_razredov.get(i);
                }
            }
        }
    }

    //Instanciramo ArrayList za shranjevanje vrednosti ocen
    private static ArrayList<String> ocene = new ArrayList<>();

    //Metoda za izracun vrednosti
    private static void izracun() {

        double[] vredn = new double[steviloRazredov];
        int vrstice_testna = glavniTestna.size();
        int stolpci_testna = pomozniTestna.get(0).length;
        double a;

        for (int i = 0; i < vrstice_testna - 1; i++) {
            for (int o = 0; o < steviloRazredov; o++) {
                vredn[o] = 1.0;
            }
            for (int j = 0; j < stolpci_testna - 1; j++) {
                for (int k = 0; k < atribut.size() - 1; k++) {
                    if (atribut.get(k).equals(pomozniTestna.get(i)[j])) {
                        for (int l = 0; l < steviloRazredov; l++) {
                            a = verjetnostAtributa[j][k][l];
                            vredn[l] *= a;
                        }
                    }
                }
            }
            double max = 0;
            int maxx = 0;
            for (int m = 0; m < steviloRazredov; m++) {
                vredn[m] = vredn[m] * verjetnost.get(m);
                if (vredn[m] > max) {
                    max = vredn[m];
                    maxx = m;
                }
            }
            ocene.add(razredi.get(maxx));
        }
    }

    @RequestMapping(value = {"/errorPrediction"}, method = RequestMethod.POST)
    public static void errorPrediction(Model model, @RequestParam(value = "idInput") int id, @RequestParam(value = "timeInput") String time, @RequestParam(value = "distanceInput") String distance, @RequestParam(value = "speedInput") String speed, @RequestParam(value = "RPMInput") String RPM, @RequestParam(value = "ODODistanceInput") String mileage, OAuth2Authentication authentication) throws Exception {

        String[] seznam = new String[6];
        seznam[0] = time;
        seznam[1] = distance;
        seznam[2] = speed;
        seznam[3] = RPM;
        seznam[4] = mileage;
        seznam[5] = "Fuel Volume Regulator Control Circuit Low";

        overrideCSV("errorPrediction_testna.csv", seznam);

        branjeUcneDatoteke("errorPrediction_ucna.csv");

        branjeTestneDatoteke("errorPrediction_testna.csv");

        //Klic vseh metod za izracune in prikaz matrike zmede
        klasifikator();
        atributi();
        verjetnostiAtributa();
        izracun();

        model.addAttribute("error", ocene.get(count));
        count++;
    }
}