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
import java.util.List;

import static codingtribe.com.item_home.CatDbHelper;

public class line_line extends AppCompatActivity implements DialogInterface.OnMultiChoiceClickListener{

    private LineChart mLineChart;
    private Button btn_pie3, btn_bar3, btn_line3, btn_month;
    TextView text_month;

    //ArrayList<ActionVO> actionArrayList;
    //ActionDB ActionDbHelper;
    //ArrayList<StatMonthVO> statArray;
    //ArrayList<CategoryVO> catArrayList;

    int year;
    int month;
    int date;
    SimpleDateFormat sdf;

    ArrayList<String> showCat;
    ArrayList<Boolean> showCatCheck;
    ArrayList<String> selectCat;

    int choice_item;

    ArrayList<Integer> checkArray = new ArrayList<>();

    @Override

    protected Dialog onCreateDialog(int id) {

        // TODO Auto-generated method stub
        final String[] items = {"공부", "잠", "식사", "이동", "휴식","취미","운동","모임","일","봉사"};
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

        //ActionDbHelper = new ActionDB(getApplicationContext());
        //catArrayList = CatDbHelper.getAllCat();
        //actionArrayList = ActionDbHelper.getAllAction(getParent());

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

        text_month = (TextView) findViewById(R.id.text_month);
        text_month.setText(year + "년 " + (month + 1) + "월 " + date + "일");

        //statArray = takeMonthData(selectCat);

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
        setcase6.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase6.setColors(ColorTemplate.COLORFUL_COLORS[2]);
        setcase6.setCircleColor(ColorTemplate.COLORFUL_COLORS[2]);

        LineDataSet setcase8 = new LineDataSet(case8, "모임");
        setcase6.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase6.setColors(ColorTemplate.COLORFUL_COLORS[3]);
        setcase6.setCircleColor(ColorTemplate.COLORFUL_COLORS[3]);

        LineDataSet setcase9 = new LineDataSet(case9, "일");
        setcase6.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase6.setColors(ColorTemplate.PASTEL_COLORS[0]);
        setcase6.setCircleColor(ColorTemplate.PASTEL_COLORS[0]);

        LineDataSet setcase10 = new LineDataSet(case10, "봉사");
        setcase6.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase6.setColors(ColorTemplate.PASTEL_COLORS[1]);
        setcase6.setCircleColor(ColorTemplate.PASTEL_COLORS[1]);

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
                    break;
                case "모임":
                    break;
                case "일":
                    break;
                case "봉사":
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
}
