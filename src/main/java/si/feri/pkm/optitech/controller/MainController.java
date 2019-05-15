package si.feri.pkm.optitech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import si.feri.pkm.optitech.Database.SQLDatabaseConnection;

import java.sql.ResultSet;
import java.util.ArrayList;

@Controller
public class MainController {

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {


        System.out.println();

        return "index";
    }

    @RequestMapping(value = {"/carsList"}, method = RequestMethod.GET)
    public String seznamVozil(Model model) {
        ArrayList<String> vehicles = SQLDatabaseConnection.getInsertedVehicles();

        for(int i =0; i<vehicles.size();i++){
            System.out.println(vehicles.get(i));
        }

        model.addAttribute("vehicles", vehicles);

        return "carsList";

    }
}
