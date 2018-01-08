package codingtribe.com;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static codingtribe.com.item_home.mem_id;

/**
 * Created by pc-05 on 2018-01-06.
 */

public class CatDB extends SQLiteOpenHelper {

    public CatDB(Context context) {
        super(context, "catDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CAT_INFO (cat_id INTEGER PRIMARY KEY AUTOINCREMENT, cat_name TEXT, mem_id INTEGER, IS_DEL INTEGER DEFAULT 0);");

        String[] basicCat = new String[]{"공부", "잠", "식사", "이동", "휴식", "취미", "운동", "모임", "일", "봉사"};
        for(int iter = 0; iter < basicCat.length ;iter++){
            sqLiteDatabase.execSQL("INSERT INTO CAT_INFO VALUES(null, '"+basicCat[iter]+"',"+mem_id+",null);");
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String cat_name, int mem_id) {

        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO CAT_INFO VALUES(null, '" + cat_name + "', " + mem_id + ", null);");
        db.close();
    }

    public void update(int cat_id, int cat_name_will_be_changed) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 카테고리명 수정
        db.execSQL("UPDATE CAT_INFO SET cat_name=" + cat_name_will_be_changed + " WHERE item='" + cat_id + "';");
        db.close();
    }

    public void delete(int cat_id) {
        SQLiteDatabase db = getWritableDatabase();
        // ## 입력한 항목과 일치하는 행을 삭제하진 않는다! 곧 다음 코드를 쓰는 것이 아니다.
        // db.execSQL("DELETE FROM CAT_INFO WHERE item='" + item + "';");
        // 실제 DB 에서 값은 삭제되지 않고,

        db.execSQL("UPDATE CAT_INFO SET IS_DEL = 1 WHERE cat_id = "+cat_id+";");
        db.close();
    }

    public String getCatName(int cat_id){
        SQLiteDatabase db = getReadableDatabase();

        String catName="";
        Cursor cursor = db.rawQuery("SELECT cat_name FROM CAT_INFO WHERE cat_id = "+cat_id+";", null);
        while(cursor.moveToNext()){
            catName = cursor.getString(0);
        }

        db.close();
        return catName;
    }

    public ArrayList<CategoryVO> getAllCat() {

        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        CategoryVO tempCatVO;
        ArrayList<CategoryVO> tempArrayList = new ArrayList<CategoryVO>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM CAT_INFO;", null);

        while (cursor.moveToNext()) {

//            Log.v("CatDB정보",cursor.getInt(0)+" "+cursor.getString(1)+" "+cursor.getInt(2));
            tempCatVO = new CategoryVO(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), (cursor.getInt(3)==0?false:true));
            if(!tempCatVO.isDel)tempArrayList.add(tempCatVO);

        }


        return tempArrayList;
    }

}
