package codingtribe.com;

/**
 * Created by pc-13 on 2018-01-09.
 */

public class PredictVO {
    String startTime;
    String endTime;
    String catName;
    float percent;

    public PredictVO(String startTime, String endTime, String catName, float percent) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.catName = catName;
        this.percent = percent;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
