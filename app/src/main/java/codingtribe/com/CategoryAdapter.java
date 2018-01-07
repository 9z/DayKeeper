package codingtribe.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pc-05 on 2018-01-04.
 */

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<CategoryVO> categoryData;
    private LayoutInflater inflater;
    private String id;
    private int selectedId;

    public CategoryAdapter(Context context, int layout, ArrayList<CategoryVO> categoryData, String id) {
        this.context = context;
        this.layout = layout;
        this.categoryData = categoryData;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.id = id;
    }

        @Override
    public int getCount() {
        return categoryData.size();
    }

    @Override
    public Object getItem(int i) {
        return categoryData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            view = inflater.inflate(layout, viewGroup, false);
            holder = new ViewHolder();

            holder.tv_categoryName = view.findViewById(R.id.tv_category_name);

            holder.tv_categoryName.setText(categoryData.get(i).getCategoryName()+" "+categoryData.get(i).getCat_id()+" "+categoryData.get(i).getCat_id());


        } else {
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public class ViewHolder{
        TextView tv_categoryName;
    }
}
