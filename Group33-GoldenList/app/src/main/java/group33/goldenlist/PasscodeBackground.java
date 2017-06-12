package group33.goldenlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import group33.goldenlist.Database.Database;

public class PasscodeBackground extends AppCompatActivity {

    ImageView bg1, bg2, bg3, bg4;
    LinearLayout ll;
    int i1, i2,i3, i4;
    String backgound;
    Database database;
    TextView tvPasscode, tvChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode_background);
        tvPasscode = (TextView) findViewById(R.id.tvPasscode);
        tvChoose = (TextView) findViewById(R.id.Choose);
        Animation translate_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right);
        Animation translate_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left);
        Animation translate_bottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_bottom);
        Animation translate_top = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_top);
        tvPasscode.startAnimation(translate_bottom);
        tvChoose.startAnimation(translate_top);
        bg1 = (ImageView) findViewById(R.id.imageViewbg1);
        bg2 = (ImageView) findViewById(R.id.imageViewbg2);
        bg3 = (ImageView) findViewById(R.id.imageViewbg3);
        bg4 = (ImageView) findViewById(R.id.imageViewbg4);
        bg1.startAnimation(translate_right);
        bg3.startAnimation(translate_right);
        bg2.startAnimation(translate_left);
        bg4.startAnimation(translate_left);
        ll = (LinearLayout) findViewById(R.id.llSetPasscode);
        database = new Database(this);
        backgound = database.getCurBackground();
        switch (backgound){
            case "0": i1 = i2 = i3 = i4 = 0; break;
            case "1": i1 = 1; i2 = i3 = i4 = 0; break;
            case "2": i2 = 1; i1 = i3 = i4 = 0; break;
            case "3": i3 = 1; i2 = i1 = i4 = 0; break;
            case "4": i4 = 1; i2 = i3 = i1 = 0; break;
        }
        if(i1 == 1){ ll.setBackgroundResource(R.drawable.background1_1);}
        else if (i2 == 1){ ll.setBackgroundResource(R.drawable.background2_2);}
        else if (i3 == 1){ ll.setBackgroundResource(R.drawable.background3_3);}
        else if (i4 == 1){ ll.setBackgroundResource(R.drawable.background4_4);}
        else ll.setBackgroundResource(R.drawable.background);
    }

    public void onClickbg1 (View v) {
        i1++;
        switch (i1) {
            case 1: ll.setBackgroundResource(R.drawable.background1_1) ;
                i2 = i3 = i4 = 0;
                break;
            case 2:
                ll.setBackgroundResource(R.drawable.background);
                i1 = 0;
                break;
        }
    }
    public void onClickbg2 (View v){
        i2++;
        switch (i2) {
            case 1: ll.setBackgroundResource(R.drawable.background2_2) ;
                i1 = i3 = i4 = 0;
                break;
            case 2:
                ll.setBackgroundResource(R.drawable.background);
                i2 = 0;
                break;
        }
    }
    public void onClickbg3 (View v){
        i3++;
        switch (i3) {
            case 1: ll.setBackgroundResource(R.drawable.background3_3) ;
                i2 = i1 = i4 = 0;
                break;
            case 2:
                ll.setBackgroundResource(R.drawable.background);
                i3 = 0;
                break;
        }
    }
    public void onClickbg4 (View v){
        i4++;
        switch (i4) {
            case 1: ll.setBackgroundResource(R.drawable.background4_4) ;
                i2 = i3 = i1 = 0;
                break;
            case 2:
                ll.setBackgroundResource(R.drawable.background);
                i4 = 0;
                break;
        }
    }
    public void onClickChoose (View v){
        if (i1 == 1) backgound = "1";
        else if (i2 == 1) backgound = "2";
        else if (i3 == 1) backgound = "3";
        else if (i4 == 1) backgound = "4";
        else backgound = "0";
        if (i1 == 1 || i2 == 1|| i3 == 1|| i4 == 1 ){
            AlertDialog.Builder a = new AlertDialog.Builder(this);
            a.setTitle("Notification");
            a.setMessage("Do you want set this background for screen passcode ?");
            a.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    database.updateBackground(Integer.parseInt(backgound));
                    finish();
                }
            });
            a.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }) ;
            a.create().show();
        }
        else{
            backgound = "0";
            ll.setBackgroundResource(R.drawable.background);
            database.updateBackground(0);
            finish();
        }
    }
}