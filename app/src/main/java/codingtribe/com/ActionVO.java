package codingtribe.com;

public class ActionVO {

    int action_id;
    int cat_id;
    long start_time;

    public ActionVO(int action_id, int cat_id, long start_time) {
        this.action_id = action_id;
        this.cat_id = cat_id;
        this.start_time = start_time;
    }

    public int getAction_id() {
        return action_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public long getStart_time() {
        return start_time;
    }
}
