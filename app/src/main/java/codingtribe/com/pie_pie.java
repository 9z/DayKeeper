package codingtribe.com;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

public class pie_pie extends AppCompatActivity {

    private PieChart mPieChart; //piechart
    private Button btn_pie1, btn_bar1, btn_line1, btn_date;
    DatePickerDialog dpd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_pie);

        mPieChart = (PieChart)findViewById(R.id.piechart);
        btn_pie1 = (Button)findViewById(R.id.btn_pie1);
        btn_bar1 = (Button)findViewById(R.id.btn_bar1);
        btn_line1 = (Button)findViewById(R.id.btn_line1);
        btn_date = (Button)findViewById(R.id.btn_next_month);

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
                                    }
                                }
                                , // 사용자가 날짜설정 후 다이얼로그 빠져나올때
                                //    호출할 리스너 등록
                                today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE)); // 기본값 연월일
                dpd.show();
            }
        });

        //piechart

        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5,10,5,5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);

        mPieChart.setDrawHoleEnabled(false);
        mPieChart.setHoleColor(Color.WHITE);
        mPieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(34f,"공부"));  //라벨
        yValues.add(new PieEntry(23f,"휴식"));
        yValues.add(new PieEntry(35f,"운동"));
        yValues.add(new PieEntry(70f,"수면"));
        yValues.add(new PieEntry(40f,"일"));

        Description description = new Description();
        description.setText("나의 하루"); //라벨
        description.setTextSize(15);
        mPieChart.setDescription(description);

        mPieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션

        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS); //색바꾸는 속성

        PieData pie_data = new PieData((dataSet));
        pie_data.setValueTextSize(10f);
        pie_data.setValueTextColor(Color.YELLOW); //퍼센트 색깔

        mPieChart.setData(pie_data);


    }
}
