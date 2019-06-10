package si.feri.pkm.optitech.AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Id3 {

    public Vozlisce id3(ArrayList<ArrayList<String>> vrednosti, ArrayList<String> parametri, ArrayList<ArrayList<String>> razlicne_vrednosti, Vozlisce oce) {

        boolean not = true;                                                        //dolocimo ali so vsi razredi v mno�ici enaki
        ArrayList<String> vred = oce.vrednosti.get(0);
        int velikost = vred.size() - 1;
        for (int i = 0; i < oce.vrednosti.size() - 1; i++) {
            if (!oce.vrednosti.get(i).get(velikost).equals(oce.vrednosti.get(i + 1).get(velikost))) {
                not = false;
            }
        }

        if (not || razlicne_vrednosti.size() <= 1) {                                        //ce so enaki
            //System.out.println("Vsi rezultati so enaki1");
            ArrayList<String> pogostost = new ArrayList<String>();
            for (ArrayList<String> primerek : oce.vrednosti) {
                pogostost.add(primerek.get(primerek.size() - 1));
            }
            int najvisjaPogostost = 0;
            for (String vrednost : razlicne_vrednosti.get(razlicne_vrednosti.size() -1)) {
                int temp = Collections.frequency(pogostost, vrednost);
                if (temp > najvisjaPogostost) {
                    najvisjaPogostost = temp;
                    oce.parameter = vrednost;
                }
            }

        } else {
            oce = izracunaj(vrednosti, parametri, razlicne_vrednosti, oce);                //racunaje entropije


            String vrednost;                                                // vnesemo v drevo vse lastnosti, ki spadajo k zmagovalnemu parametru
            ArrayList<String> vr;
            vr = razlicne_vrednosti.get(parametri.indexOf(oce.parameter));
            for (int i = 0; i < vr.size(); i++) {
                vrednost = vr.get(i);
                Vozlisce otrok = new Vozlisce();                                            //dodajanje "otrok"
                otrok.vrednost = vrednost;                                                    //vrednost "otroka"
                otrok.oce = oce;                                                            //njegov "oce"


                for (ArrayList<String> vsi_pripad : vrednosti) {                            //v tabeli poiscemo tiste vrednosti, ki sodijo k tem "otroku"
                    //System.out.println(oce.parameter+" "+vrednost);
                    if (vsi_pripad.get(parametri.indexOf(oce.parameter)).equals(vrednost)) {
                        ArrayList<String> vsi = (ArrayList<String>) vsi_pripad.clone();
//                        System.out.println(vsi);
                        vsi.remove(parametri.indexOf(oce.parameter));
                        otrok.vrednosti.add(vsi);                                        //dodamo vse vrednosti
                    }
                }
                if (!otrok.vrednosti.isEmpty()) {                                            //ko jih zmanjka koncamo
                    otrok.parametri = (ArrayList<String>) parametri.clone();
                    otrok.parametri.remove(parametri.indexOf(oce.parameter));

                    otrok.razlicne_vrednosti = (ArrayList<ArrayList<String>>) razlicne_vrednosti.clone();
                    otrok.razlicne_vrednosti.remove(parametri.indexOf(oce.parameter));

                    oce.otroci.add(otrok);
                    id3(otrok.vrednosti, otrok.parametri, otrok.razlicne_vrednosti, otrok);        //rekurzija

                }
            }
        }
        return oce;
    }

    public Vozlisce izracunaj(ArrayList<ArrayList<String>> vrednosti, ArrayList<String> parametri, ArrayList<ArrayList<String>> razlicne_vrednosti, Vozlisce oce) {

        boolean ni_not = true;                                                        //vsakemu razredu priredimo vrednosti in kolikokrat se pojavijo (podatki)
        ArrayList<ArrayList<String>> podatki = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < parametri.size(); i++) {

            ArrayList<String> vnos = new ArrayList<String>();
            for (int j = 0; j < vrednosti.size(); j++) {
                if (vnos.size() < 1) {
                    vnos.add(vrednosti.get(j).get(i));
                    String parameter = vrednosti.get(j).get(i);
                    int st = 0;
                    for (int l = 0; l < vrednosti.size(); l++) {
                        if (parameter.equals(vrednosti.get(l).get(i))) {
                            st = st + 1;
                        }
                    }
                    vnos.add(Integer.toString(st));
                } else {
                    for (int k = 0; k < vnos.size(); k++) {

                        if ((vnos.get(k)).equals(vrednosti.get(j).get(i))) {
                            ni_not = false;
                            break;
                        }
                    }
                    if (ni_not) {
                        vnos.add(vrednosti.get(j).get(i));
                        String vrednost = vrednosti.get(j).get(i);
                        int st = 0;
                        for (int l = 1; l < vrednosti.size(); l++) {
                            if (vrednost.equals(vrednosti.get(l).get(i))) {
                                st = st + 1;
                            }
                        }
                        vnos.add(Integer.toString(st));
                    }
                    ni_not = true;
                }
            }
            podatki.add(vnos);
        }
		
		
		/*for(int i=0; i<podatki.size(); i++){
			System.out.println(parametri.get(i)+": "+podatki.get(i));
		}*/

        ArrayList<ArrayList<Integer>> delez = new ArrayList<ArrayList<Integer>>();
        ArrayList<String> razlicni_rezultati = null;

        for (int i = 0; i < podatki.size(); i++) {
            for (int j = 0; j < podatki.get(i).size(); j = j + 2) {
                razlicni_rezultati = new ArrayList<>();        //koncni razlicni rezultati
                for (int t = 0; t < razlicne_vrednosti.get(razlicne_vrednosti.size() - 1).size(); t++) {
                    razlicni_rezultati.add(razlicne_vrednosti.get(razlicne_vrednosti.size() - 1).get(t));
                }

                ArrayList<Integer> stevilo = new ArrayList<Integer>();
                for (int k = 0; k < razlicni_rezultati.size(); k++) {
                    int d = 0;
                    for (int l = 0; l < vrednosti.size(); l++) {
                        if ((vrednosti.get(l).get(i)).equals(podatki.get(i).get(j))) {
                            if ((razlicni_rezultati.get(k)).equals(vrednosti.get(l).get(podatki.size() - 1))) {
                                d = d + 1;
                            }
                        }
                    }
                    stevilo.add(d);

                }
                delez.add(stevilo);
            }
        }
        for (int i = 0; i < delez.size(); i++) {
            //System.out.println(delez.get(i));
        }
        int stevec = 0;


        ArrayList<ArrayList<Double>> vmesne_entropije = new ArrayList<ArrayList<Double>>(); //za vsako lastnost izracunamo entropije
        ArrayList<Double> entropija0;
        double[] entropija_parametrov = new double[parametri.size()];
        double vseh = 0;
        int vsehClenov = 0;
        for (int i = 0; i < podatki.size(); i++) {            //parametri
            double entropija = 0;
            //System.out.println(imena_parametrov[i]);
            entropija0 = new ArrayList<Double>();
            for (int j = 1; j <= podatki.get(i).size(); j = j + 2) { //vrednosti
                vseh = Double.parseDouble(podatki.get(i).get(j));
                vsehClenov = podatki.get(i).size() - 1;
                double entropija1 = 0;
                for (int k = 0; k < delez.get(0).size(); k++) {
                    if ((delez.get(stevec).get(k)) != 0 && vsehClenov != 0) {
                        double clen = (delez.get(stevec).get(k)) / (vsehClenov);

                        entropija1 = entropija1 - (clen * Math.log(clen) / Math.log(2));        //izracun

                    }
                }

                entropija0.add(entropija1);
                entropija = entropija + ((vseh / (vrednosti.size())) * entropija1);
                stevec++;

            }
            vmesne_entropije.add(entropija0);
            entropija_parametrov[i] = entropija;

        }


        if (Glavni.ig) {
            ArrayList<String> k_vrednosti = new ArrayList<String>();
            for (int i = 0; i < vrednosti.size(); i++) {
                k_vrednosti.add(vrednosti.get(i).get(vrednosti.get(i).size() - 1));
            }

            ArrayList<Double> s = new ArrayList<Double>();
            for (int i = 0; i < razlicni_rezultati.size(); i++) {
                double stev = 0;
                for (int j = 0; j < k_vrednosti.size(); j++) {
                    if (razlicni_rezultati.get(i).equals(k_vrednosti.get(j))) {
                        stev = stev + 1;
                    }
                }
                s.add(stev);

            }
            double izracun = 0;
            for (int i = 0; i < s.size(); i++) {
                double clen = s.get(i) / (k_vrednosti.size());
                if (clen != 0) {
                    izracun = izracun - clen * (Math.log(clen) / Math.log(2));
                } else {
                    izracun = 1;
                }
            }

            double[] ig = new double[entropija_parametrov.length];                    //entropija mnozice
            for (int i = 0; i < ig.length - 1; i++) {
                ig[i] = izracun - entropija_parametrov[i];
            }
            double najvecji = -1;
            int indeks = 0;                                                            //poi��emo najvecji ig
            for (int i = 0; i < ig.length - 1; i++) {
                if (ig[i] > najvecji) {
                    najvecji = ig[i];
                    indeks = i;
                }
            }

            oce.parameter = parametri.get(indeks);
            oce.entropija = najvecji;
            //System.out.println("Vrednost entropije:"+oce.entropija);*/
            return oce;
        } else {
            double najmanjsi = 2;
            int indeks = 0;                                                            //poiscemo najmanjso entropijo
            for (int i = 0; i < entropija_parametrov.length - 1; i++) {
                if (entropija_parametrov[i] < najmanjsi) {
                    najmanjsi = entropija_parametrov[i];
                    indeks = i;
                }
                //System.out.println("E: "+entropija_parametrov[i]);
            }
            double najm = 3;                                                            //najmanj�a izmed vmesnih entropij
            for (int i = 0; i < vmesne_entropije.get(indeks).size(); i++) {
                if (vmesne_entropije.get(indeks).get(i) < najm) {
                    najm = vmesne_entropije.get(indeks).get(i);
                }
            }

            oce.parameter = parametri.get(indeks);
            oce.entropija = najmanjsi;
            //System.out.println("Vrednost entropije:"+oce.entropija);
            return oce;

        }
    }

    public int[][] test(ArrayList<String> posametna_vrednost, Vozlisce koren, ArrayList<String> parametri, int[][] matrika, String[] vrstica) {
        ArrayList<String> razlicni_rezultati = new ArrayList<String>(Arrays.asList(vrstica));

        Vozlisce o;
        for (int st_o = 0; st_o < koren.otroci.size(); st_o++) {                    //gremo po drevesu
            o = koren.otroci.get(st_o);
            if (o.vrednost.equals(posametna_vrednost.get(parametri.indexOf(koren.parameter)))) {        //pogledamo za vsak posamezen parameter
                if (razlicni_rezultati.indexOf(o.parameter) != -1) {                                        //ce najdemo usterezn parameter
                    for (int i = 0; i < vrstica.length; i++) {
                        for (int j = 0; j < vrstica.length; j++) {

                            if (o.parameter.equals(vrstica[i])) {
                                if (posametna_vrednost.get(posametna_vrednost.size() - 1).equals(vrstica[j])) {    //primerjamo z dejansko vrednostjo
                                    matrika[i][j]++;                                                            //v matriko
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    test(posametna_vrednost, o, parametri, matrika, vrstica);        //rekurzija					//ponovimo postopek
                }
            }
        }
        return matrika;
    }

}