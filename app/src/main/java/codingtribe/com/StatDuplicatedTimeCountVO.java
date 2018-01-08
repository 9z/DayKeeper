package codingtribe.com;

/**
 * Created by pc-05 on 2018-01-09.
 */

public class StatDuplicatedTimeCountVO {

    long time;
    int count;

    public StatDuplicatedTimeCountVO(long time, int count) {
        this.time = time;
        this.count = count;
    }

    public long getTime() {
        return time;
    }

    public int getCount() {
        return count;
    }
}
