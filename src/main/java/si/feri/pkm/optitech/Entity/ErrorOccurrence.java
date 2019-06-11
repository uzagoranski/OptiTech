package si.feri.pkm.optitech.Entity;

public class ErrorOccurrence {
    String first;
    String last;
    String desciption;

    public ErrorOccurrence(String desciption, String first, String last) {
        this.first = first;
        this.last = last;
        this.desciption = desciption;
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

    @Override
    public String toString() {
        return "ErrorOccurrence{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", desciption='" + desciption + '\'' +
                '}';
    }
}
