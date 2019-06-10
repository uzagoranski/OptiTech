package si.feri.pkm.optitech.AI;

import java.util.ArrayList;

public class Vozlisce {

	public Vozlisce oce;
	public ArrayList <Vozlisce> otroci;
	public String vrednost;
	public String parameter;
	public double entropija;
	public ArrayList<ArrayList<String>> vrednosti;
	public ArrayList<ArrayList<String>> razlicne_vrednosti;
	public ArrayList<String> parametri;

	public Vozlisce(){
		otroci=new ArrayList<Vozlisce>();
		vrednosti=new ArrayList<ArrayList<String>>();
		entropija=1.0;
		razlicne_vrednosti= new ArrayList<ArrayList<String>>();
		parametri= new ArrayList<String>();
		
	}

    public void izpisi() {
        izpisi("", true);
    }

	public void izpisi(String zacetek, boolean zadnji) {
		System.out.println(zacetek + (zadnji ? "--- " : "--- ") + parameter +": "+ vrednost);

		for (int i = 0; i < otroci.size() - 1; i++) {
			otroci.get(i).izpisi(zacetek + (zadnji ? "    " : "|   "), false);
		}
		if (!otroci.isEmpty()) {
			otroci.get(otroci.size() - 1).izpisi(zacetek + (zadnji ? "    " : "|   "), true);
		}
	}

}