package codingtribe.com;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static codingtribe.com.item_home.CatDbHelper;

public class bar_bar extends AppCompatActivity {

    private BarChart mBarChart; //chart
    private Button btn_pie2, btn_bar2, btn_line2, btn_week;
    DatePickerDialog dpd;

    ArrayList<ActionVO> actionArrayList;
    ActionDB ActionDbHelper;
    ArrayList<StatWeekVO> statWeekArray;
    ArrayList<CategoryVO> catArrayList;
    ArrayList<StatVO> statArray;

    int[] dateNowArr;
    TextView text_week;
    int year;
    int month;
    int date;
    SimpleDateFormat sdf;

    boolean checkChange = false;

    private  Calendar today;
    Calendar cal;

    long firstActionStartTime;
    long lastActionEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_bar);

        mBarChart = (BarChart) findViewById(R.id.chart);
        btn_pie2 = (Button)findViewById(R.id.btn_pie2);
        btn_bar2 = (Button)findViewById(R.id.btn_bar2);
        btn_line2 = (Button)findViewById(R.id.btn_line2);
        btn_week = (Button)findViewById(R.id.btn_week);
        text_week = (TextView)findViewById(R.id.text_week);

        btn_pie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(bar_bar.this, pie_pie.class);
                finish();
                startActivity(it);
            }
        });

        btn_line2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(bar_bar.this, line_line.class);
                finish();
                startActivity(it);
            }
        });

        Intent intent = getIntent();

        today = Calendar.getInstance();

        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH);
        this.date = today.get(Calendar.DATE);
        int check = intent.getIntExtra("yearWeek", 0);

        if(check != 0){
            year = intent.getIntExtra("yearWeek",2018);
            month= intent.getIntExtra("monthWeek",1);
            date = intent.getIntExtra("dateWeek",1);
        }

        dateNowArr = new int[]{year,month,date}; //선택된시간
        text_week = (TextView) findViewById(R.id.text_week);
        text_week.setText((month + 1) + "월 " + date + "일");

        btn_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd = new DatePickerDialog
                        (v.getContext(), // 현재화면의 제어권자
                                new DatePickerDialog.OnDateSetListener() {
                                    public void onDateSet(DatePicker view,
                                                          int year, int monthOfYear, int dayOfMonth) {
                                        Toast.makeText(getApplicationContext()                                               ,
                                                year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일 을 선택했습니다",
                                                Toast.LENGTH_SHORT).show();
                                        checkChange = true;
                                        text_week.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
                                        dateNowArr = new int[]{year,monthOfYear,dayOfMonth};
                                        Intent intent = new Intent(bar_bar.this, bar_bar.class);
                                        intent.putExtra("yearWeek",year);
                                        intent.putExtra("monthWeek",monthOfYear);
                                        intent.putExtra("dateWeek",dayOfMonth);
                                        Log.v("하...",intent.getIntExtra("yearWeek",0)+"");
                                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                                , // 사용자가 날짜설정 후 다이얼로그 빠져나올때
                                  // 호출할 리스너 등록
                                dateNowArr[0], dateNowArr[1], dateNowArr[2]); // 기본값 연월일
                dpd.show();
            }
        });

        ActionDbHelper = new ActionDB(getApplicationContext());
       catArrayList = CatDbHelper.getAllCat();
       actionArrayList = ActionDbHelper.getAllAction(getParent());

        Calendar choiceDate = Calendar.getInstance();
        statArray = new ArrayList<>();

        choiceDate.set(Calendar.YEAR,year);
        choiceDate.set(Calendar.MONTH,month);
        choiceDate.set(Calendar.DATE,date);


        float[][] test = new float[7][];
        float testDate = 1;

       for (int i = 0; i<test.length;i++) {
           long l = choiceDate.getTimeInMillis()-(1000*60*60*24*i);
           Calendar temp = Calendar.getInstance();
           temp.setTimeInMillis(l);
           statArray = takeOneDayData(temp);
           test[i] = new float[statArray.size()];
           for (int j = 0; j <statArray.size() ; j++) {
               Log.v("하루 스탯", statArray.get(j).getCatName()+"");
               test[i][j] = statArray.get(j).getTime();
               testDate += statArray.get(j).getTime();
           }
           for (int k = 0; k < statArray.size(); k++) {
               test[i][k] = test[i][k] * (24 / testDate);
           }
           testDate=0;
       }

        //barchart

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, test[0]));
        entries.add(new BarEntry(1f, test[1]));
        entries.add(new BarEntry(2f, test[2]));
        entries.add(new BarEntry(3f, test[3]));
        entries.add(new BarEntry(4f, test[4]));
        entries.add(new BarEntry(5f, test[5]));
        entries.add(new BarEntry(6f, test[6]));

        /*final List<Integer> colors = new ArrayList<>();
        colors.add(ColorTemplate.COLORFUL_COLORS[0]);
        colors.add(ColorTemplate.COLORFUL_COLORS[1]);
        colors.add(ColorTemplate.COLORFUL_COLORS[2]);
        colors.add(ColorTemplate.COLORFUL_COLORS[3]);
        colors.add(ColorTemplate.COLORFUL_COLORS[4]);*/

        int[] colors = {ColorTemplate.COLORFUL_COLORS[0],ColorTemplate.COLORFUL_COLORS[1],
                ColorTemplate.COLORFUL_COLORS[2],ColorTemplate.COLORFUL_COLORS[3],ColorTemplate.COLORFUL_COLORS[4]};

        XAxis bar_xAxis = mBarChart.getXAxis();
        bar_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bar_xAxis.setTextSize(10f);
        bar_xAxis.setTextColor(Color.BLACK);
        bar_xAxis.setDrawAxisLine(true);
        bar_xAxis.setDrawGridLines(false);
        /*bar_xAxis.setDrawLabels(true);*/
        String[] name = {"월","화","수","목","금","토","일"};
        String[] task = {"공부", "잠", "식사", "이동", "휴식", "취미", "운동", "모임", "일", "봉사"};
        bar_xAxis.setValueFormatter(new IndexAxisValueFormatter(name));

        BarDataSet set = new BarDataSet(entries, "");
        set.setStackLabels(task);

        set.setColors(colors);

        Legend l = mBarChart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.SQUARE); // set what type of form/shape should be used
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis
        l.setEnabled(true);

        BarData bar_data = new BarData(set);
        bar_data.setBarWidth(0.9f); // set custom bar width
        mBarChart.setData(bar_data);
        mBarChart.setFitBars(true); // make the x-axis fit exactly all bars
        mBarChart.invalidate(); // refresh

        mBarChart.animateXY(1000,1000);
    }

    private ArrayList<StatWeekVO> takeWeekData(Calendar choiceDate) {
        return null;
    }

    private ArrayList<StatVO> takeOneDayData(Calendar choiceDate) {

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        ArrayList<StatVO> preprocessingStat = new ArrayList<>();
        ArrayList<StatVO> resultStat = new ArrayList<>();
        ArrayList<StatVO> afterProcessingResultStat = new ArrayList<StatVO>();
        ArrayList<StatVO> sortArray = new ArrayList<StatVO>();

        startDate = choiceDate;
        startDate.set(Calendar.AM_PM,0);
        startDate.set(Calendar.HOUR,0);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.SECOND,0);
        startDate.set(Calendar.MILLISECOND,1);
        long startDateTimeMillis = startDate.getTimeInMillis();
        startDateTimeMillis += 1000*60*60*24;
        endDate.setTimeInMillis(startDateTimeMillis);

        long stm = startDate.getTimeInMillis();
        long etm = endDate.getTimeInMillis();

        //받아온 날짜의 00시 00분 의 timemillis 반환
        int startActionID=0;
        int lastActionID=0;
        ActionVO firstAction=null;
        ActionVO lastAction = null;


        int count = 0;

        for (int i = 0; i<actionArrayList.size(); i++){
            ActionVO action = actionArrayList.get(i);
            long ntm = action.getStart_time();

            if (actionArrayList.get(i).getStart_time()> stm && actionArrayList.get(i).getStart_time()< etm){
                //하루 사이에 있는 애들 바로 전 아이를 12시부터 세팅하기
                //정확히 하루 사이에 있는 애들

                preprocessingStat.add(new StatVO(action.getCat_name(),ntm,action.getCat_id()));

                Log.v("하루의 사이 행동",action.getAction_id()+" "+timeFormat(ntm)+" "+action.getCat_name()+ntm);
                if(startActionID==0)startActionID = action.getAction_id(); //if 문 내부에 들어왔다면 가장 최초로 입력되는 i 값이다.
                lastActionID =i;
                count++;
            }
        }

        if(count==0){
            //이 if 문 내부에 들어왔다는 것은,  해당 날짜에 검색된 action 이 없다는 것이다. 이 때 세 가지 가능성이 있다.
            //CASE1 : DB 에 아무 자료도 저장되어 있지 않거나,
            //CASE2 : 이전 날에 기록이 시작된 action 이 하루를 넘겨버린 것이 그 케이스이거나,
            //CASE3 : 미래의 날짜를 선택한 경우이다.


            //선택된 날짜 값 세팅
            cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,this.year);
            cal.set(Calendar.MONTH,this.month);
            cal.set(Calendar.DATE,this.date);




            if(ActionDbHelper.getAllAction(getParent()).size() == 0){            //CASE1 의 해결 (DB에 저장된 정보가 없을 때)
                //DB 에 기록된 자료가 아직 없을 경우.

            } else {                                                             //CASE2 의 해결 (이전 날에 기록이 시작된 action 인 경우)
                //DB 에 기록된 자료가 있는 경우.
                ActionDbHelper.getOneActionByTime(cal.getTimeInMillis());
            };
        }


        Log.v("하루구분선","===========");
        if(startActionID!=0){
            firstAction = actionArrayList.get(startActionID-2);
            Log.v("하루의 최초 행동", firstAction.getCat_id()+" "+ firstAction.getCat_name() +" "+ timeFormat(firstAction.getStart_time()));

            //시작시간 설정
            cal = Calendar.getInstance();
            cal.setTimeInMillis(firstAction.getStart_time()+1000*60*60*24);
            int ampm =0;
            int hour = 0;
            int minute = 0;
            int second = 0;
            int milliSec= 1;
            cal.set(Calendar.AM_PM, ampm);
            cal.set(Calendar.HOUR,hour);
            cal.set(Calendar.MINUTE,minute);
            cal.set(Calendar.SECOND,second);
            cal.set(Calendar.MILLISECOND,milliSec);

            firstActionStartTime = cal.getTimeInMillis();
            Log.v("하루의 최초 행동의 시작 시간", timeFormat(firstActionStartTime)+"");
        }
        if(lastActionID!=0){
            lastAction = actionArrayList.get(lastActionID);

            //현재 시간의 날짜정보(365일로 표현) 가져오고 년도 정보 가져오기
            int nowDay365 = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
            int nowYear = Calendar.getInstance().get(Calendar.YEAR);

            //해당 액션의 날짜정보(365일로 표현) 가져오고 년도 정보 가져오기
            cal = Calendar.getInstance();
            cal.setTimeInMillis(lastAction.getStart_time());
            int actionDay365 = cal.get(Calendar.DAY_OF_YEAR);
            int actionYear = cal.get(Calendar.YEAR);

            //해당 액션이 오늘인지 판단
            boolean isToday = (nowDay365 == actionDay365) && (nowYear == actionYear);

            //마지막 액션의 끝시간 설정
            if(isToday){
                //해당 액션이 오늘일 경우 현재 시간을 기준으로 마지막 시간을 세팅
                cal = Calendar.getInstance();

                int ampm = cal.get(Calendar.AM_PM);
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                int milliSec = cal.get(Calendar.MILLISECOND);
                cal.set(Calendar.HOUR, hour);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND,second);
                cal.set(Calendar.MILLISECOND,milliSec);

                lastActionEndTime = cal.getTimeInMillis();
            } else {
                //해당 액션이 오늘이 아닐 경우 자정을 끝으로 지정
                cal = Calendar.getInstance();
                cal.setTimeInMillis(lastAction.getStart_time());

                int ampm = 1;
                int hour = 11;
                int minute = 59;
                int second = 59;
                int milliSec = 0;
                cal.set(Calendar.AM_PM, ampm);
                cal.set(Calendar.HOUR, hour);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND,second);
                cal.set(Calendar.MILLISECOND,milliSec);


                lastActionEndTime = cal.getTimeInMillis();
            }
            Log.v("하루구분선","===========");
            Log.v("하루의 마지막 행동", lastAction.getCat_id()+" "+lastAction.getCat_name()+ " "+timeFormat(lastAction.getStart_time()));
            Log.v("하루의 마지막 행동의 끝 시간", timeFormat(lastActionEndTime)+"");
            Log.v("하루 이 행동을 한 시간",lastAction.getStart_time()+"호"+lastActionEndTime);
            Log.v("하루 이 행동을 한 시간",(lastActionEndTime-lastAction.getStart_time())/(60*1000)+"");
            Log.v("하루 이 행동을 한 시간",timeFormat(lastActionEndTime-lastAction.getStart_time()));

            //preprocessingStat 에 하루 최초 자료 넣기
            preprocessingStat.add(0,new StatVO(firstAction.getCat_name(),firstActionStartTime,firstAction.getCat_id()));

            for (int i = 0 ; i<preprocessingStat.size();i++){
                if(i+1!=preprocessingStat.size()){
                    resultStat.add(new StatVO(preprocessingStat.get(i).getCatName(),(preprocessingStat.get(i+1).getTime()-preprocessingStat.get(i).getTime())/(60*1000),preprocessingStat.get(i).getCat_id()));
                    Log.v("하루 한 일",(preprocessingStat.get(i+1).getTime()-preprocessingStat.get(i).getTime())/(60*1000)+preprocessingStat.get(i).getCatName());
                }else{
                    resultStat.add(new StatVO(preprocessingStat.get(i).getCatName(),(lastActionEndTime - lastAction.getStart_time())/(60*1000),preprocessingStat.get(i).getCat_id()));
                    Log.v("하루 한 일",(lastActionEndTime - lastAction.getStart_time())/(60*1000)+preprocessingStat.get(i).getCatName());
                }
            }

            Set<String> set = new HashSet<String>();

            for (StatVO statVO:resultStat) {
                set.add(statVO.getCat_id()+"");
            }

            int catId;
            String catName;


            for(Iterator i = set.iterator(); i.hasNext();){
                catId = Integer.parseInt(i.next().toString());
                catName = CatDbHelper.getCatName(catId);
                afterProcessingResultStat.add(new StatVO(catName,0,catId));

            }

            String tempCatName;
            long tempTime;
            int tempCatID;

            int[] catArr;
            long settedTime;

            for(StatVO stat:resultStat){
                tempCatName = stat.getCatName();
                tempTime = stat.getTime();
                tempCatID = stat.getCat_id();

                for(StatVO cat : afterProcessingResultStat){
                    if(cat.getCat_id()==stat.getCat_id()){
                        settedTime = cat.getTime()+stat.getTime();
                        cat.setTime(settedTime);
                    }
                }


                sortArray = afterProcessingResultStat;
                StatVO tempVO;
                Log.v("정렬사이즈",sortArray.size()+"");


            }
        }

        Compare comp = new Compare();
        Collections.sort(afterProcessingResultStat,comp);

        Log.v("하루액션사이즈",afterProcessingResultStat.size()+"");
        return afterProcessingResultStat;
    }

    private String timeFormat(long firstActionStartTime) {
        sdf = new SimpleDateFormat("yyyy MM dd aa HH:mm");
        Date date = new Date(firstActionStartTime);

        return sdf.format(date);
    }

    class Compare implements Comparator<StatVO>{

        @Override
        public int compare(StatVO stat1, StatVO stat2) {

            long t1 = stat1.getTime();
            long t2 = stat2.getTime();

            if(t1>t2){
                return -1;
            } else {
                return 1;
            }
        }
    }

}
