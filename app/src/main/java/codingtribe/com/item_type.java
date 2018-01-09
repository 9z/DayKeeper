package codingtribe.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class item_type extends AppCompatActivity {
    Random rd;
    ImageView type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_type);
rd=new Random();
        type=(ImageView)findViewById(R.id.imageView);

        int random=rd.nextInt(3)+1;

        switch(random){
            case 1:
                type.setImageResource(R.drawable.betoben);
            case 2:
                type.setImageResource(R.drawable.hugo);
            case 3:
                type.setImageResource(R.drawable.valjak);
        }
    }
}
