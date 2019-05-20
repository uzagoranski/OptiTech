package si.feri.pkm.optitech.Entity;

public class Drive {
    int id;
    String naziv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Drive(int id, String naziv){
        this.id = id;
        this.naziv = naziv;


    }
}
