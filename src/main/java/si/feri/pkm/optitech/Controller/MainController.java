package si.feri.pkm.optitech.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import si.feri.pkm.optitech.Database.SQLCarsDatabase;
import si.feri.pkm.optitech.Database.SQLFuelType;
import si.feri.pkm.optitech.Entity.FuelType;
import si.feri.pkm.optitech.Entity.Vehicle;

import java.text.ParseException;
import java.util.ArrayList;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {


        System.out.println();

        return "index";
    }

    @RequestMapping(value = {"/carsList"}, method = RequestMethod.GET)
    public String seznamVozil(Model model, @RequestParam(value="id", required = false) Integer id) throws ParseException {

        // V vehicles maš hranjene vse avte, ki jih dobim nazaj tipa Vehicle,
        // pol pa z getterji pa setterji pridobivaj podatke ki jih rabiš za izpis.
        ArrayList<Vehicle> vehicles = SQLCarsDatabase.getInsertedVehicles();

        model.addAttribute("vehicles", vehicles);

        Vehicle vehicle = null;

        if(id != null){
             vehicle = SQLCarsDatabase.getSelectedVehicle(id);
        }

        //tu not imaš VSE IDje in Imena če je bencinar / dizel / hibrid...
        ArrayList<FuelType> fuelTypes= SQLFuelType.getAllFuelTypes();

        //Tu je pravo Gorivo, ki ga uporablja avto
        String fuel ="";
        if(vehicle != null) {

            for (FuelType f : fuelTypes) {
                if (f.getId() == vehicle.getFuelTypeId()) {
                    fuel = f.getNaziv();
                }
            }
        }
        model.addAttribute("fuel", fuel);
        model.addAttribute("vehicle", vehicle);

        return "carsList";

    }

    @RequestMapping(value = {"/carDetails"}, method = RequestMethod.GET)
    public String carDetails(Model model, @RequestParam(value="id") int id) throws ParseException {


        return "carDetails";
    }

}
