package codingtribe.com;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class inherit_static extends AppCompatActivity {

    Button btn_pie, btn_bar, btn_line;
//    private PieChart mPieChart; //piechart
//    private BarChart mBarChart; //chart
//    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inherit_statics);

        btn_pie = (Button)findViewById(R.id.btn_pie);
        btn_bar = (Button)findViewById(R.id.btn_bar);
        btn_line = (Button)findViewById(R.id.btn_line);

//        mPieChart = (PieChart) findViewById(R.id.piechart);
//        mBarChart = (BarChart) findViewById(R.id.chart);
//        mLineChart = (LineChart) findViewById(R.id.chart1);
//

//

//
//        //초기 속성값
//        mPieChart.setVisibility(View.VISIBLE);
//        mBarChart.setVisibility(View.INVISIBLE);
//        mLineChart.setVisibility(View.INVISIBLE);


        btn_pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inherit_static.this, pie_fragment.class);
                startActivity(intent);
//                mPieChart.setVisibility(View.VISIBLE);
//                mBarChart.setVisibility(View.INVISIBLE);
//                mLineChart.setVisibility(View.INVISIBLE);
//
//                btn_pie.setTextColor(Color.WHITE);
//                btn_bar.setTextColor(Color.BLACK);
//                btn_line.setTextColor(Color.BLACK);
//                Toast.makeText(getApplicationContext(),"야야",Toast.LENGTH_SHORT).show();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();



            }
        });

        btn_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mPieChart.setVisibility(View.INVISIBLE);
//                mBarChart.setVisibility(View.VISIBLE);
//                mLineChart.setVisibility(View.INVISIBLE);
//
//                btn_pie.setTextColor(Color.BLACK);
//                btn_bar.setTextColor(Color.WHITE);
//                btn_line.setTextColor(Color.BLACK);
            }
        });

        btn_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mPieChart.setVisibility(View.INVISIBLE);
//                mBarChart.setVisibility(View.INVISIBLE);
//                mLineChart.setVisibility(View.VISIBLE);
//
//                btn_pie.setTextColor(Color.BLACK);
//                btn_bar.setTextColor(Color.BLACK);
//                btn_line.setTextColor(Color.WHITE);
            }
        });



    }

}
