package codingtribe.com;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static codingtribe.com.item_home.CatDbHelper;

/**
 * Created by pc-05 on 2018-01-07.
 */

public class ActionDB extends SQLiteOpenHelper {

    public ActionDB(Context context) {
        super(context, "actionDB.db", null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ACTION_INFO (action_id INTEGER PRIMARY KEY AUTOINCREMENT, cat_id INTEGER, start_time INTEGER);");

        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512054000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512081600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512083400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512085800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512100200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512103800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512118200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512120000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1512124800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512132000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512140400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1512176400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512184200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512189600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512207600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512211200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1512216600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1512225000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512234000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512264000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512267600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1512277200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512283200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512287400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512292200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1512295800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512300600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512304200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512311400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512340200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512342000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512343200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512345600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512360000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512363600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512378000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512379800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512384000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512385800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512406800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512428400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512430200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512432000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512446400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512450600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512465000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512466800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1512470400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512475800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512496800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512513600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512514800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512516600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512518400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512532800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512536400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512550800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512552600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1512558000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512567000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512582000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512600000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512602400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512604200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512619200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512624600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512639000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512640800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512645000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512669000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512687000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512688800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512690600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512705600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512709200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1512723600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512747600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512780600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512783000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512788400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512794400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1512806400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512809400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512816000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512819000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512825000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1512834600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512841800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512870600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512872400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1512878400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512885000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1512889200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512903600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1512907200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1512917400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512940200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512941400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1512946800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512963600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1512968100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1512986100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1512989700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1512991500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1512994200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1512997800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513001400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513025400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513027500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513032900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513050300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513054800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513071600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513074900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1513077900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1513081500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513086900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513089300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513090500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513112100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513113900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513119300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513136100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513140600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513157400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513161600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1513164300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1513167300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513174500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513177500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513201500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513203900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513208100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513224900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513229400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513247400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1513251000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513261800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513263600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513287000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513288800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513293000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513309800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1513314300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513332300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1513335900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513347900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513349700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513375500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513382700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1513386300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513391100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1513392900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513401900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513404300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1513407300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513414500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513418100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1513422300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513427100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1513428300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513436100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513437300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513464900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513466700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513470300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,10,1513473300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513484100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513488300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513491000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1513498200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513502400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1513505400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513508400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513517400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513546200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513548600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513553100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513569900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513574400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513592400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513596000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513598400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513602000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513605600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513632600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513634400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513638900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513655700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513660200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513677000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513679400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1513681200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513685400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513687800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513690800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513718400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513725600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1513730100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513746900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,10,1513749300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513760100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513763700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1513768200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513771200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513772400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513776000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513803600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513805400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513809600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513826400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513830900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513847700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513850100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1513851300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513862100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513864500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513890300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513892100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513896600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513913400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1513917900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513934700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513938300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1513940100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513943100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1513944900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1513948500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1513978500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1513980900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1513985100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514001900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514003700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1514009700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514017500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514021100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514024100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1514027100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1514031300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514033700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514062500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514070300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1514072700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514079900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514082300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1514086500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514097300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514100300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514105100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1514107500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514111100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1514114700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514119500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514122500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514149500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514151300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514155500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514172300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514176800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514193600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514196000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1514197200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514208000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514210400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514236200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514238000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514242500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514259300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514263800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514280600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514283000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1514284800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514289000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514291400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514294400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514322000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514323800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514328300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514345100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514349600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1514366400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514378400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514382000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514409000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514410800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514415300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514432100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514436600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514453400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514457000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1514458800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514466000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514469000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514494800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514496600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514501100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514517900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514522400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514539200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514541600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1514543400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514547000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1514549400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514552400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514556000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514581800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514586600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1514591100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514598900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514603400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514607000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514609400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1514613000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514620200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514623800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514626200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1514629200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514641200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514671200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1514674800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514679300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514688900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514691900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1514693700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514697300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514699100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514702700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1514706300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514717100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514720700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514725500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514755500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514759700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1514762700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514766300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1514769900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514777100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514780700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,10,1514784300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514793900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514797500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514801700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1514805900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514808900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514813100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514841900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514843700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514848200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514865000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514869500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514886300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514890800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1514894400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514898600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1514899800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514902200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514927400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514929200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514933700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514950500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1514955000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1514973000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514976000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1514977800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1514985000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1514988000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515013800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515015600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1515020100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515036900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1515041400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515058800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1515061800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515066600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1515070200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1515072600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515100200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515102000000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1515106500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515123300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1515127800000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515144600000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515149100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1515152700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515156900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1515158100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1515160500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515188100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515190500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1515194700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515211500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1515213300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1515219300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515227100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515230700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1515233700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1515236700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1515240900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,6,1515243300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1515245100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1515275100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515282900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1515285300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515292500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515294900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1515299100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515309900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1515312900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515317700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,7,1515320100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1515323700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,1,1515327300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1515332100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1515333300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515359100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515360900000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1515365400000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515382200000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,9,1515386700000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,3,1515403500000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515405300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,8,1515407100000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,4,1515414300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,5,1515417300000);");
        sqLiteDatabase.execSQL("INSERT INTO ACTION_INFO VALUES(null,2,1515419700000);");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(int cat_id, long startTime) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        Log.v("들어온 값은 ", startTime+"");
        db.execSQL("INSERT INTO ACTION_INFO VALUES(null, '" + cat_id + "', " + startTime + ");");
        db.close();
    }

    public void update_cat(int action_id, int cat_id_will_be_changed) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 액션의  수정
        db.execSQL("UPDATE ACTION_INFO SET cat_id=" + cat_id_will_be_changed + " WHERE action_id='" + action_id + "';");
        db.close();
    }

    public void update_time(int action_id, int startTime_will_be_changed) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 액션의  수정
        db.execSQL("UPDATE ACTION_INFO SET start_time=" + startTime_will_be_changed + " WHERE action_id='" + action_id + "';");
        db.close();
    }

    public void delete(int action_id) {
        SQLiteDatabase db = getWritableDatabase();
        // 삭제가 쓰이지는 않을 것 같다.

        db.execSQL("DELETE FROM ACTION_INFO WHERE action_id =  "+action_id+";");
        db.close();
    }

    public ArrayList<ActionVO> getAllAction(Activity act) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ActionVO tempActionVO;
        ArrayList<ActionVO> tempArrayList = new ArrayList<ActionVO>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM ACTION_INFO;", null);

        while (cursor.moveToNext()) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd 일 aa HH:mm z");


            long timeMillis = cursor.getLong(2);
            String now = sdf.format(timeMillis);


//            Log.v("DB정보", cursor.getInt(0)+" "+cursor.getInt(1)+" "+ now);


            String catName = CatDbHelper.getCatName(cursor.getInt(1));


            tempActionVO = new ActionVO(cursor.getInt(0), cursor.getInt(1), cursor.getLong(2), catName);
            tempArrayList.add(tempActionVO);


        }
        return tempArrayList;
    }

    public ActionVO getOneActionByTime(long timeInMillis) {
        SQLiteDatabase db = getReadableDatabase();
        ActionVO tempActionVO = null;
        ArrayList<ActionVO> tempArrayList = new ArrayList<ActionVO>();

        long max = 0;
        int actionIdMaxTime = 0;

        Cursor cursor = db.rawQuery("SELECT * FROM ACTION_INFO WHERE start_time < "+timeInMillis+";", null);



        while (cursor.moveToNext()) {

            int action_id = cursor.getInt(0);
            int cat_id = cursor.getInt(1);
            long start_time = cursor.getLong(2);

//            Log.v("getOneActionByTime", action_id+" "+cat_id+" "+ start_time);

            String catName = CatDbHelper.getCatName(cursor.getInt(1));

            tempActionVO = new ActionVO(cursor.getInt(0), cursor.getInt(1), cursor.getLong(2), catName);
            tempArrayList.add(tempActionVO);

        }

        for(int i =0;i<tempArrayList.size();i++){
            if(max<tempArrayList.get(i).getStart_time()){
                max = tempArrayList.get(i).getStart_time();
                actionIdMaxTime = tempArrayList.get(i).getAction_id();
            }
        }

//        Log.v("설정 시간의 max 타임 액션 정보", getActionById(actionIdMaxTime).getAction_id()+" "+getActionById(actionIdMaxTime).getStart_time()+" "+getActionById(actionIdMaxTime).getCat_name() );

        return tempActionVO;
    }

    public ActionVO getActionById(int action_id) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ActionVO tempActionVO=null;

        Cursor cursor = db.rawQuery("SELECT * FROM ACTION_INFO WHERE action_id = "+action_id+";", null);

        while (cursor.moveToNext()) {

            String catName = CatDbHelper.getCatName(cursor.getInt(1));
            tempActionVO = new ActionVO(cursor.getInt(0), cursor.getInt(1), cursor.getLong(2), catName);
        }
        return tempActionVO;
    }
}
