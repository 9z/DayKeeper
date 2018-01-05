package codingtribe.com;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class bar_bar extends AppCompatActivity {

    private BarChart mBarChart; //chart
    private Button btn_pie2, btn_bar2, btn_line2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_bar);


        mBarChart = (BarChart) findViewById(R.id.chart);
        btn_pie2 = (Button)findViewById(R.id.btn_pie2);
        btn_bar2 = (Button)findViewById(R.id.btn_bar2);
        btn_line2 = (Button)findViewById(R.id.btn_line2);

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
    }
}
