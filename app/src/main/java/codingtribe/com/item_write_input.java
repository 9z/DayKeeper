package codingtribe.com;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.provider.SyncStateContract.Helpers.update;

public class item_write_input extends AppCompatActivity {

    private WeekView mWeekView;
    private ArrayList<WeekViewEvent> mNewEvents;
    private Button btn_cancle, btn_finish;
    final int DIALOG_DATE = 1;
    final int DIALOG_TIME = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_write_input);

        btn_cancle = (Button)findViewById(R.id.btn_cancle);
        btn_finish = (Button)findViewById(R.id.btn_finish);
        Button btn_date = (Button)findViewById(R.id.date);
        Button btn_time = (Button)findViewById(R.id.time);

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView)findViewById(R.id.weekView);

        // Initially, there will be no events on the week view because the user has not tapped on
        // it yet.
        mNewEvents = new ArrayList<WeekViewEvent>();



        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Intent intent = getIntent();
        long time1 = intent.getLongExtra("time",0);
        long endTime = intent.getLongExtra("endTime",0);
        final int position = intent.getIntExtra("position", 0);
        Log.v("롱롱1:", String.valueOf(time1));
        Log.v("롱롱2:", String.valueOf(endTime));



        //long > Calendar

        cal1.setTimeInMillis(time1);
        cal2.setTimeInMillis(endTime);

        Log.v("캘린더1:", String.valueOf(cal1));

        cal1.getTime();
        Log.v("캘린더2:", String.valueOf(cal2));

        // Create a new event.
        WeekViewEvent event = new WeekViewEvent(1, "확인해보자", cal1, cal2);

        mNewEvents.add(event);

        // Refresh the week view. onMonthChange will be called again.
       //mWeekView.notifyDatasetChanged();

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = (Fragment)getFragmentManager().findFragmentById(R.layout.activity_item_write);
                //fragment.getArguments();

            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button b = (Button)findViewById(R.id.test);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭시 팝업 메뉴가 나오게 하기
                // PopupMenu 는 API 11 레벨부터 제공한다
                PopupMenu p = new PopupMenu(
                        getApplicationContext(), // 현재 화면의 제어권자
                        v); // anchor : 팝업을 띄울 기준될 위젯
                getMenuInflater().inflate(R.menu.item_list_menu, p.getMenu());
                // 이벤트 처리
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getApplicationContext(),
                                "팝업메뉴 이벤트 처리 - "
                                        + item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                p.show(); // 메뉴를 띄우기
            }
        });



        btn_date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_DATE); // 날짜 설정 다이얼로그 띄우기
            }
        });
        btn_time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_TIME);
            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_list_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ("공부" == item.getTitle())
            Toast.makeText(this, "새 게임 시작은 곧 구현할 예정입니다.", Toast.LENGTH_SHORT).show();
        else if ("일" == item.getTitle())
            Toast.makeText(this, "이어하기는 곧 구현할 예정입니다.", Toast.LENGTH_SHORT).show();
        else if ("휴식" == item.getTitle())
            Toast.makeText(this, "설정은 곧 구현할 예정입니다.", Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        switch(id){
            case DIALOG_DATE :
                DatePickerDialog dpd = new DatePickerDialog
                        (item_write_input.this, // 현재화면의 제어권자
                                new DatePickerDialog.OnDateSetListener() {
                                    public void onDateSet(DatePicker view,
                                                          int year, int monthOfYear,int dayOfMonth) {
                                        Toast.makeText(getApplicationContext(),
                                                year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+"일 을 선택했습니다",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                                , // 사용자가 날짜설정 후 다이얼로그 빠져나올때
                                //    호출할 리스너 등록
                                2015, 6, 21); // 기본값 연월일
                return dpd;
            case DIALOG_TIME :
                TimePickerDialog tpd =
                        new TimePickerDialog(item_write_input.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view,
                                                          int hourOfDay, int minute) {
                                        Toast.makeText(getApplicationContext(),
                                                hourOfDay +"시 " + minute+"분 을 선택했습니다",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }, // 값설정시 호출될 리스너 등록
                                4,19, false); // 기본값 시분 등록
                // true : 24 시간(0~23) 표시
                // false : 오전/오후 항목이 생김
                return tpd;
        }


        return super.onCreateDialog(id);
    }

}
