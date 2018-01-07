package codingtribe.com;

public class CategoryVO {

    int cat_id;
    String categoryName;
    int mem_id;
    boolean isDel;


    public CategoryVO(int cat_id, String categoryName, int mem_id, boolean isDel) {
        this.cat_id = cat_id;
        this.categoryName = categoryName;
        this.mem_id = mem_id;
        this.isDel = isDel;
    }

    public int getCat_id() {
        return cat_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getMem_id() {
        return mem_id;
    }

    public boolean isDel() {
        return isDel;
    }
}
