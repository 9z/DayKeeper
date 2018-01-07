package codingtribe.com;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(1,3,1515053392173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(2,9,1515064192173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(3,5,1515067792173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(4,1,1515069592173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(5,5,1515074992173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(6,7,1515085792173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(7,6,1515089392173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(8,10,1515091192173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(9,1,1515096592173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(10,1,1515107392173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(11,3,1515110992173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(12,7,1515112792173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(13,8,1515118192173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(14,1,1515128992173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(15,5,1515132592173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(16,6,1515134392173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(17,7,1515139792173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(18,3,1515150592173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(19,4,1515154192173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(20,4,1515155992173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(21,6,1515161392173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(22,6,1515172192173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(23,4,1515175792173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(24,6,1515177592173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(25,6,1515182992173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(26,10,1515193792173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(27,6,1515197392173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(28,4,1515199192173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(29,5,1515204592173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(30,6,1515215392173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(31,6,1515218992173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(32,8,1515220792173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(33,2,1515226192173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(34,6,1515236992173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(35,9,1515240592173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(36,10,1515242392173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(37,6,1515247792173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(38,5,1515258592173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(39,6,1515262192173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(40,8,1515263992173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(41,3,1515269392173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(42,5,1515280192173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(43,6,1515283792173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(44,8,1515285592173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(45,4,1515290992173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(46,6,1515301792173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(47,7,1515305392173);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(48,7,1515307192173);");

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

    public ArrayList<ActionVO> getAllAction() {
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
            tempActionVO = new ActionVO(cursor.getInt(0), cursor.getInt(1), cursor.getLong(2));
            tempArrayList.add(tempActionVO);


        }
        return tempArrayList;
    }
}
