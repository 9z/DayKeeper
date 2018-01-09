package codingtribe.com;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by pc-13 on 2018-01-09.
 */

public class PredictAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<PredictVO> predictData;
    private LayoutInflater inflater;



    public PredictAdapter(Context applicationContext, int listpredict, ArrayList<PredictVO> arrayList) {
        this.context = applicationContext;
        this.layout = listpredict;
        this.predictData = arrayList;

        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//일단 객체형으로 반환
    }

    @Override
    public int getCount() {
        return predictData.size();
    }

    @Override
    public Object getItem(int position) {
        return predictData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        //항목의 index, 전에 inflate 되어있는 view, listview

        ViewHolder holder;

        if(view == null){
            view = inflater.inflate(layout,parent, false);
            holder = new ViewHolder();
            holder.listText_time = view.findViewById(R.id.listText_time);
            holder.listText_action = view.findViewById(R.id.listText_action);
            holder.listText_percent = view.findViewById(R.id.listText_percent);

            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        holder.listText_time.setText(predictData.get(i).getStartTime()+predictData.get(i).getEndTime());
        holder.listText_action.setText(predictData.get(i).getCatName()+"");
        holder.listText_percent.setText((int)predictData.get(i).getPercent()+"%");

        return view;
    }
    public class ViewHolder{
        TextView listText_time;
        TextView listText_action;
        TextView listText_percent;
    }
}
