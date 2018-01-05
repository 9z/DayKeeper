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

        btn_statics = (Button)getView().findViewById(R.id.btn_statics);
        btn_predict = (Button)getView().findViewById(R.id.btn_predict);
        btn_type = (Button)getView().findViewById(R.id.btn_type);

        btn_statics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDetail();
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

        return inflater.inflate(R.layout.activity_item_analysis2, container, false);

    }

    private void updateDetail() {

        Intent intent = new Intent(getActivity(), inherit_static.class);
        startActivity(intent);
    }


//    public void goToAttract(View v)
//    {
//        btn_statics.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), inherit_static.class);
//                startActivity(intent);
//            }
//        });
//    }
}
