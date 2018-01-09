package codingtribe.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class item_predict extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_predict);

//predict 작성 시작
//서버에서 String 데이터를 전송 받을 때
//    private void receiveObject(JSONObject data) {
//
//        try {
//            mReceiveTv.setText(data.toString());
//            mReceiveNationTv.setText("nation : " + data.getString("nation"));
//            mReceiveNameTv.setText("name : " + data.getString("nation"));
//            mReceiveAddressTv.setText("address : " + data.getString("address"));
//            mReceiveAgeTv.setText("age : " + data.getString("age"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//
//        // 서버에서 배열 데이터를 전송 받을 때
//
//    private void receiveArray(String dataObject) {
//        recyclerView.setVisibility(View.VISIBLE);
//        objectResultLo.setVisibility(View.GONE);
//        mItems.clear();
//        try {
//// String 으로 들어온 값 JSONObject 로 1차 파싱
//            JSONObject wrapObject = new JSONObject(dataObject);
//
//// JSONObject 의 키 "list" 의 값들을 JSONArray 형태로 변환
//            JSONArray jsonArray = new JSONArray(wrapObject.getString("list"));
//            for (int i = 0; i < jsonArray.length(); i++) {
//// Array 에서 하나의 JSONObject 를 추출
//                JSONObject dataJsonObject = jsonArray.getJSONObject(i);
//// 추출한 Object 에서 필요한 데이터를 표시할 방법을 정해서 화면에 표시
//// 필자는 RecyclerView 로 데이터를 표시 함
//                mItems.add(new Item(dataJsonObject.getString("nation") + i, dataJsonObject.getString("name") + i,
//                        dataJsonObject.getString("address") + i, dataJsonObject.getString("age")));
//            }
//// Recycler Adapter 에서 데이터 변경 사항을 체크하라는 함수 호출
//            adapter.notifyDataSetChanged();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}








