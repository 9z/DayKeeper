package codingtribe.com;


import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kang5 on 2018-01-08.
 */

public class JsonSend {

ActionDB ActionDbHelper;
ArrayList<ActionVO> actionVOArrayList;

    public void createJson(Activity activity) {
        ActionDbHelper = new ActionDB(activity);
        actionVOArrayList = ActionDbHelper.getAllAction(activity);

        int action_id;
        int cat_id;
        long start_time;
        String cat_name;

         Log.v(" DB 출력",actionVOArrayList.size()+"");
         for(int i=0; i<actionVOArrayList.size();i++){
             action_id = actionVOArrayList.get(i).getAction_id();
             cat_id = actionVOArrayList.get(i).getCat_id();
             start_time = actionVOArrayList.get(i).getStart_time();
             cat_name = actionVOArrayList.get(i).getCat_name();

             Log.v("DB 출력",action_id+" "+ cat_id+" "+ start_time+" "+cat_name);

         }
    }


}
