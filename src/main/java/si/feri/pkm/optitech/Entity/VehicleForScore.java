package si.feri.pkm.optitech.Entity;

import si.feri.pkm.optitech.Database.SQLDriveData;

public class VehicleForScore {
    int rpmMax;
    int rpmAvg;
    int vssMax;
    int vssAvg;
    int drvDist;
    int drvTime;
    int drvStartStopCnt;
    int fuelCons;

    public VehicleForScore(int rpmMax, int rpmAvg, int vssMax, int vssAvg, int drvDist, int drvTime, int drvStartStopCnt, int fuelCons) {
        this.rpmMax = rpmMax;
        this.rpmAvg = rpmAvg;
        this.vssMax = vssMax;
        this.vssAvg = vssAvg;
        this.drvDist = drvDist;
        this.drvTime = drvTime;
        this.drvStartStopCnt = drvStartStopCnt;
        this.fuelCons = fuelCons;
    }



    public int getRpmMax() {
        return rpmMax;
    }

    public int getRpmAvg() {
        return rpmAvg;
    }

    public int getVssMax() {
        return vssMax;
    }

    public int getVssAvg() {
        return vssAvg;
    }

    public int getDrvDist() {
        return drvDist;
    }

    public int getDrvTime() {
        return drvTime;
    }

    public int getDrvStartStopCnt() {
        return drvStartStopCnt;
    }

    public int getFuelCons() {
        return fuelCons;
    }

    public int calculateScore(int rpmMax, int rpmAvg, int vssMax, int vssAvg, int drvDist, int drvTime, int drvStartStop, int fuelCons, int totalScore){
int stevec = 1;
int[] a = {rpmMax, rpmAvg, vssMax, vssAvg, drvDist,drvTime,drvStartStop, fuelCons};
        for(int i=0;i<a.length;i++){
         if(a[i] != 0) {
             stevec++;
         }
         }
     return (rpmMax+rpmAvg+vssMax+vssAvg+drvDist+drvTime+drvStartStop+fuelCons+totalScore)/stevec;
    }
}


//vehicleForScore.calculateScore(
//                (int)Math.round(100-dd.koefRpmMax()*(vehicleForScore.getRpmMax()-dd.getRpmMaxMIN())),
//                (int)Math.round(100-dd.koefRpmAvg()*(vehicleForScore.getRpmAvg()-dd.getRpmAvgMIN())),
//                (int)Math.round(100-dd.koefVssMax()*(vehicleForScore.getVssMax()-dd.getVssMaxMIN())),
//                (int)Math.round(100-dd.koefVssAvg()*(vehicleForScore.getVssAvg()-dd.getVssAvgMIN())),
//                (int)Math.round(100-dd.koefDrvDist()*(vehicleForScore.getDrvDist()-dd.getDrvDistMIN())),
//                (int)Math.round(100-dd.koefDrvTime()*(vehicleForScore.getDrvTime()-dd.getDrvTimeMIN())),
//                (int)Math.round(100-dd.koefDrvStartStopCnt()*(vehicleForScore.getDrvStartStopCnt()-dd.getDrvStartStopMIN())),
//                (int)Math.round(100-dd.koefFuelCons()*(vehicleForScore.getFuelCons()-dd.getFuelConsMIN())),
//                SQLDriveData.getTotalScoreForSelectedCar(id)
//                );
