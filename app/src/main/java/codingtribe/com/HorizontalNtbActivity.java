package codingtribe.com;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class HorizontalNtbActivity extends FragmentActivity {

    TextView percentview = null;
    ProgressBar progressBar = null;
    ImageView run = null;
    int width;


    long now = System.currentTimeMillis();//현재 시간 구하기 msec
    Date date = new Date(now);
    SimpleDateFormat sdfnow = new SimpleDateFormat("HH");
    int time = Integer.parseInt(sdfnow.format(date));//인트형 현재 24시간

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_ntb);
        initUI();

        percentview = (TextView) findViewById(R.id.percentview);//percent 값 나타내는 텍스트 뷰
        progressBar = (ProgressBar) findViewById(R.id.progressBar);//프로그레스바 아이디 가져오기


        int nowpercent = DoDayOfWeek();//몇요일인지 숫자로 꺼내욤

        //화면 해상도 구하는 코드
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;

        percentview.setText(nowpercent + "%");
        progressBar.setProgress(nowpercent);//프로그레스 바 x값주기

        run = (ImageView) findViewById(R.id.run);//달리는 이미지 아이디 가져오기

        RelativeLayout.LayoutParams mLayoutParams =
                (RelativeLayout.LayoutParams) run.getLayoutParams();
        if (0 <= nowpercent && 1 >= nowpercent) {
            mLayoutParams.leftMargin = (int) (width * 0.09);
        } else {
            mLayoutParams.leftMargin = (int) (width * (nowpercent * 0.01)) - (int) (width * 0.07);
        }
        run.setLayoutParams(mLayoutParams);
    }

    private int DoDayOfWeek() {//요일값 %가져오기
        Calendar cal = Calendar.getInstance();
        int nweek = cal.get(Calendar.DAY_OF_WEEK);

        if (nweek == 1) {//nweek 1->월,2->화,3->수,4->목
            nweek = 7;
        } else if (nweek > 1 && nweek <= 7) {
            nweek--;
        }

        nweek = (((nweek - 1) * 24 + time) * 100) / 168;
        return nweek;
    }


    private void initUI() {
        final CustomViewPager viewPager = (CustomViewPager)findViewById(R.id.vp_horizontal_ntb);

        viewPager.setPagingEnabled(false);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int position) {

                View v = null;
                if (position == 0) {
                    return item_home.newInstance(position);
                    //              v = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_vp, null, false);
                } else if (position == 1) {
                    return item_write.newInstance(position);

                    //               v = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_vp, null, false);
                } else if (position == 2) {
                    //세영 담당
                    return item_analysis2.newInstance(position);
                    //                v = LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_item_analysis2, null, false);
                } else {
                    return item_option.newInstance(position);
                    //             v = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_list, null, false);
                }

                //        ((ViewPager)container).addView(v, 0);

                //       return v;
            }


        });

     /*   viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {




            }
        });*/

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.home),
                        Color.parseColor(colors[0]))
                        //                      .selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("홈")
                        //.badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.history),
                        Color.parseColor(colors[1]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("기록")
                        //.badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.analysis),
                        Color.parseColor(colors[2]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("분석")
                        //.badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.settings),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("설정")
                        //.badgeTitle("icon")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0); //처음 화면 띄울때 어떤 화면을 띄워줄지 설정
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                //navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);

                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           // model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
}
