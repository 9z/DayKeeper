package codingtribe.com;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static codingtribe.com.item_home.CatDbHelper;

/**
 * Created by pc-05 on 2018-01-07.
 */

public class ActionDB extends SQLiteOpenHelper {

    public ActionDB(Context context) {
        super(context, "actionDB.db", null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ACTION_INFO (action_id INTEGER PRIMARY KEY AUTOINCREMENT, cat_id INTEGER, start_time INTEGER);");

        Random rand = new Random();
        int ranCatNum;

        Date dateEnd = new Date();
        long dateTimeEnd;
        long dateTimeNow;
        long dateTimeStart;

        Date dateStart = new Date();

        //더미데이터 생성 마지막 날짜 생성
        dateEnd.setYear(2018     -1900);
        dateEnd.setMonth(1    -1);
        dateEnd.setDate(7    -1);
        dateEnd.setHours(17);

        //마지막 날짜를 지정하고 싶을 때
        //        dateTimeEnd = dateEnd.getTime();

        //현재 시각을 지정하고 싶을 때
        dateTimeEnd = System.currentTimeMillis();

        //더미데이터 생성 첫번째 날짜 생성
        dateStart.setYear(2017    -1900);
        dateStart.setMonth(11    -1);
        dateStart.setDate(1    -1);
        dateStart.setHours(9);
        dateTimeStart = dateStart.getTime();

        ArrayList<ActionVO> actionAL = new ArrayList<>();

        dateTimeNow = dateTimeStart;
        while(dateTimeNow<dateTimeEnd){
            ranCatNum = rand.nextInt(10)+1;
            int plus = 600000*rand.nextInt(48)+rand.nextInt(600000);
            dateTimeNow += plus;
            sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,"+ranCatNum+","+dateTimeNow+");");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(int cat_id, long startTime) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        Log.v("들어온 값은 ", startTime+"");
        db.execSQL("INSERT INTO ACTION_INFO VALUES(null, '" + cat_id + "', " + startTime + ");");
        db.close();
    }

    public void update_cat(int action_id, int cat_id_will_be_changed) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 액션의  수정
        db.execSQL("UPDATE ACTION_INFO SET cat_id=" + cat_id_will_be_changed + " WHERE action_id='" + action_id + "';");
        db.close();
    }

    public void update_time(int action_id, int startTime_will_be_changed) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 액션의  수정
        db.execSQL("UPDATE ACTION_INFO SET start_time=" + startTime_will_be_changed + " WHERE action_id='" + action_id + "';");
        db.close();
    }

    public void delete(int action_id) {
        SQLiteDatabase db = getWritableDatabase();
        // 삭제가 쓰이지는 않을 것 같다.

        db.execSQL("DELETE FROM ACTION_INFO WHERE action_id =  "+action_id+";");
        db.close();
    }

    public ArrayList<ActionVO> getAllAction(Activity act) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ActionVO tempActionVO;
        ArrayList<ActionVO> tempArrayList = new ArrayList<ActionVO>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM ACTION_INFO;", null);

        while (cursor.moveToNext()) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd 일 aa HH:mm z");


            long timeMillis = cursor.getLong(2);
            String now = sdf.format(timeMillis);


            Log.v("DB정보", cursor.getInt(0)+" "+cursor.getInt(1)+" "+ now);


            String catName = CatDbHelper.getCatName(cursor.getInt(1));


            tempActionVO = new ActionVO(cursor.getInt(0), cursor.getInt(1), cursor.getLong(2), catName);
            tempArrayList.add(tempActionVO);


        }
        return tempArrayList;
    }
}
