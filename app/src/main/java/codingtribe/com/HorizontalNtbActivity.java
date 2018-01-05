package codingtribe.com;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class HorizontalNtbActivity extends Activity {
TextView percentview=null;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_ntb);
        initUI();
        /*int dow=DoDayOfWeek();
        percentview=(TextView)findViewById(R.id.percentview);
        percentview.setText(dow);*/
    }
   /* private int DoDayOfWeek(){
        Calendar cal=Calendar.getInstance();
        String strWeek=null;

        int nweek = cal.get(Calendar.DAY_OF_WEEK);
        //nweek 1->일요일 , 2->월요일 , 3 ->화요일

   return nweek;
    }*/

    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);



        viewPager.setAdapter(new PagerAdapter() {
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

                View v = null;
                if(position==0){
                    v = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_vp, null, false);
                }else if(position==1){
                    v = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_vp, null, false);
                }else if (position==2){
                    //세영 담당
                    v = LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_item_analysis2, null, false);
                }else{
                    v = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_list, null, false);
                }

                ((ViewPager)container).addView(v, 0);

                return v;


            }
        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

       models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[1]))
  //                      .selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("Home")
                        //.badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[0]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("VIEW")
                        //.badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("INFO")
                        //.badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("OPTION")
                        //.badgeTitle("icon")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 2);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
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
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
}
