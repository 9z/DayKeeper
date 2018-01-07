package codingtribe.com;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class item_analysis2 extends Fragment {


    Button btn_statics, btn_predict, btn_type;
    private int mPosition;

    static item_analysis2 newInstance(int position) {
        item_analysis2 f = new item_analysis2();	//객체 생성
        Bundle args = new Bundle();					//해당 fragment에서 사용될 정보 담을 번들 객체
        args.putInt("position", position);				//포지션 값을 저장
        f.setArguments(args);							//fragment에 정보 전달.
        return f;											//fragment 반환
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments() != null ? getArguments().getInt("position") : 0;	// 뷰페이저의 position값을  넘겨 받음
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_item_analysis2, container, false);	//미리 알고 있는 레이아웃을 inflate 한다.

        btn_statics = (Button)v.findViewById(R.id.button1);
        btn_predict = (Button)v.findViewById(R.id.button2);
        btn_type = (Button)v.findViewById(R.id.button3);


        btn_statics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"응답",Toast.LENGTH_SHORT).show();
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

        return v;

    }

    private void updateDetail() {

        Intent intent = new Intent(getActivity(), pie_pie.class);
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
