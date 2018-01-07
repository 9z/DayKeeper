package codingtribe.com;

/**
 * Created by pc-13 on 2018-01-07.
 */

class StatVO {

    String catName;
    int time;

    public StatVO(String catName, int time) {
        this.catName = catName;
        this.time = time;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
