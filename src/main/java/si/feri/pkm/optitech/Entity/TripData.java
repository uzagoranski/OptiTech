package si.feri.pkm.optitech.Entity;

public class TripData {
    String startTime;
    String finishTime;
    int userId;
    double distance;
    int totalScore;
    int rpmAvg;
    int rpmMax;
    int vssAvg;
    int vssMax;

    public TripData() {

    }

    public TripData(String startTime, String finishTime, int userId, int distance, int totalScore, int rpmAvg, int rpmMax, int vssAvg, int vssMax) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.userId = userId;
        this.distance = distance;
        this.totalScore = totalScore;
        this.rpmAvg = rpmAvg;
        this.rpmMax = rpmMax;
        this.vssAvg = vssAvg;
        this.vssMax = vssMax;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getRpmAvg() {
        return rpmAvg;
    }

    public void setRpmAvg(int rpmAvg) {
        this.rpmAvg = rpmAvg;
    }

    public int getRpmMax() {
        return rpmMax;
    }

    public void setRpmMax(int rpmMax) {
        this.rpmMax = rpmMax;
    }

    public int getVssAvg() {
        return vssAvg;
    }

    public void setVssAvg(int vssAvg) {
        this.vssAvg = vssAvg;
    }

    public int getVssMax() {
        return vssMax;
    }

    public void setVssMax(int vssMax) {
        this.vssMax = vssMax;
    }

    @Override
    public String toString() {
        return "TripData{" +
                "startTime='" + startTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", userId=" + userId +
                ", distance=" + distance +
                ", totalScore=" + totalScore +
                ", rpmAvg=" + rpmAvg +
                ", rpmMax=" + rpmMax +
                ", vssAvg=" + vssAvg +
                ", vssMax=" + vssMax +
                '}';
    }
}
