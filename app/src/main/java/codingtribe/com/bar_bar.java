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
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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
        entries.add(new BarEntry(0f, new float[] { 8,2,4,8,2 }));
        entries.add(new BarEntry(1f, new float[] { 13,2,4,5,0 }));
        entries.add(new BarEntry(2f, new float[] { 8,10,4,0,2 }));
        entries.add(new BarEntry(3f, new float[] { 8,6,4,4,2 }));
        // gap of 2f
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
}
