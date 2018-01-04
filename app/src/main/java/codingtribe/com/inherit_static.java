package codingtribe.com;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class inherit_static extends AppCompatActivity {

    Button btn_pie, btn_bar, btn_line;
    private PieChart mPieChart; //piechart
    private BarChart mBarChart; //chart
    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inherit_statics);

        btn_pie = (Button)findViewById(R.id.btn_pie);
        btn_bar = (Button)findViewById(R.id.btn_bar);
        btn_line = (Button)findViewById(R.id.btn_line);

        mPieChart = (PieChart) findViewById(R.id.piechart);
        mBarChart = (BarChart) findViewById(R.id.chart);
        mLineChart = (LineChart) findViewById(R.id.chart1);

        //piechart

        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5,10,5,5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);

        mPieChart.setDrawHoleEnabled(false);
        mPieChart.setHoleColor(Color.WHITE);
        mPieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(34f,"Japan"));  //라벨
        yValues.add(new PieEntry(23f,"USA"));
        yValues.add(new PieEntry(14f,"UK"));
        yValues.add(new PieEntry(35f,"India"));
        yValues.add(new PieEntry(40f,"Russia"));
        yValues.add(new PieEntry(40f,"Korea"));

        Description description = new Description();
        description.setText("세계 국가"); //라벨
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

        //barchart

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, new float[] { 10, 40, 30 }));
        entries.add(new BarEntry(1f, new float[] { 10, 20, 70 }));
        entries.add(new BarEntry(2f, new float[] { 10, 50, 30 }));
        entries.add(new BarEntry(3f, new float[] { 10, 50, 20 }));
        // gap of 2f
        entries.add(new BarEntry(5f, new float[] { 10, 30, 23 }));
        entries.add(new BarEntry(6f, new float[] { 10, 20, 10 }));

        final List<Integer> colors = new ArrayList<>();
        colors.add(ColorTemplate.COLORFUL_COLORS[0]);
        colors.add(ColorTemplate.COLORFUL_COLORS[1]);
        colors.add(ColorTemplate.COLORFUL_COLORS[2]);

        XAxis bar_xAxis = mBarChart.getXAxis();
        bar_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bar_xAxis.setTextSize(10f);
        bar_xAxis.setTextColor(Color.RED);
        bar_xAxis.setDrawAxisLine(true);
        bar_xAxis.setDrawGridLines(false);

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        set.setColors(colors);

        Legend l = mBarChart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.LINE); // set what type of form/shape should be used
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis
        // set custom labels and colors

        /*final String[] quarters = new String[]{"q1","q2","q3","q4","q5","q6","q7"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int)value];
            }

        };*/

        BarData bar_data = new BarData(set);
        bar_data.setBarWidth(0.9f); // set custom bar width
        mBarChart.setData(bar_data);
        mBarChart.setFitBars(true); // make the x-axis fit exactly all bars
        mBarChart.invalidate(); // refresh

        mBarChart.animateXY(1000,1000);

        //linechart

        List<Entry> case1 = new ArrayList<Entry>();
        List<Entry> case2 = new ArrayList<Entry>();

        case1.add(new Entry(0f, 100f));
        case1.add(new Entry(1f, 230f));
        case1.add(new Entry(2f, 130f));
        case1.add(new Entry(3f, 290f));

        case2.add(new Entry(0f, 290f));
        case2.add(new Entry(1f, 100f));
        case2.add(new Entry(2f, 40f));
        case2.add(new Entry(3f, 120f));

        LineDataSet setcase1 = new LineDataSet(case1, "seyoung");
        setcase1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase1.setColors(Color.BLUE);
        setcase1.setCircleColor(Color.BLUE);
        LineDataSet setcase2 = new LineDataSet(case2, "selin");
        setcase2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setcase2.setColors(Color.RED);
        setcase2.setCircleColor(Color.RED);

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setcase1);
        dataSets.add(setcase2);

        LineData line_data = new LineData(dataSets);

        mLineChart.setData(line_data);
        mLineChart.invalidate();


        final String[] quarters = new String[]{"q1","q2","q3","q4"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int)value];
            }

        };

        XAxis line_xAxis = mLineChart.getXAxis();
        line_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        line_xAxis.setTextSize(10f);
        line_xAxis.setTextColor(Color.RED);
        line_xAxis.setDrawAxisLine(true);
        line_xAxis.setDrawGridLines(false);

        mLineChart.animateXY(1000,1000);
        line_xAxis.setGranularity(1f);
        line_xAxis.setValueFormatter(formatter);

        //초기 속성값
        mPieChart.setVisibility(View.VISIBLE);
        mBarChart.setVisibility(View.INVISIBLE);
        mLineChart.setVisibility(View.INVISIBLE);


        btn_pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPieChart.setVisibility(View.VISIBLE);
                mBarChart.setVisibility(View.INVISIBLE);
                mLineChart.setVisibility(View.INVISIBLE);

                btn_pie.setTextColor(Color.WHITE);
                btn_bar.setTextColor(Color.BLACK);
                btn_line.setTextColor(Color.BLACK);
            }
        });

        btn_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPieChart.setVisibility(View.INVISIBLE);
                mBarChart.setVisibility(View.VISIBLE);
                mLineChart.setVisibility(View.INVISIBLE);

                btn_pie.setTextColor(Color.BLACK);
                btn_bar.setTextColor(Color.WHITE);
                btn_line.setTextColor(Color.BLACK);
            }
        });

        btn_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPieChart.setVisibility(View.INVISIBLE);
                mBarChart.setVisibility(View.INVISIBLE);
                mLineChart.setVisibility(View.VISIBLE);

                btn_pie.setTextColor(Color.BLACK);
                btn_bar.setTextColor(Color.BLACK);
                btn_line.setTextColor(Color.WHITE);
            }
        });




    }
}
