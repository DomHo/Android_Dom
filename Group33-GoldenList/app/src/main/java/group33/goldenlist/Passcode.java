package group33.goldenlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import group33.goldenlist.Database.Database;
import group33.goldenlist.Database.User;

public class Passcode extends AppCompatActivity implements View.OnClickListener {

    int i = 0;
    int countUser;
    String passcode = "";
    int i1, i2,i3, i4;
    String backgound;
    ImageView immk1, immk2, immk3, immk4;
    TextView passcodeWrong, tvPasscode;
    Database database;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
        tvPasscode = (TextView) findViewById(R.id.tvPasscode);
        Animation translate_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right);
        Animation translate_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left);
        tvPasscode.startAnimation(translate_right);
        int[] idButton = {R.id.btnNumber0, R.id.btnNumber1, R.id.btnNumber2, R.id.btnNumber3,
                R.id.btnNumber4, R.id.btnNumber5, R.id.btnNumber6,
                R.id.btnNumber7, R.id.btnNumber8, R.id.btnNumber9};
        for (int id : idButton) {
            View v = findViewById(id);
            v.setOnClickListener(this);
        }
        immk1 = (ImageView) findViewById(R.id.immk1);
        immk2 = (ImageView) findViewById(R.id.immk2);
        immk3 = (ImageView) findViewById(R.id.immk3);
        immk4 = (ImageView) findViewById(R.id.immk4);
        immk1.startAnimation(translate_left);
        immk2.startAnimation(translate_left);
        immk3.startAnimation(translate_left);
        immk4.startAnimation(translate_left);
        ll = (LinearLayout) findViewById(R.id.llPasscodeActivity);
        passcodeWrong = (TextView) findViewById(R.id.tvPasscodeWrong);

        database = new Database(this);
        countUser = database.getUsersCount();

        if (countUser == 0){
            passcodeWrong.setVisibility(View.VISIBLE);
            passcodeWrong.setText("Please choose 4 numbers to set passcode.");
            ll.setBackgroundResource(R.drawable.background);
        }
        else {
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
    }

    @Override
    public void onClick(View view) {
        i++;
        switch (view.getId()){
            case R.id.btnNumber0:
                passcode += "0";
                break;
            case R.id.btnNumber1:
                passcode += "1";
                break;
            case R.id.btnNumber2:
                passcode += "2";
                break;
            case R.id.btnNumber3:
                passcode += "3";
                break;
            case R.id.btnNumber4:
                passcode += "4";
                break;
            case R.id.btnNumber5:
                passcode += "5";
                break;
            case R.id.btnNumber6:
                passcode += "6";
                break;
            case R.id.btnNumber7:
                passcode += "7";
                break;
            case R.id.btnNumber8:
                passcode += "8";
                break;
            case R.id.btnNumber9:
                passcode += "9";
                break;
        }

        switch (i) {
            case 1:
                immk1.setImageLevel(1);
                break;
            case 2:
                immk2.setImageLevel(1);
                break;
            case 3:
                immk3.setImageLevel(1);
                break;
            case 4:
                immk4.setImageLevel(1);

                if(countUser == 0) {
                    try {
                        database.addPasscode(new User(passcode));
                    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                        Toast.makeText(this, "Error "+ e.toString() +"\nPlease restart the application..", Toast.LENGTH_LONG).show();
                    }
                    Intent iPasscode = new Intent(this, MainActivity.class);
                    startActivity(iPasscode);
                    finish();
                }
                else
                {
                    try {
                        User guest = new User(passcode);
                        if (guest.getPasscode().compareTo(database.getCheckPass()) == 0){
                            Intent iPasscode = new Intent(this, MainActivity.class);
                            startActivity(iPasscode);
                            finish();
                        }
                        else {
                            Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vib.vibrate(500);
                            passcodeWrong.setVisibility(View.VISIBLE);
                            passcode = "";
                            i = 0;
                            immk1.setImageLevel(0);
                            immk2.setImageLevel(0);
                            immk3.setImageLevel(0);
                            immk4.setImageLevel(0);
                        }
                    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                        AlertDialog.Builder c = new AlertDialog.Builder(this);
                        c.setTitle("Notification");
                        c.setMessage("Error "+ e.toString() +"\nPlease restart the application");
                        c.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        c.create().show();
                    }
                }
                break;
        }
    }
}