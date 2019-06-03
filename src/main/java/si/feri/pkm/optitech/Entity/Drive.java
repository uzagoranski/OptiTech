package si.feri.pkm.optitech.Entity;

public class Drive {
    int id;
    String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Drive(int id, String type) {
        this.id = id;
        this.type = type;


    }
}
