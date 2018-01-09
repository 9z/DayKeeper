package codingtribe.com;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class pie_pie extends AppCompatActivity {

    private PieChart mPieChart; //piechart
    private Button btn_pie1, btn_bar1, btn_line1, btn_date;
    DatePickerDialog dpd;

    ArrayList<ActionVO> actionArrayList;
    ActionDB ActionDbHelper;
    ArrayList<StatVO> statArray;
    CatDB CatDbHelper;
    ArrayList<CategoryVO> catArrayList;

    boolean checkChange = false;
    boolean check = false;

    int[] dateNowArr;
    TextView text_date;

    int year;
    int month;
    int date;
    SimpleDateFormat sdf;

    Calendar cal;

    long firstActionStartTime;
    long lastActionEndTime;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_pie);

        mPieChart = (PieChart)findViewById(R.id.piechart);
        btn_pie1 = (Button)findViewById(R.id.btn_pie1);
        btn_bar1 = (Button)findViewById(R.id.btn_bar1);
        btn_line1 = (Button)findViewById(R.id.btn_line1);
        btn_date = (Button)findViewById(R.id.btn_month);

        btn_bar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(pie_pie.this, bar_bar.class);
                finish();
                startActivity(it);
            }
        });

        btn_line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(pie_pie.this, line_line.class);
                finish();
                startActivity(it);
            }
        });
        final Calendar today = Calendar.getInstance();

        Intent intent = getIntent();

        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        date = Calendar.getInstance().get(Calendar.DATE);
        int check = intent.getIntExtra("year", 0);

       if(check != 0){
            year = intent.getIntExtra("year",2018);
            month= intent.getIntExtra("month",1);
            date = intent.getIntExtra("date",1);
        }

        dateNowArr = new int[]{year,month,date}; //선택된시간
        text_date = (TextView) findViewById(R.id.text_date);
        text_date.setText(year + "년 " + (month + 1) + "월 " + date + "일");

        btn_date.setOnClickListener(new View.OnClickListener() {
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
                                        text_date.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
                                        dateNowArr = new int[]{year,monthOfYear,dayOfMonth};
                                        Intent intent = new Intent(pie_pie.this, pie_pie.class);
                                        intent.putExtra("year",year);
                                        intent.putExtra("month",monthOfYear);
                                        intent.putExtra("date",dayOfMonth);
                                        Log.v("하...",intent.getIntExtra("year",0)+"");
                                        //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        startActivity(intent);
                                        finish();
                                        //       mPieChart.notifyDataSetChanged();
                                       /* check = true;*/
                                    }
                                }
                                , // 사용자가 날짜설정 후 다이얼로그 빠져나올때
                                //    호출할 리스너 등록
                                dateNowArr[0], dateNowArr[1], dateNowArr[2]); // 기본값 연월일
                dpd.show();
            }
        });

        CatDbHelper = new CatDB(getApplicationContext());
        ActionDbHelper = new ActionDB(getApplicationContext());

        catArrayList = CatDbHelper.getAllCat();
        actionArrayList = ActionDbHelper.getAllAction(getParent());

        Calendar choiceDate = Calendar.getInstance();
        statArray = new ArrayList<>();

        choiceDate.set(Calendar.YEAR,year);
        choiceDate.set(Calendar.MONTH,month);
        choiceDate.set(Calendar.DATE,date);
        statArray = takeOneDayData(choiceDate);

        //piechart

        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5,10,5,5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);

        mPieChart.setDrawHoleEnabled(false);
        mPieChart.setHoleColor(Color.WHITE);
        mPieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        for(int i = 0; i<statArray.size();i++){
            yValues.add(new PieEntry(statArray.get(i).getTime(),statArray.get(i).getCatName()));
        }

        /*yValues.add(new PieEntry(34f,"공부"));  //라벨
        yValues.add(new PieEntry(23f,"휴식"));
        yValues.add(new PieEntry(35f,"운동"));
        yValues.add(new PieEntry(70f,"수면"));
        yValues.add(new PieEntry(40f,"일"));*/

        Description description = new Description();
        description.setText("나의 하루"); //라벨
        description.setTextSize(20);
        mPieChart.setDescription(description);

        mPieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션

        PieDataSet dataSet = new PieDataSet(yValues,"");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); //색바꾸는 속성

        PieData pie_data = new PieData((dataSet));
        pie_data.setValueTextSize(15f);
        pie_data.setValueTextColor(Color.YELLOW); //퍼센트 색깔

        mPieChart.setData(pie_data);


    }

    private ArrayList<StatVO> takeOneDayData(Calendar choiceDate) {

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        ArrayList<StatVO> preprocessingStat = new ArrayList<>();
        ArrayList<StatVO> resultStat = new ArrayList<>();
        ArrayList<StatVO> afterProcessingResultStat = new ArrayList<StatVO>();

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
            }
        }

        for(StatVO stat:afterProcessingResultStat){

        }

       return afterProcessingResultStat;
    }

    private String timeFormat(long firstActionStartTime) {
        sdf = new SimpleDateFormat("yyyy MM dd aa HH:mm");
        Date date = new Date(firstActionStartTime);

        return sdf.format(date);
    }
}
