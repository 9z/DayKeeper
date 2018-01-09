package codingtribe.com;

/**
 * Created by pc-05 on 2018-01-09.
 */

class StatRowVO {

    long startTime;
    long endTime;
    String catName;
    String beforeAction;
    String afterAction;
    int ActionCount;
    int catid;

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public StatRowVO(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;

        //        this.catName = catName;
        //        this.beforeAction = beforeAction;
        //        this.afterAction = afterAction;
        //        ActionCount = actionCount;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getBeforeAction() {
        return beforeAction;
    }

    public void setBeforeAction(String beforeAction) {
        this.beforeAction = beforeAction;
    }

    public String getAfterAction() {
        return afterAction;
    }

    public void setAfterAction(String afterAction) {
        this.afterAction = afterAction;
    }

    public int getActionCount() {
        return ActionCount;
    }

    public void setActionCount(int actionCount) {
        ActionCount = actionCount;
    }
}
