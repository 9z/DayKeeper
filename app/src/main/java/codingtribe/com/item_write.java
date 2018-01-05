package codingtribe.com;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class item_write extends Fragment implements MonthLoader.MonthChangeListener, WeekView.EmptyViewClickListener {

    private WeekView mWeekView;
    private ArrayList<WeekViewEvent> mNewEvents;

    private int mPosition;

    static item_write newInstance(int position) {
        item_write f = new item_write();    //객체 생성
        Bundle args = new Bundle();                    //해당 fragment에서 사용될 정보 담을 번들 객체
        args.putInt("position", position);                //포지션 값을 저장
        f.setArguments(args);                            //fragment에 정보 전달.
        return f;                                            //fragment 반환
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments() != null ? getArguments().getInt("position") : 0;    // 뷰페이저의 position값을  넘겨 받음
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_item_write, container, false);

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) v.findViewById(R.id.weekView);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set up empty view click listener.
        mWeekView.setEmptyViewClickListener(this);

        // Initially, there will be no events on the week view because the user has not tapped on
        // it yet.
        Calendar cal1  = new GregorianCalendar();

        Calendar startOfMonth = Calendar.getInstance();
        startOfMonth.set(Calendar.YEAR, 2018);
        startOfMonth.set(Calendar.MONTH, 2 - 1);
        startOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        startOfMonth.set(Calendar.HOUR_OF_DAY, 3);
        startOfMonth.set(Calendar.MINUTE, 40);
        startOfMonth.set(Calendar.SECOND, 0);
        startOfMonth.set(Calendar.MILLISECOND, 0);
        Calendar endOfMonth = (Calendar) startOfMonth.clone();
        endOfMonth.set(Calendar.DAY_OF_MONTH, endOfMonth.getMaximum(Calendar.DAY_OF_MONTH));
        endOfMonth.set(Calendar.HOUR_OF_DAY, 23);
        endOfMonth.set(Calendar.MINUTE, 59);
        endOfMonth.set(Calendar.SECOND, 59);

        Calendar endTime = (Calendar) cal1.clone();
        endTime.add(Calendar.MINUTE, 60);



        mNewEvents = new ArrayList<WeekViewEvent>();
        mNewEvents.add(new WeekViewEvent(20, "olleh",,));
        return v;
    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with the events that was added by tapping on empty view.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        ArrayList<WeekViewEvent> newEvents = getNewEvents(newYear, newMonth);
        events.addAll(newEvents);
        return events;
    }

    /**
     * Get events that were added by tapping on empty view.
     *
     * @param year  The year currently visible on the week view.
     * @param month The month currently visible on the week view.
     * @return The events of the given year and month.
     */
    private ArrayList<WeekViewEvent> getNewEvents(int year, int month) {

        // Get the starting point and ending point of the given month. We need this to find the
        // events of the given month.
        Calendar startOfMonth = Calendar.getInstance();
        startOfMonth.set(Calendar.YEAR, year);
        startOfMonth.set(Calendar.MONTH, month - 1);
        startOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        startOfMonth.set(Calendar.HOUR_OF_DAY, 0);
        startOfMonth.set(Calendar.MINUTE, 0);
        startOfMonth.set(Calendar.SECOND, 0);
        startOfMonth.set(Calendar.MILLISECOND, 0);
        Calendar endOfMonth = (Calendar) startOfMonth.clone();
        endOfMonth.set(Calendar.DAY_OF_MONTH, endOfMonth.getMaximum(Calendar.DAY_OF_MONTH));
        endOfMonth.set(Calendar.HOUR_OF_DAY, 23);
        endOfMonth.set(Calendar.MINUTE, 59);
        endOfMonth.set(Calendar.SECOND, 59);

        // Find the events that were added by tapping on empty view and that occurs in the given
        // time frame.
        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : mNewEvents) {
            if (event.getEndTime().getTimeInMillis() > startOfMonth.getTimeInMillis() &&
                    event.getStartTime().getTimeInMillis() < endOfMonth.getTimeInMillis()) {
                events.add(event);
            }
        }
        return events;
    }

    //시간 받는 곳
    @Override
    public void onEmptyViewClicked(Calendar time) {
        // Set the new event with duration one hour.
        Calendar endTime = (Calendar) time.clone();
        endTime.add(Calendar.MINUTE, 60);
        updateDetail(time, endTime);

        // Create a new event.
        //WeekViewEvent event = new WeekViewEvent(20, "New event", time, endTime);

        // mNewEvents.add(event);

        // Refresh the week view. onMonthChange will be called again.
        // mWeekView.notifyDatasetChanged();


    }

    private void updateDetail(Calendar time, Calendar endTime) {
        Intent intent = new Intent(getActivity(), item_write_input.class);
        intent.putExtra("time", time);
        intent.putExtra("endTime", endTime);
        startActivity(intent);
    }

}
