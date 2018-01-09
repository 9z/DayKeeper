package codingtribe.com;

/**
 * Created by pc-05 on 2018-01-09.
 */

class TimeTableRowVO {
    int dayOfWeek;
    int weekOfMonth;
    int isWeekend;
    int hour;
    int month;
    int action;

    public TimeTableRowVO(int dayOfWeek, int weekOfMonth, int isWeekend, int hour, int month, int action) {
        this.dayOfWeek = dayOfWeek;
        this.weekOfMonth = weekOfMonth;
        this.isWeekend = isWeekend;
        this.hour = hour;
        this.month = month;
        this.action = action;
    }
}
