package codingtribe.com;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by kang5 on 2018-01-08.
 */

public class JsonCreate {

    ActionDB ActionDbHelper;
    ArrayList<ActionVO> actionVOArrayList;


    public JSONArray createJson(Activity activity) {//SQLite에서 값 가져와서 JSON 형식으로 변환
        ActionDbHelper = new ActionDB(activity);
        actionVOArrayList = ActionDbHelper.getAllAction(activity);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String json="";
        Log.v(" DB 출력", actionVOArrayList.size() + "");
        try {
        for (int i = 0; i < actionVOArrayList.size(); i++) {
            jsonObject.put("action_id", actionVOArrayList.get(i).getAction_id()+"");
            jsonObject.put("cat_id", actionVOArrayList.get(i).getCat_id()+"");
            jsonObject.put("start_time", actionVOArrayList.get(i).getStart_time()+"");
            jsonObject.put("cat_name", actionVOArrayList.get(i).getCat_name()+"");
            //Log.v("DB 출력",action_id+" "+ cat_id+" "+ start_time+" "+cat_name);
            jsonArray.put(jsonObject);
        }

        } catch (JSONException e) {
            e.printStackTrace();
        }
         return jsonArray;
    }
}
