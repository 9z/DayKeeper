package codingtribe.com;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class item_write extends Fragment implements MonthLoader.MonthChangeListener, WeekView.EmptyViewClickListener, WeekView.EventLongPressListener {

    private WeekView mWeekView;
    private ArrayList<WeekViewEvent> mNewEvents;
    private int check;
    private FloatingActionButton fab;
    Calendar present_time;
    Calendar next_time;
    TimePickerDialog tpd;
    DatePickerDialog dpd;
    Builder time_builder;
    Builder builder;

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

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_item_write, container, false);


        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) v.findViewById(R.id.weekView);

        // Floating Action Button을 리스트 뷰에 적용
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.show();


        // 이벤트 적용
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"야호",Toast.LENGTH_SHORT).show();

                present_time = new GregorianCalendar();
                present_time.add(Calendar.HOUR, -2);
                next_time = (Calendar) present_time.clone();
                next_time.add(Calendar.MINUTE, -75);

                Calendar today = Calendar.getInstance();

                final CharSequence[] items = {"일", "공부", "휴식"};
                Builder builder = new Builder(v.getContext());
                builder.setTitle("카테고리를 선택하세요.")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int item) {
                                choice_item = item;
                                Toast.makeText(getActivity(), items[item], Toast.LENGTH_SHORT).show();
                                final String select = items[item].toString();
                            }
                        });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                builder.setPositiveButton("완료", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        choice_event = new WeekViewEvent(1, items[choice_item].toString(), next_time, present_time);
                        switch (choice_item) {
                            case 0:
                                choice_event.setColor(getActivity().getResources().getColor(R.color.colorAccent));
                                break;
                            case 1:
                                choice_event.setColor(getActivity().getResources().getColor(R.color.colorPrimary));
                                break;
                            case 2:
                                choice_event.setColor(getActivity().getResources().getColor(R.color.common_google_signin_btn_text_light));
                                break;
                            case 3:
                                choice_event.setColor(getActivity().getResources().getColor(R.color.common_google_signin_btn_text_dark_pressed));
                                break;
                        }

                        mNewEvents.add(choice_event);
                        mWeekView.notifyDatasetChanged();

                    }
                });
                builder.create().show();

                tpd =
                        new TimePickerDialog(v.getContext(),
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view,
                                                          int hourOfDay, int minute) {
                                        Toast.makeText(getActivity(),
                                                hourOfDay + "시 " + minute + "분 을 선택했습니다",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }, // 값설정시 호출될 리스너 등록
                                today.get(Calendar.HOUR), today.get(Calendar.MINUTE), false); // 기본값 시분 등록
                // true : 24 시간(0~23) 표시
                // false : 오전/오후 항목이 생김
                tpd.show();

                dpd = new DatePickerDialog
                        (v.getContext(), // 현재화면의 제어권자
                                new DatePickerDialog.OnDateSetListener() {
                                    public void onDateSet(DatePicker view,
                                                          int year, int monthOfYear, int dayOfMonth) {
                                        Toast.makeText(getActivity(),
                                                year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일 을 선택했습니다",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                                , // 사용자가 날짜설정 후 다이얼로그 빠져나올때
                                //    호출할 리스너 등록
                                today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE)); // 기본값 연월일
                dpd.show();

            }
        });


        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set up empty view click listener.
        mWeekView.setEmptyViewClickListener(this);

        mWeekView.setEventLongPressListener(this);

        // Initially, there will be no events on the week view because the user has not tapped on
        // it yet.
        Calendar cal1 = new GregorianCalendar();

        Calendar endTime1 = (Calendar) cal1.clone();
        endTime1.add(Calendar.MINUTE, 60);
        Calendar endTime2 = (Calendar) endTime1.clone();
        endTime2.add(Calendar.MINUTE, 60);

        Calendar endTime3 = (Calendar) endTime1.clone();
        endTime3.add(Calendar.MINUTE, -60);


        WeekViewEvent event1 = new WeekViewEvent(244, "olleh", endTime1, endTime2);
        mNewEvents = new ArrayList<WeekViewEvent>();

        //event1.setColor(v.getResources().getColor(Color.CYAN));
        mNewEvents.add(event1);
        mNewEvents.add(new WeekViewEvent(1, "success?", endTime3, endTime1));


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
    int choice_item;
    int choice_time;
    WeekViewEvent choice_event;
    EditText name;

    @Override
    public void onEmptyViewClicked(final Calendar time) {
        // Set the new event with duration one hour.
        final Calendar endTime = (Calendar) time.clone();
        //updateDetail(time, endTime);

        //시간 옵션
        final CharSequence[] time_item = {"10", "30", "60","기타"};
        time_builder = new Builder(this.getContext());
        time_builder.setTitle("카테고리를 선택하세요.")
                .setSingleChoiceItems(time_item, 0, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int item) {
                        choice_time = item;
                        Toast.makeText(getActivity(), time_item[item], Toast.LENGTH_SHORT).show();
                        final String select = time_item[item].toString();
                    }
                });
        time_builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        time_builder.setPositiveButton("완료", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                switch (choice_time){
                    case 0:
                        endTime.add(Calendar.MINUTE, 10);
                        builder.create().show();
                        break;
                    case 1:
                        endTime.add(Calendar.MINUTE, 30);
                        builder.create().show();
                        break;
                    case 2:
                        endTime.add(Calendar.MINUTE, 60);
                        builder.create().show();
                        break;
                    case 3:
                        endTime.add(Calendar.MINUTE, 120);
                        builder.create().show();
                        break;
                }
            }
        });
        time_builder.create().show();

        //한 일 옵션
        final CharSequence[] items = {"일", "공부", "휴식","수면"};
       builder = new Builder(this.getContext());
        builder.setTitle("카테고리를 선택하세요.")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int item) {
                        choice_item = item;
                        Toast.makeText(getActivity(), items[item], Toast.LENGTH_SHORT).show();
                        final String select = items[item].toString();
                    }
                });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        builder.setPositiveButton("완료", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                switch (choice_item) {
                    case 0:
                        choice_event = new WeekViewEvent(1, items[choice_item].toString(), time, endTime);
                        choice_event.setColor(getActivity().getResources().getColor(R.color.dasol6));
                        break;
                    case 1:
                        choice_event = new WeekViewEvent(1, items[choice_item].toString(), time, endTime);
                        choice_event.setColor(getActivity().getResources().getColor(R.color.dasol1));
                        break;
                    case 2:
                        choice_event = new WeekViewEvent(1, items[choice_item].toString(), time, endTime);
                        choice_event.setColor(getActivity().getResources().getColor(R.color.dasol4));
                        break;
                    case 3:
                        choice_event = new WeekViewEvent(1, items[choice_item].toString(), time, endTime);
                        choice_event.setColor(getActivity().getResources().getColor(R.color.dasol2));
                        break;
                }
                mNewEvents.add(choice_event);
                mWeekView.notifyDatasetChanged();

            }
        });

       /* Builder alert = new Builder(this.getContext());
        alert.setTitle("원하시는 항목을 선택하세요.");
        alert.setMessage("Plz, input yourname");
        final EditText name = new EditText(this.getContext());
        alert.setView(name);
        alert.setPositiveButton("완료", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String username = name.getText().toString();

            }
        });
        alert.setNegativeButton("취소",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();*/



        /*PopupMenu p = new PopupMenu(
                getActivity(), // 현재 화면의 제어권자
                getView()); // anchor : 팝업을 띄울 기준될 위젯
        p.getMenuInflater().inflate(R.menu.item_list_menu, p.getMenu());
        // 이벤트 처리
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getActivity(),
                        "팝업메뉴 이벤트 처리 - "
                                + item.getTitle(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        p.show(); // 메뉴를 띄우기*/

       /* AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Share link");
        View selectContactView = getLayoutInflater().inflate(R.layout.contact_select_layout, null);
        builder.setView(selectContactView);

        ListView contactsListView = (ListView)selectContactView.findViewById(R.id.selectContactListView);

        CursorAdapter adapter = new CursorAdapter(this, cursor, true)
        {

            @Override
            public void bindView(View view, Context arg1, Cursor cursor)
            {
                final long id = cursor.getLong(cursor.getColumnIndex(Contacts._ID));
                final String displayName = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME));

                TextView displayNameTextView = (TextView)view.findViewById(R.id.displayNameTextView);
                final CheckBox selectContactCheckBox = (CheckBox)view.findViewById(R.id.selectContactCheckBox);

                displayNameTextView.setText(displayName);

                boolean isChecked = selectedContactsMap.containsKey(id)?
                        selectedContactsMap.get(id):false;

                selectContactCheckBox.setChecked(isChecked);

                selectContactCheckBox.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v)
                    {
                        boolean isChecked = selectContactCheckBox.isChecked();
                        if(!isChecked && selectedContactsMap.containsKey(id))
                        {
                            Log.d(TAG, "Remove id: "+id+": "+displayName);
                            selectedContactsMap.remove(id);
                        }
                        else
                        {
                            Log.d(TAG, "Select id: "+id+": "+displayName+" : "+isChecked);
                            selectedContactsMap.put(id, isChecked);
                        }
                    }
                });

            }

            @Override
            public View newView(Context context, Cursor arg1, ViewGroup arg2)
            {
                View selectContactItemView = getLayoutInflater().inflate(R.layout.contact_select_item_layout, null);
                return selectContactItemView;
            }

        };

        contactsListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long id)
            {
                WebViewerFragment webViewerFrag = (WebViewerFragment)
                        getFragmentManager().findFragmentById(R.id.web_viewer_fragment);

                String currentLink = null;
                if (webViewerFrag != null)
                {
                    currentLink = webViewerFrag.getCurrentLink();
                    long _id = (Long)view.getTag();
                    share(_id, currentLink);

                }
                else
                {
                    Toast.makeText(MainActivity.this,"No link is found", Toast.LENGTH_LONG).show();
                }

                shareDialog.dismiss();

            }
        });
        contactsListView.setAdapter(adapter);

        builder.setView(selectContactView);
        builder.setPositiveButton("Share!", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        builder.setNegativeButton("Cancel", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,":-(",Toast.LENGTH_SHORT).show();
            }
        });

        shareDialog = builder.create();*/





               /* AlertDialog.Builder builder = new AlertDialog.Builder(item_write.newInstance(mPosition).getContext()); //MainActivity.this 더 많은 정보
                builder.setTitle("삭제알림");
                builder.setMessage("삭제하시겠습니까?");
                builder.setNegativeButton("예", new DialogInterface.OnClickListener() {

                 //왼쪽 가운데 오른쪽
                builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() { // Positive, Negative, middle
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss(); // 사라져라
                    }
                }

                builder.show();*/


        // Create a new event.
        //WeekViewEvent event = new WeekViewEvent(20, "New event", time, endTime);

        // mNewEvents.add(event);

        // Refresh the week view. onMonthChange will be called again.
        // mWeekView.notifyDatasetChanged();


    }

    private void updateDetail(Calendar time, Calendar endTime) {
        /*Intent intent = new Intent(getActivity(), item_write_input.class);
        long this_time = time.getTimeInMillis(); // Calendar > long
        long this_endTime = endTime.getTimeInMillis();
        intent.putExtra("time", this_time);
        intent.putExtra("endTime", this_endTime);
        intent.putExtra("position",mPosition);
        startActivity(intent);*/

    }


    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }
}
