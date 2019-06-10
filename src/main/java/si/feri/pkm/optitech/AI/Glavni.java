package si.feri.pkm.optitech.AI;

import java.util.ArrayList;

import org.apache.commons.cli.*;

public class Glavni {

	public static boolean podrobno=false;
	public static boolean ig=true;
	public static void main(String[] args) {

		ArrayList<ArrayList<String>> vrednosti=new ArrayList<ArrayList<String>>();
		ArrayList<String> parametri=new ArrayList<String>();
		ArrayList<ArrayList<String>> razlicne_vrednosti=new ArrayList<ArrayList<String>>();
	
		Options options = new Options();
        options.addOption("i", false, "Podrobnejsi prikaz");
        options.addOption("t", true, "Ucna datoteka");
        options.addOption("T", true, "Testna datoteka");
        options.addOption("m", false, "Racunanje z entropijo");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
           cmd = parser.parse(options, args);
        } catch (Exception e) { e.printStackTrace(); }

        if (cmd.hasOption("i")){
        	podrobno=true;
        }

        String ucna = cmd.getOptionValue("t");
        if (ucna != null){ //System.out.println("Imamo -t !! " +  ucna);
        }

        String testna = cmd.getOptionValue("T");
        if (testna != null){ //System.out.println("Imamo -T !! " +  testna);
        }

       if (cmd.hasOption("m")){
        	ig=false;
        }
  
	    Branje b1=new Branje();
		ArrayList<ArrayList<String>> celaTabela=b1.beri(ucna);
		
		//Vse vrednosti v tabeli (brez imena parametrov)
		for(int i=1; i<celaTabela.size(); i++){
//            System.out.println(celaTabela.get(i));
			vrednosti.add(celaTabela.get(i));
		}
		
		//Imena parametrov
		for(int i=0; i<celaTabela.get(0).size(); i++){
			parametri.add(celaTabela.get(0).get(i));
		}

		//razlicni primerki vrednosti
		boolean ni_not=true;
		for(int i=0; i<vrednosti.get(0).size(); i++){
			ArrayList<String> stolpec=new ArrayList<>();
			for(int j=1; j<vrednosti.size(); j++){
				if(stolpec.size()<1){
					stolpec.add(vrednosti.get(j).get(i));
				}else{
					for(int k=0; k<stolpec.size(); k++){
						if((stolpec.get(k)).equals(vrednosti.get(j).get(i))){
                            System.out.print("");
						    ni_not=false;
						}
					}
					if(ni_not){
						stolpec.add(vrednosti.get(j).get(i));
					}
					ni_not=true;
				}
			}
			razlicne_vrednosti.add(stolpec);
		}

		Vozlisce koren= new Vozlisce();
		koren.vrednosti=vrednosti;
		koren.parametri=parametri;
		koren.razlicne_vrednosti=razlicne_vrednosti;

		//ustvarjeno id3 drevo
		Id3 id3=new Id3();
		koren=id3.id3(vrednosti,parametri,razlicne_vrednosti,koren);

		//ustvarimo si koncno matriko, ki bo imela po stolpcih in vrsticah vrednosti koncnih razredov
		String [] vrstica = new String [razlicne_vrednosti.get(razlicne_vrednosti.size()-1).size()];

		//		Razredi
		vrstica = razlicne_vrednosti.get(razlicne_vrednosti.size()-1).toArray(vrstica);

		int [][] matrika = new int[vrstica.length][vrstica.length];
		for (int i=0; i<vrstica.length;i++){
			for (int j=0;j<vrstica.length;j++){
				matrika[i][j]=0;
			}
		}

//		Prebere se Testna mnozica
		Branje b2= new Branje();
		celaTabela=b2.beri(testna);
		
		vrednosti=new ArrayList<ArrayList<String>>();
		parametri=new ArrayList<String>();
		razlicne_vrednosti=new ArrayList<ArrayList<String>>();
		
		//vse vrednosti v tabeli (brez imena parametrov)
		for(int i=1; i<celaTabela.size(); i++){
			vrednosti.add(celaTabela.get(i));
		}
		
		//Imena parametrov
		for(int i=0; i<celaTabela.get(0).size(); i++){
			parametri.add(celaTabela.get(0).get(i));
		}

		for (ArrayList<String> posamezna_vrednost: vrednosti){
			 matrika = id3.test(posamezna_vrednost,koren,parametri,matrika,vrstica);
		}

		//Matrika zmede
		System.out.println("Matrika zmede: ");
		for (int i=0;i<vrstica.length;i++){
			System.out.format("%-13s", vrstica[i] );
		}
		System.out.println();
		for (int i=0; i<vrstica.length;i++){
			for (int j=0;j<vrstica.length;j++){
				System.out.format("%-13s",matrika[i][j] );
			}
			System.out.format("%-13s", "|"+vrstica[i] );
			System.out.println();
		}

		//Metrike
		if(podrobno){
		KoncniIzracuni ki=new KoncniIzracuni();
		System.out.println("\n\nMetrike:");
		System.out.println("Tocnost: "+ ki.stPravilnih(matrika)/vrednosti.size());
		System.out.println("F-Score: " + ki.fScore(matrika));
		System.out.println("Precision: " + ki.precision(matrika));
		System.out.println("Recall: "+ ki.recall(matrika));
		}
	}
}