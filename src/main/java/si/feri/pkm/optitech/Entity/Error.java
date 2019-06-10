package si.feri.pkm.optitech.Entity;

import java.sql.Date;

public class Error {
    String descrition;
    int code;
    Date date;


    public Error(String descrition, int code, Date date) {
        this.descrition = descrition;
        this.code = code;
        this.date = date;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
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
                "descrition='" + descrition + '\'' +
                ", code=" + code +
                ", date=" + date +
                '}';
    }
}
