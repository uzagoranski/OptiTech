package si.feri.pkm.optitech.Entity;

import java.sql.Date;

public class Error {
    String description;
    int code;
    Date date;


    public Error(String description, int code, Date date) {
        this.description = description;
        this.code = code;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Error{" +
                "description='" + description + '\'' +
                ", code=" + code +
                ", date=" + date +
                '}';
    }
}
