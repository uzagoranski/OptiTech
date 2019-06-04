package si.feri.pkm.optitech.Entity;

public class DriveData {
    int rpmMaxMAX = 0;
    int rpmMaxMIN = 0;
    int rpmAvgMIN = 0;
    int rpmAvgMAX = 0;
    int vssMaxMAX = 0;
    int vssMaxMIN = 0;
    int vssAvgMAX = 0;
    int vssAvgMIN = 0;
    int drvDistMAX = 0;
    int drvDistMIN = 0;
    int drvTimeMAX = 0;
    int drvTimeMIN = 0;
    int drvStartStopMAX = 0;
    int drvStartStopMIN = 0;
    int fuelConsMAX = 0;
    int fuelConsMIN = 0;

    public DriveData(int rpmMaxMAX, int rpmMaxMIN, int rpmAvgMIN, int rpmAvgMAX, int vssMaxMAX, int vssMaxMIN, int vssAvgMAX, int vssAvgMIN, int drvDistMAX, int drvDistMIN, int drvTimeMAX, int drvTimeMIN, int drvStartStopMAX, int drvStartStopMIN, int fuelConsMAX, int fuelConsMIN) {
        this.rpmMaxMAX = rpmMaxMAX;
        this.rpmMaxMIN = rpmMaxMIN;
        this.rpmAvgMIN = rpmAvgMIN;
        this.rpmAvgMAX = rpmAvgMAX;
        this.vssMaxMAX = vssMaxMAX;
        this.vssMaxMIN = vssMaxMIN;
        this.vssAvgMAX = vssAvgMAX;
        this.vssAvgMIN = vssAvgMIN;
        this.drvDistMAX = drvDistMAX;
        this.drvDistMIN = drvDistMIN;
        this.drvTimeMAX = drvTimeMAX;
        this.drvTimeMIN = drvTimeMIN;
        this.drvStartStopMAX = drvStartStopMAX;
        this.drvStartStopMIN = drvStartStopMIN;
        this.fuelConsMAX = fuelConsMAX;
        this.fuelConsMIN = fuelConsMIN;
    }

    //Končno ohranjenost izračunaš tako,
    // da od izbrane vrednosti odšteješ minValue in nato imaš
    // ohranjenost = k *(dobljenaVrednost - minVrednost)
//    double koefRpmMax  = 100.d / (rpmMaxMAX - rpmMaxMIN);
//    double koefRpmAvg = 100.d / (rpmAvgMAX - rpmAvgMIN);
//    double koefVssMax  = 100.d / (vssMaxMAX - vssMaxMIN);
//    double koefVssAvg  = 100.d / (vssAvgMAX - vssAvgMIN);
//    double koefDrvDist  = 100.d / (drvDistMAX - drvDistMIN);
//    double koefDrvTime  = 100.d / (drvTimeMAX - drvTimeMIN);
//    double koefDrvStartStop  = 100.d / ( drvStartStopMAX - drvStartStopMIN);
//    double koefFuelCons  = 100.d / ( fuelConsMAX - fuelConsMIN);


    public double koefRpmMax() {
        return (100.d / (rpmMaxMAX - rpmMaxMIN));
    }

    public double koefRpmAvg() {
        return (100.d / (rpmAvgMAX - rpmAvgMIN));
    }

    public double koefVssMax() {
        return (100.d / (vssMaxMAX - vssMaxMIN));
    }

    public double koefVssAvg() {
        return (100.d / (vssAvgMAX - vssAvgMIN));
    }

    public double koefDrvDist() {
        return (100.d / (drvDistMAX - drvDistMIN));
    }

    public double koefDrvTime() {
        return (100.d / (drvTimeMAX - drvTimeMIN));
    }

    public double koefDrvStartStopCnt() {
        return (100.d / (drvStartStopMAX - drvStartStopMIN));
    }

    public double koefFuelCons() {
        return (100.d / (fuelConsMAX - fuelConsMIN));
    }

    public int getRpmMaxMAX() {
        return rpmMaxMAX;
    }

    public int getRpmMaxMIN() {
        return rpmMaxMIN;
    }

    public int getRpmAvgMIN() {
        return rpmAvgMIN;
    }

    public int getRpmAvgMAX() {
        return rpmAvgMAX;
    }

    public int getVssMaxMAX() {
        return vssMaxMAX;
    }

    public int getVssMaxMIN() {
        return vssMaxMIN;
    }

    public int getVssAvgMAX() {
        return vssAvgMAX;
    }

    public int getVssAvgMIN() {
        return vssAvgMIN;
    }

    public int getDrvDistMAX() {
        return drvDistMAX;
    }

    public int getDrvDistMIN() {
        return drvDistMIN;
    }

    public int getDrvTimeMAX() {
        return drvTimeMAX;
    }

    public int getDrvTimeMIN() {
        return drvTimeMIN;
    }

    public int getDrvStartStopMAX() {
        return drvStartStopMAX;
    }

    public int getDrvStartStopMIN() {
        return drvStartStopMIN;
    }

    public int getFuelConsMAX() {
        return fuelConsMAX;
    }

    public int getFuelConsMIN() {
        return fuelConsMIN;
    }

    @Override
    public String toString() {
        return "DriveData{" +
                "rpmMaxMAX=" + rpmMaxMAX +
                ", rpmMaxMIN=" + rpmMaxMIN +
                ", rpmAvgMIN=" + rpmAvgMIN +
                ", rpmAvgMAX=" + rpmAvgMAX +
                ", vssMaxMAX=" + vssMaxMAX +
                ", vssMaxMIN=" + vssMaxMIN +
                ", vssAvgMAX=" + vssAvgMAX +
                ", vssAvgMIN=" + vssAvgMIN +
                ", drvDistMAX=" + drvDistMAX +
                ", drvDistMIN=" + drvDistMIN +
                ", drvTimeMAX=" + drvTimeMAX +
                ", drvTimeMIN=" + drvTimeMIN +
                ", drvStartStopMAX=" + drvStartStopMAX +
                ", drvStartStopMIN=" + drvStartStopMIN +
                ", fuelConsMAX=" + fuelConsMAX +
                ", fuelConsMIN=" + fuelConsMIN +
                '}';
    }

}