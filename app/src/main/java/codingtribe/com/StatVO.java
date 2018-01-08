package codingtribe.com;

/**
 * Created by pc-13 on 2018-01-07.
 */

class StatVO {

    String catName;
    long time;
    int cat_id;

    public StatVO(String catName, long time, int cat_id) {
        this.catName = catName;
        this.time = time;
        this.cat_id = cat_id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCat_id() {
        return cat_id;
    }
}
