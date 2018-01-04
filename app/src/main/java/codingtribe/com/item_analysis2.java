package codingtribe.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class item_analysis2 extends AppCompatActivity {

    Button btn_statics, btn_predict, btn_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_analysis2);

        btn_statics = (Button)findViewById(R.id.btn_statics);
        btn_predict = (Button)findViewById(R.id.btn_predict);
        btn_type = (Button)findViewById(R.id.btn_type);

        btn_statics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_statics = new Intent(item_analysis2.this, inherit_static.class);
                startActivity(it_statics);
            }
        });

        btn_predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
