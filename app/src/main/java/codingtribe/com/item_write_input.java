package codingtribe.com;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class item_write_input extends AppCompatActivity {

    private WeekView mWeekView;
    private ArrayList<WeekViewEvent> mNewEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_write_input);

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView)findViewById(R.id.weekView);

        // Initially, there will be no events on the week view because the user has not tapped on
        // it yet.
        mNewEvents = new ArrayList<WeekViewEvent>();



        Calendar cal = Calendar.getInstance();

        Intent intent = getIntent();
        long time = intent.getLongExtra("time",0);
        long endTime = intent.getLongExtra("endTime",0);

        //long > Calendar
        //Calendar time_1 = cal.setTimeInMillis(time);
        //Calendar time_2 = cal.setTimeInMillis(endTime);

        // Create a new event.
        //WeekViewEvent event = new WeekViewEvent(20, "New event", time, endTime);

        //mNewEvents.add(event);

        // Refresh the week view. onMonthChange will be called again.
       // mWeekView.notifyDatasetChanged();
    }
}
