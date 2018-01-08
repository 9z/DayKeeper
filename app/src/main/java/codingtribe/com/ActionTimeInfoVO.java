package codingtribe.com;

/**
 * Created by pc-05 on 2018-01-09.
 */

class ActionTimeInfoVO {

    int actionId;
    int actionName;
    long startTime;
    long endTime;
    int minuteStart;
    int minuteEnd;
    int hourStart;
    int hourEnd;

    public ActionTimeInfoVO(int actionId, int actionName, long startTime, long endTime, int minuteStart, int minuteEnd, int hourStart, int hourEnd) {
        this.actionId = actionId;
        this.actionName = actionName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minuteStart = minuteStart;
        this.minuteEnd = minuteEnd;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getActionName() {
        return actionName;
    }

    public void setActionName(int actionName) {
        this.actionName = actionName;
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

    public int getMinuteStart() {
        return minuteStart;
    }

    public void setMinuteStart(int minuteStart) {
        this.minuteStart = minuteStart;
    }

    public int getMinuteEnd() {
        return minuteEnd;
    }

    public void setMinuteEnd(int minuteEnd) {
        this.minuteEnd = minuteEnd;
    }

    public int getHourStart() {
        return hourStart;
    }

    public void setHourStart(int hourStart) {
        this.hourStart = hourStart;
    }

    public int getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(int hourEnd) {
        this.hourEnd = hourEnd;
    }
}
