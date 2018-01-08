package codingtribe.com;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by pc-13 on 2018-01-08.
 */

class StatWeekVO {

    int date;
    ArrayList<StatVO> dayData;

    public StatWeekVO(int date, ArrayList<StatVO> dayData) {
        this.date = date;
        this.dayData = dayData;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public ArrayList<StatVO> getDayData() {
        return dayData;
    }

    public void setDayData(ArrayList<StatVO> dayData) {
        this.dayData = dayData;
    }
}
