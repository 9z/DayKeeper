package codingtribe.com;

import android.app.Fragment;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_write_input);

        btn_cancle = (Button)findViewById(R.id.btn_cancle);
        btn_finish = (Button)findViewById(R.id.btn_finish);

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView)findViewById(R.id.weekView);

        // Initially, there will be no events on the week view because the user has not tapped on
        // it yet.
        mNewEvents = new ArrayList<WeekViewEvent>();



        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Intent intent = getIntent();
        long time = intent.getLongExtra("time",0);
        long endTime = intent.getLongExtra("endTime",0);
        final int position = intent.getIntExtra("position", 0);
        Log.v("롱롱1:", String.valueOf(time));
        Log.v("롱롱2:", String.valueOf(endTime));



        //long > Calendar

        cal1.setTimeInMillis(time);
        cal2.setTimeInMillis(endTime);

        Log.v("캘린더1:", String.valueOf(cal1));
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_list_menu, menu);
        menu.add("공부");
        menu.add("일");
        menu.add("휴식");
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
}
