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
import java.util.List;

public class bar_bar extends AppCompatActivity {

    private BarChart mBarChart; //chart
    private Button btn_pie2, btn_bar2, btn_line2, btn_week;
    DatePickerDialog dpd;

    ArrayList<ActionVO> actionArrayList;
    ActionDB ActionDbHelper;
    ArrayList<StatWeekVO> statArray;
    CatDB CatDbHelper;
    ArrayList<CategoryVO> catArrayList;

    int[] dateNowArr;
    TextView text_week;
    int year;
    int month;
    int date;
    SimpleDateFormat sdf;

    boolean checkChange = false;

    Calendar cal;

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

        final Calendar today = Calendar.getInstance();

        Intent intent = getIntent();

        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        date = Calendar.getInstance().get(Calendar.DATE);

        if(intent != null){
            year = intent.getIntExtra("year",2018);
            month= intent.getIntExtra("month",1);
            date = intent.getIntExtra("date",1);
        }

        dateNowArr = new int[]{year,month,date}; //선택된시간
        text_week = (TextView) findViewById(R.id.text_date);
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
                                        intent.putExtra("year",year);
                                        intent.putExtra("month",monthOfYear);
                                        intent.putExtra("date",dayOfMonth);
                                        Log.v("하...",intent.getIntExtra("year",0)+"");
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

        CatDbHelper = new CatDB(getApplicationContext());
        ActionDbHelper = new ActionDB(getApplicationContext());

        catArrayList = CatDbHelper.getAllCat();
        actionArrayList = ActionDbHelper.getAllAction(getParent());

        Calendar choiceDate = Calendar.getInstance();
        statArray = new ArrayList<>();

        choiceDate.set(Calendar.YEAR,year);
        choiceDate.set(Calendar.MONTH,month);
        choiceDate.set(Calendar.DATE,date);
        statArray = takeWeekData(choiceDate);

        //barchart

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, new float[] { 8,2,4,8,2 }));
        entries.add(new BarEntry(1f, new float[] { 13,2,4,5,0 }));
        entries.add(new BarEntry(2f, new float[] { 8,10,4,0,2 }));
        entries.add(new BarEntry(3f, new float[] { 8,6,4,4,2 }));
        entries.add(new BarEntry(4f, new float[] { 0,0,0,0,0 }));
        entries.add(new BarEntry(5f, new float[] { 8,2,0,12,2 }));
        entries.add(new BarEntry(6f, new float[] { 8,2,4,10,0 }));

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
        String[] task = {"일","공부","취미","수면","운동"};
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
}
