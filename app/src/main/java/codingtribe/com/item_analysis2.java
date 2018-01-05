package codingtribe.com;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class item_analysis2 extends Fragment {

    Button btn_statics, btn_predict, btn_type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.bar_fragment, container, false );
        return view;

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
