package codingtribe.com;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static codingtribe.com.item_home.CatDbHelper;

public class line_line extends AppCompatActivity implements DialogInterface.OnMultiChoiceClickListener{

    private LineChart mLineChart;
    private Button btn_pie3, btn_bar3, btn_line3, btn_month;
    TextView text_month;

    ArrayList<ActionVO> actionArrayList;
    ActionDB ActionDbHelper;
    ArrayList<StatVO> statArray;
    ArrayList<CategoryVO> catArrayList;

    int year;
    int month;
    int date;
    SimpleDateFormat sdf;

    ArrayList<String> showCat;
    ArrayList<Boolean> showCatCheck;
    ArrayList<String> selectCat;

    Calendar cal;

    long firstActionStartTime;
    long lastActionEndTime;

    int choice_item;

    ArrayList<Integer> checkArray = new ArrayList<>();

    String[] items= {"공부", "잠", "식사", "이동", "휴식","취미","운동","모임","일","봉사"};

    @Override

    protected Dialog onCreateDialog(int id) {

        // TODO Auto-generated method stub
        final boolean[] checkedItems = {false, false, false, false, false, false, false, false, false, false};

        final AlertDialog.Builder builder = new AlertDialog.Builder(line_line.this);
        builder.setTitle("확인하고 싶은 항목을 선택해주세요.");
        // builder.setMessage("메시지");
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // TODO Auto-generated method stub
                // 바뀐 것을 적용한다.
                checkedItems[which] = isChecked;

            }
        });

        // 같은 onclick을 쓰기때문에 DialogInterface를 적어주어야 에러발생하지 않는다.

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast로 현제 체크된 항목 표시하기
                   String str = "";
                    for (int i = 0; i<items.length; i++){
                        if(checkedItems[i]) {
                            checkArray.add(i);
                        }
                    }
                for (int i = 0; i<items.length; i++){
                    if(checkedItems[i]) {
                        str += items[i];
                        if (i <= items.length-1) {
                            str += ",";
                        }
                    }
                }
                    if (checkArray.size()>4){
                        Toast.makeText(getApplicationContext(),"4개만 선택하세요.",Toast.LENGTH_SHORT).show();
                        checkArray.clear();
                    }else if(checkArray.size() == 0){
                        Toast.makeText(getApplicationContext(),"1개 이상 선택하세요.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(line_line.this, str, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(line_line.this, line_line.class);
                        intent.putExtra("check",str);
                        intent.putExtra("change",1);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    }
            }
        });
        return builder.create();
    }
    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

    }
    int checkIt;
    String[] array = {"공부","잠","식사","휴식"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_line);

        mLineChart = (LineChart)findViewById(R.id.chart1);
        btn_pie3 = (Button)findViewById(R.id.btn_pie3);
        btn_bar3 = (Button)findViewById(R.id.btn_bar3);
        btn_line3 = (Button)findViewById(R.id.btn_line3);
        text_month = (TextView)findViewById(R.id.text_month);
        btn_month = (Button)findViewById(R.id.btn_month);

        btn_bar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(line_line.this, bar_bar.class);
                finish();
                startActivity(it);
            }
        });

        btn_pie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(line_line.this, pie_pie.class);
                finish();
                startActivity(it);
            }
        });

        btn_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        ActionDbHelper = new ActionDB(getApplicationContext());
        catArrayList = CatDbHelper.getAllCat();
        actionArrayList = ActionDbHelper.getAllAction(getParent());

        Calendar choiceDate = Calendar.getInstance();
        statArray = new ArrayList<>();

        Intent intent1 = getIntent();

        checkIt = intent1.getIntExtra("change",0);

        if(checkIt != 0){
            String check = intent1.getStringExtra("check");
            Log.v("나와",check);
            checkIt = 0;
            array = check.split(",");
        }

        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        date = Calendar.getInstance().get(Calendar.DATE);

        choiceDate.set(Calendar.YEAR,year);
        choiceDate.set(Calendar.MONTH,month);
        choiceDate.set(Calendar.DATE,date);

        text_month = (TextView) findViewById(R.id.text_month);
        text_month.setText(year + "년 " + (month + 1) + "월 " + date + "일");

        statArray = takeOneDayData(choiceDate);

        /*for(int i=0;i<statArray.size();i++){
            switch (i){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }*/
        float[][][] lineData = new float[4][][]; //라인데이터 뽑아줄 배열

/*        for (int k = 0;k < lineData.length;k++){
            float[][] test = new float[7][]; //일자별 카테고리별 정대시간 누적값
            lineData[k] = new float[items.length][];
            if(k == 0){
                for (int i = 0; i<test.length;i++) {
                    long l = choiceDate.getTimeInMillis()-(1000*60*60*24*i);
                    Calendar temp = Calendar.getInstance();
                    temp.setTimeInMillis(l);
                    statArray = takeOneDayData(temp);
                    test[i] = new float[statArray.size()];
                    for (int j = 0; j <statArray.size() ; j++) {
                        Log.v("하루 스탯", statArray.get(j).getCatName()+"");
                        test[i][j] = statArray.get(j).getTime();mn
                    }
                }
            }else{
                for (int i = 0; i<test.length;i++) {
                    long l = choiceDate.getTimeInMillis()-(1000*60*60*24*i*7*k);
                    Calendar temp = Calendar.getInstance();
                    temp.setTimeInMillis(l);
                    statArray = takeOneDayData(temp);
                    test[i] = new float[statArray.size()];
                    for (int j = 0; j <statArray.size() ; j++) {
                        Log.v("하루 스탯", statArray.get(j).getCatName()+"");
                        test[i][j] = statArray.get(j).getTime();
                    }
                }
            }


        }*/

        List<Entry> case1 = new ArrayList<>();
        List<Entry> case2 = new ArrayList<Entry>();
        List<Entry> case3 = new ArrayList<Entry>();
        List<Entry> case4 = new ArrayList<Entry>();
        List<Entry> case5 = new ArrayList<Entry>();
        List<Entry> case6 = new ArrayList<Entry>();
        List<Entry> case7 = new ArrayList<Entry>();
        List<Entry> case8 = new ArrayList<Entry>();
        List<Entry> case9 = new ArrayList<Entry>();
        List<Entry> case10 = new ArrayList<Entry>();

        case1.add(new Entry(0f, 28f)); //공부
        case1.add(new Entry(1f, 29f));
        case1.add(new Entry(2f, 26f));
        case1.add(new Entry(3f, 24f));

        case2.add(new Entry(0f, 56f)); //잠
        case2.add(new Entry(1f, 58f));
        case2.add(new Entry(2f, 55f));
        case2.add(new Entry(3f, 54f));

        case3.add(new Entry(0f, 20f)); //식사
        case3.add(new Entry(1f, 21f));
        case3.add(new Entry(2f, 22f));
        case3.add(new Entry(3f, 20f));

        case4.add(new Entry(0f, 19f)); //이동
        case4.add(new Entry(1f, 18f));
        case4.add(new Entry(2f, 20f));
        case4.add(new Entry(3f, 21f));

        case5.add(new Entry(0f, 8f)); //휴식
        case5.add(new Entry(1f, 10f));
        case5.add(new Entry(2f, 11f));
        case5.add(new Entry(3f, 9f));

        case6.add(new Entry(0f, 4f)); //취미
        case6.add(new Entry(1f, 3f));
        case6.add(new Entry(2f, 5f));
        case6.add(new Entry(3f, 6f));

        case7.add(new Entry(0f, 8f)); //운동
        case7.add(new Entry(1f, 5f));
        case7.add(new Entry(2f, 11f));
        case7.add(new Entry(3f, 7f));

        case8.add(new Entry(0f, 4f)); //모임
        case8.add(new Entry(1f, 5f));
        case8.add(new Entry(2f, 12f));
        case8.add(new Entry(3f, 9f));

        case9.add(new Entry(0f, 56f)); //일
        case9.add(new Entry(1f, 54f));
        case9.add(new Entry(2f, 53f));
        case9.add(new Entry(3f, 55f));

        case10.add(new Entry(0f, 0f)); //봉사
        case10.add(new Entry(1f, 1f));
        case10.add(new Entry(2f, 0f));
        case10.add(new Entry(3f, 2f));

        LineDataSet setcase1 = new LineDataSet(case1, "공부");
        setcase1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase1.setColors(ColorTemplate.JOYFUL_COLORS[0]);
        setcase1.setCircleColor(ColorTemplate.JOYFUL_COLORS[0]);

        LineDataSet setcase2 = new LineDataSet(case2, "잠");
        setcase2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase2.setColors(ColorTemplate.JOYFUL_COLORS[1]);
        setcase2.setCircleColor(ColorTemplate.JOYFUL_COLORS[1]);

        LineDataSet setcase3 = new LineDataSet(case3, "식사");
        setcase3.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase3.setColors(ColorTemplate.JOYFUL_COLORS[4]);
        setcase3.setCircleColor(ColorTemplate.JOYFUL_COLORS[4]);

        LineDataSet setcase4 = new LineDataSet(case4, "이동");
        setcase4.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase4.setColors(ColorTemplate.JOYFUL_COLORS[3]);
        setcase4.setCircleColor(ColorTemplate.JOYFUL_COLORS[3]);

        LineDataSet setcase5 = new LineDataSet(case5, "휴식");
        setcase5.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase5.setColors(ColorTemplate.COLORFUL_COLORS[0]);
        setcase5.setCircleColor(ColorTemplate.COLORFUL_COLORS[0]);

        LineDataSet setcase6 = new LineDataSet(case6, "취미");
        setcase6.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase6.setColors(ColorTemplate.COLORFUL_COLORS[1]);
        setcase6.setCircleColor(ColorTemplate.COLORFUL_COLORS[1]);

        LineDataSet setcase7 = new LineDataSet(case7, "운동");
        setcase7.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase7.setColors(ColorTemplate.COLORFUL_COLORS[2]);
        setcase7.setCircleColor(ColorTemplate.COLORFUL_COLORS[2]);

        LineDataSet setcase8 = new LineDataSet(case8, "모임");
        setcase8.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase8.setColors(ColorTemplate.COLORFUL_COLORS[3]);
        setcase8.setCircleColor(ColorTemplate.COLORFUL_COLORS[3]);

        LineDataSet setcase9 = new LineDataSet(case9, "일");
        setcase9.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase9.setColors(ColorTemplate.PASTEL_COLORS[0]);
        setcase9.setCircleColor(ColorTemplate.PASTEL_COLORS[0]);

        LineDataSet setcase10 = new LineDataSet(case10, "봉사");
        setcase10.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase10.setColors(ColorTemplate.PASTEL_COLORS[1]);
        setcase10.setCircleColor(ColorTemplate.PASTEL_COLORS[1]);

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        //for (int i = 0;i<)
        for (int i =0; i< array.length;i++){
            switch (array[i]){
                case "공부":
                    dataSets.add(setcase1);
                    break;
                case "잠":
                    dataSets.add(setcase2);
                    break;
                case "식사":
                    dataSets.add(setcase3);
                    break;
                case "이동":
                    dataSets.add(setcase4);
                    break;
                case "휴식":
                    dataSets.add(setcase5);
                    break;
                case "취미":
                    dataSets.add(setcase6);
                    break;
                case "운동":
                    dataSets.add(setcase7);
                    break;
                case "모임":
                    dataSets.add(setcase8);
                    break;
                case "일":
                    dataSets.add(setcase9);
                    break;
                case "봉사":
                    dataSets.add(setcase10);
                    break;
                default:
                    break;
            }
        }
        LineData line_data = new LineData(dataSets);

        mLineChart.setData(line_data);
        mLineChart.invalidate();

        final String[] quarters = new String[]{"1주","2주","3주","4주"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int)value];
            }

        };

        XAxis line_xAxis = mLineChart.getXAxis();
        line_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        line_xAxis.setTextSize(12f);
        line_xAxis.setTextColor(Color.BLACK);
        line_xAxis.setDrawAxisLine(true);
        line_xAxis.setDrawGridLines(false);

        mLineChart.animateXY(1000,1000);
        line_xAxis.setGranularity(1f);
        line_xAxis.setValueFormatter(formatter);

        //항목선택 카테고리 생성
        /*for (int i = 0; i<catArrayList.size();i++){
            showCat.add(catArrayList.get(i).getCategoryName());
            showCatCheck.add(false);
        }*/
    }

    /*private ArrayList<StatMonthVO> takeMonthData(ArrayList<String> selectCat) {
        return null;
    }*/

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
