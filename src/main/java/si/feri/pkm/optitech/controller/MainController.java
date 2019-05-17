package si.feri.pkm.optitech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import si.feri.pkm.optitech.Database.SQLCarsDatabase;
import si.feri.pkm.optitech.Database.SQLDatabaseConnection;
import si.feri.pkm.optitech.Entity.Vehicle;

import java.sql.ResultSet;
import java.util.ArrayList;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {


        System.out.println();

        return "index";
    }

    @RequestMapping(value = {"/carsList"}, method = RequestMethod.GET)
    public String seznamVozil(Model model) {

        // V vehicles maš hranjene vse avte, ki jih dobim nazaj tipa Vehicle,
        // pol pa z getterji pa setterji pridobivaj podatke ki jih rabiš za izpis.
        ArrayList<Vehicle> vehicles = SQLCarsDatabase.getInsertedVehicles();

        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println(vehicles.get(i));
        }

        model.addAttribute("vehicles", vehicles);

        return "carsList";

    }
}
