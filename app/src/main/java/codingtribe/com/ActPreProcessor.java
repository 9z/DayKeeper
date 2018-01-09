package codingtribe.com;

import android.app.Activity;
import android.content.Context;
import android.drm.DrmStore;
import android.util.Log;
import android.widget.Switch;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by pc-05 on 2018-01-09.
 */

public class ActPreProcessor {

    ActionDB actionDB;
    StatRowVO statRowVO;

    ArrayList<ActionTimeInfoVO> actTimeInfoVO;
    ArrayList<StatRowVO> statTable;

    long veryFirstTime;
    long veryLastTime;

    Calendar cal;
    Date date;
    private String statTableString;

    public String  makeStatTable(ArrayList<ActionVO> dbArr) throws JSONException {

        actTimeInfoVO = new ArrayList<ActionTimeInfoVO>();
        statTable = new ArrayList<StatRowVO>();

        //DB 에 저장된 최초 시간과 최종시간 불러오기

            //DB에 저장된 최초 시간

            cal = Calendar.getInstance();
            cal.setTimeInMillis(dbArr.get(0).getStart_time());
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0)  ;
            cal.set(Calendar.MILLISECOND,0)  ;
            veryFirstTime = cal.getTimeInMillis();

            //오늘 날짜를 기준으로 어제 날짜의 자정시간
            cal = Calendar.getInstance();
            int dateToday = cal.get(Calendar.DATE);
            cal.set(Calendar.DATE,dateToday-1);
            cal.set(Calendar.AM_PM,1);
            cal.set(Calendar.HOUR,11);
            cal.set(Calendar.MINUTE,59);
            cal.set(Calendar.SECOND,59);
            cal.set(Calendar.MILLISECOND,0);
            veryLastTime = cal.getTimeInMillis();

        //최초시간과 최종시간 확인하기
        date = new Date(veryFirstTime);
        Log.v("최초시간", date.toString());

        date = new Date(veryLastTime);
        Log.v("최종시간", date.toString());

        //최초시간과 최종시간을 기준으로 statTable(timeTable) 생성하기
        for(long time =veryFirstTime; time<veryLastTime;time=time+(1000*60*60)){
            statTable.add(new StatRowVO(time,time+(1000*60*60)));
        }

        //db 액션들의 시작시간의 정보를 00분, 00초로 세팅해서 flag 정보로 가지고 있기
        long[] actIdFlag = new long[dbArr.size()];

        int i = 0;
        long time = 0;
        for(ActionVO act: dbArr){

            time = act.getStart_time();
            cal = Calendar.getInstance();
            cal.setTimeInMillis(time);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);

            actIdFlag[i] = cal.getTimeInMillis();
            i++;
        }

        ArrayList<StatDuplicatedTimeCountVO> dbTimeArr = new ArrayList<>();
        long timeNow =0;
        long timeNext = 0;
        int count = 0;
        for(int j = 0; j<actIdFlag.length;j++){
            count = 1;
            do{
                timeNow = actIdFlag[j];
                if(j+1>=actIdFlag.length)break;
                timeNext = actIdFlag[j+1];
                if(timeNow==timeNext){
                    count++;
                    j++;

                } else {
                    break;
                }
            }while(true);
            dbTimeArr.add(new StatDuplicatedTimeCountVO(timeNow, count));
        }

        //statTable 에 count 적용하기
        for(StatRowVO row : statTable){
            for(StatDuplicatedTimeCountVO timeAndCount : dbTimeArr){
                if(row.getStartTime()==timeAndCount.getTime()){
                    row.setActionCount(timeAndCount.getCount());
                }
            }
        }

        //statTable 에 catName 적용하기 (시간 중복 1회)
        for(StatRowVO row : statTable){
            if(row.getActionCount()==1){
                for(ActionVO vo : dbArr){
                    if(row.getStartTime()<=vo.getStart_time() && vo.getStart_time()<=row.getEndTime()){
                        int minute = getMinute(vo);
                        if(minute<30){
                            row.setCatName(vo.getCat_name());
                        } else {
                            row.setCatName(dbArr.get(vo.action_id-2).getCat_name());
                        }
                    }
                }
            }

            if(row.getActionCount()>1){
                int minuteCount = 0;
                for(int voI = 0; voI < dbArr.size();voI++){

                    int[] minuteArr = new int[row.getActionCount()];
                    if(row.getStartTime()<=dbArr.get(voI).getStart_time() && dbArr.get(voI).getStart_time()<=row.getEndTime()){
                        int startMin = 0;
                        int minLength =0;
                        int max = 0;
                        String maxCat = dbArr.get(voI).getCat_name();
                        for(int rowi=0; rowi<row.getActionCount();rowi++){

                            if(rowi==0){
                                minLength = getMinute(dbArr.get(voI)) -startMin;
                            }

                            minLength = getMinute(dbArr.get(voI)) -startMin;


                            if(!((rowi+1)==row.getActionCount() || rowi ==0)){

                            }

                            if(minLength>max){
                                max = minLength;
                                maxCat = dbArr.get(voI-1).getCat_name();
                            }

                            if((rowi+1)==row.getActionCount()){
                                //마지막 판정

                                minLength = 60- getMinute(dbArr.get(voI));
                                if(minLength>max){
                                    max = minLength;
                                    maxCat = dbArr.get(voI).getCat_name();
                                }
                            }

                            startMin = getMinute(dbArr.get(voI));
//                            Log.v("확인1",dbArr.get(voI).getCat_name()+" "+getDateFormat(dbArr.get(voI).getStart_time()));
                            voI++;
                        }
//                        Log.v("확인",max+" "+maxCat);
                        row.setCatName(maxCat);
                    }
                }

            }
        }

        for(StatRowVO vo : statTable){
            if(vo.getCatName()==null){
                String cat_name = "";
                int cat_id = 0;
                for(int dbi = 0; dbi<dbArr.size();dbi++){
                    if(dbi==0){
                        cat_name = dbArr.get(dbi).getCat_name();
                        cat_id = dbArr.get(dbi).getCat_id();
                    }else{
                        cat_name = dbArr.get(dbi-1).getCat_name();
                        cat_id = dbArr.get(dbi-1).getCat_id();
                    }
                    vo.setCatName(cat_name);
                    vo.setCatid(cat_id);
                    if(dbArr.get(dbi).getStart_time()>vo.getStartTime()){
                        break;
                    };
                }
            }
        }

        //분석 테이블용 ArrayList 만들기
        ArrayList<TimeTableRowVO> timeTable = new ArrayList<>();
        int dayOfWeek;
        int weekOfMonth;
        int isWeekend;
        int hour;
        int month;

        int catId;
        String catName;

        SimpleDateFormat sdf;
        Date date;
        for(StatRowVO vo : statTable){
            long rowTime = vo.getStartTime();
            catId = vo.getCatid();

            cal = Calendar.getInstance();
            cal.setTimeInMillis(rowTime);

            hour = cal.get(Calendar.HOUR_OF_DAY);
//
//            sdf = new SimpleDateFormat("MM", new Locale("en"));
//            sdf.setCalendar(cal);
//            date = new Date(cal.getTimeInMillis());
//            month = sdf.format(date);
            month = cal.get(Calendar.MONTH);


//            sdf = new SimpleDateFormat("EEE", new Locale("en"));
//            sdf.setCalendar(cal);
//            date = new Date(cal.getTimeInMillis());
//            dayOfWeek = sdf.format(date);
            dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

            cal.get(Calendar.WEEK_OF_MONTH);
            weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
            switch (cal.get(Calendar.WEEK_OF_MONTH)){
                case 1:
                    weekOfMonth = 0;
                    break;
                case 2:
                    weekOfMonth = 1;
                    break;
                case 3:
                    weekOfMonth = 2;
                    break;
                case 4:
                    weekOfMonth = 3;
                    break;
                case 5:
                    weekOfMonth = 4;
                    break;
                case 6:
                    weekOfMonth = 5;
                    break;
            }

            cal.get(Calendar.DAY_OF_WEEK);

            isWeekend = cal.get(Calendar.DAY_OF_WEEK)<6?0:1;

            timeTable.add(new TimeTableRowVO(dayOfWeek,weekOfMonth,isWeekend,hour,month,catId));
        }


        Gson gson = new Gson();
        statTableString  = gson.toJson(timeTable);

        Log.v("json",statTableString);

        return statTableString;
    }

    private int getMinute(ActionVO vo) {
        cal = Calendar.getInstance();
        cal.setTimeInMillis(vo.getStart_time());
        return cal.get(Calendar.MINUTE);
    }

    public String getDateFormat(long time){
        Date date = new Date(time);
        return date.toString();
    }
}
