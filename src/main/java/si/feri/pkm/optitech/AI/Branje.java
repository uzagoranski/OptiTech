package si.feri.pkm.optitech.AI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Branje {


  public ArrayList<ArrayList<String>> beri(String ime) {

	String csvFile = ime;
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	ArrayList <ArrayList<String>> parametri=new ArrayList<ArrayList<String>>();

	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

		        
			String[] vrednosti = line.split(cvsSplitBy);
			int dolzina=vrednosti.length;
			ArrayList<String> vrstica=new ArrayList<String>();
			for( int i=0; i<dolzina; i++){
				vrstica.add(vrednosti[i]);
			}
			parametri.add(vrstica);
		}
		

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	return parametri;
  }

}
