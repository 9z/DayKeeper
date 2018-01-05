package codingtribe.com;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class line_line extends AppCompatActivity {

    private LineChart mLineChart;
    private Button btn_pie3, btn_bar3, btn_line3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_line);

        mLineChart = (LineChart)findViewById(R.id.chart1);
        btn_pie3 = (Button)findViewById(R.id.btn_pie3);
        btn_bar3 = (Button)findViewById(R.id.btn_bar3);
        btn_line3 = (Button)findViewById(R.id.btn_line3);

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

        List<Entry> case1 = new ArrayList<>();
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
    }
}
