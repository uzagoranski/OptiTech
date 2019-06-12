package si.feri.pkm.optitech.Entity;

public class ErrorOccurrence {
    String first;
    String last;
    String desciption;
    int dtcCode;

    public ErrorOccurrence(String description, String first, String last, int dtcCode) {
        this.first = first;
        this.last = last;
        this.desciption = description;
        this.dtcCode = dtcCode;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getDtcCode() {
        return dtcCode;
    }

    public void setDtcCode(int dtcCode) {
        this.dtcCode = dtcCode;
    }

    @Override
    public String toString() {
        return "ErrorOccurrence{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", desciption='" + desciption + '\'' +
                ", dtcCode=" + dtcCode +
                '}';
    }
}
