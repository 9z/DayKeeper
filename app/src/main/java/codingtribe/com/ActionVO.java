package codingtribe.com;

public class ActionVO {

    int action_id;
    int cat_id;
    long start_time;
    String cat_name;

    public ActionVO(int action_id, int cat_id, long start_time, String cat_name) {
        this.action_id = action_id;
        this.cat_id = cat_id;
        this.start_time = start_time;
        this.cat_name = cat_name;
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

    public String getCat_name() { return cat_name;}
}
