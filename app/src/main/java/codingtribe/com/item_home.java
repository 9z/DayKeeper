package codingtribe.com;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class item_home extends Fragment {

    ArrayList<CategoryVO> catArrayList;
    ArrayList<ActionVO> actionArrayList;

    CategoryAdapter adapter;
    String id;
    ListView lv;

    int nowThreadTime = 0;
    long startTime = 0;

    View selectedView;

    Thread thread = null;

    SharedPreferences spf_catID = null;
    SharedPreferences spf_startTime = null;

    String name_nowCat;

    TextView tv_nowCat;
    TextView tv_passedTime;
    TextView tv_startTime;

    Button btn_addCat;

    CatDB CatDbHelper;
    ActionDB ActionDbHelper;

    int nowSelectedCatId;

    static int mem_id = 0; //멤버 ID 는 가입되지 않은 경우 0으로 설정하기로 한다. 나중에 구글 로그인 이후 서버에서 받아온 아이디를 사용한다.

    private int mPosition;
    private boolean threadReset;

    static item_home newInstance(int position) {
        item_home f = new item_home();	//객체 생성
        Bundle args = new Bundle();					//해당 fragment에서 사용될 정보 담을 번들 객체
        args.putInt("position", position);				//포지션 값을 저장
        f.setArguments(args);							//fragment에 정보 전달.
        return f;											//fragment 반환
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments() != null ? getArguments().getInt("position") : 0;	// 뷰페이저의 position값을  넘겨 받음
        thread = new Thread(new timeThread());
        thread.start();
    }

    //찬울 : onCreate안에 있던 코드 넣는 곳
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_item_home, container, false);
        //여기!!
        // findViewById 하는법 : v.findViewById
        // Intent 하는법 : new Intent(getgetActivity(), 이동할페이지이름.class);

//        id = getIntent().getStringExtra("id");

        CatDbHelper = new CatDB(getActivity());
        ActionDbHelper = new ActionDB(getActivity());

        catArrayList = CatDbHelper.getAllCat();
        actionArrayList = ActionDbHelper.getAllAction();

        adapter = new CategoryAdapter(getActivity(), R.layout.category, catArrayList, id);
        lv = (ListView) v.findViewById(R.id.lv_category);
        lv.setAdapter(adapter);

        spf_catID = v.getContext().getSharedPreferences("select", MODE_PRIVATE);
        spf_startTime = v.getContext().getSharedPreferences("appStartTime", MODE_PRIVATE);


        name_nowCat = spf_catID.getString("selectCat","");
        nowSelectedCatId = spf_catID.getInt("select", 0);

        startTime = System.currentTimeMillis() - spf_startTime.getLong("appStartTime",0);

        tv_nowCat = (TextView) v.findViewById(R.id.tv_nowCat);

        tv_nowCat.setText(name_nowCat);

        tv_passedTime = (TextView) v.findViewById(R.id.tv_passedTime);

        tv_startTime = (TextView) v.findViewById(R.id.tv_startTime);
        tv_startTime.setText(getTimeInHHmm(spf_startTime.getLong("appStartTime",-9*3600*1000))+"");



        btn_addCat = (Button) v.findViewById(R.id.btn_addCat);

        //카테고리 추가 버튼을 눌렀을 때, 카테고리명을 입력받는 다이얼로그
        btn_addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                alert.setTitle("카테고리 추가");
                alert.setMessage("추가할 카테고리명을 입력해주세요.");

                final EditText name = new EditText(view.getContext());
                alert.setView(name);

                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        CatDbHelper.insert(name.getText()+"", mem_id);
                        catArrayList = CatDbHelper.getAllCat();
                        adapter = new CategoryAdapter(getActivity(), R.layout.category, catArrayList, id);
                        lv.setAdapter(adapter);
                    }
                });

                alert.setNegativeButton("no",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(nowSelectedCatId == catArrayList.get(i).getCat_id()) {
                    Toast.makeText(view.getContext(),"이미 선택한 항목을 기록하고 있습니다.",Toast.LENGTH_SHORT).show();
                } else {

                    if(selectedView!=null){
                        selectedView.setBackgroundColor(Color.WHITE);
                    }

                    selectedView = view;
                    selectedView.setBackgroundColor(Color.LTGRAY);

                    spf_catID.edit().putString("selectCat", catArrayList.get(i).getCategoryName()).commit();
                    tv_nowCat.setText(catArrayList.get(i).getCategoryName());

                    //시간 표현하기
                    long now = System.currentTimeMillis();
                    String formatDate = getTimeInHHmm(now);
                    tv_startTime.setText(formatDate);

                    spf_startTime.edit().putLong("appStartTime", now).commit();
                    nowSelectedCatId = catArrayList.get(i).getCat_id();
                    spf_catID.edit().putInt("select", nowSelectedCatId).commit();

                    ActionDbHelper.insert(nowSelectedCatId,now);
                    ActionDbHelper.getAllAction();

                    threadReset = true;
                }


            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(adapterView.getContext());

                alert.setTitle("\""+ catArrayList.get(i).getCategoryName()+"\" 카테고리 삭제");
                alert.setMessage(catArrayList.get(i).getCategoryName()+" 항목을 삭제하시겠습니까?");

                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        CatDbHelper.delete(catArrayList.get(i).getCat_id());
                        catArrayList = CatDbHelper.getAllCat();
                        adapter = new CategoryAdapter(getActivity(), R.layout.category, catArrayList, id);
                        lv.setAdapter(adapter);
                    }
                });

                alert.setNegativeButton("no",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();

                return true;
            }
        });
        return v;
    }

    @NonNull
    private String getTimeInHHmm(long now) {
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("aa HH:mm");
        String formatDate = "";
        formatDate += sdfNow.format(date);
        return formatDate;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            nowThreadTime = msg.arg1;

            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 /100) / 60;

            int hour = (msg.arg1 % 3600 ) % 24;
            String time = String.format("%03d:%02d:%02d", min,sec,mSec);
            tv_passedTime.setText(time);
        }
    };

    boolean isRunning = true;

    public class  timeThread implements Runnable {
        final int start = (int)startTime/10;
        int i = start;

        @Override
        public void run() {
            while (true) { //계속 값이 올라 갈 것이므로
                while (isRunning) {
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);
                    
                    if(threadReset){
                        i=0;
                        threadReset = false;
                    }
                    
                    try {
                        Thread.sleep(10); //쓰레드가 쉬는 동안에 다른 쓰레드가 들어올수 있다.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }

            }

        }

    }
}
