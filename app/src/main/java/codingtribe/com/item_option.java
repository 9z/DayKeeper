package codingtribe.com;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class item_option extends Fragment {

    private int mPosition;
    ArrayList<String> array = new ArrayList<>();
    ListView lv;

    static item_option newInstance(int position) {
        item_option f = new item_option();	//객체 생성
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_item_option, container, false);

        lv = (ListView)v.findViewById(R.id.list_view);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_option_list);
        lv.setAdapter(adapter);

        adapter.add("개발자 한마디");
        adapter.add("버전 정보");
        adapter.notifyDataSetChanged(); // 어댑터새로고침

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),adapter.getItem(position)+"",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
