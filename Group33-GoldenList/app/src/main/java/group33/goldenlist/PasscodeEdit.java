package group33.goldenlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class PasscodeEdit extends AppCompatActivity implements View.OnClickListener {

    ImageView immk1, immk2, immk3, immk4, immk11, immk21, immk31, immk41, immk111, immk211, immk311, immk411;
    TextView tvPasscode, currentPass, newPass, rePass;
    int i = 0;
    String passcode = "";
    User newUser;
    Database database;
    int i1, i2,i3, i4;
    String backgound;
    LinearLayout ll, lltext;
    Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode_edit);

        tvPasscode = (TextView) findViewById(R.id.tvPasscode);
        Animation translate_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right);
        Animation translate_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left);
        Animation translate_bottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_bottom);
        tvPasscode.startAnimation(translate_bottom);

        int[] idButton = {R.id.btnNumber0, R.id.btnNumber1, R.id.btnNumber2, R.id.btnNumber3,
                R.id.btnNumber4, R.id.btnNumber5, R.id.btnNumber6, R.id.btnNumber7,
                R.id.btnNumber8, R.id.btnNumber9};

        for (int id : idButton) {
            View v = findViewById(id);
            v.setOnClickListener(this);
        }

        ll = (LinearLayout) findViewById(R.id.llEditPasscode);
        lltext = (LinearLayout) findViewById(R.id.textPasscode);
        currentPass = (TextView) findViewById(R.id.currentPass);
        newPass = (TextView) findViewById(R.id.newPass);
        rePass = (TextView) findViewById(R.id.rePass);
        currentPass.startAnimation(translate_right);
        newPass.startAnimation(translate_right);
        rePass.startAnimation(translate_right);
        immk111 = (ImageView) findViewById(R.id.immk111);
        immk211 = (ImageView) findViewById(R.id.immk211);
        immk311 = (ImageView) findViewById(R.id.immk311);
        immk411 = (ImageView) findViewById(R.id.immk411);
        immk1 = (ImageView) findViewById(R.id.immk1);
        immk2 = (ImageView) findViewById(R.id.immk2);
        immk3 = (ImageView) findViewById(R.id.immk3);
        immk4 = (ImageView) findViewById(R.id.immk4);
        immk11 = (ImageView) findViewById(R.id.immk11);
        immk21 = (ImageView) findViewById(R.id.immk21);
        immk31 = (ImageView) findViewById(R.id.immk31);
        immk41 = (ImageView) findViewById(R.id.immk41);
        immk1.startAnimation(translate_left);
        immk2.startAnimation(translate_left);
        immk3.startAnimation(translate_left);
        immk4.startAnimation(translate_left);
        immk11.startAnimation(translate_left);
        immk21.startAnimation(translate_left);
        immk31.startAnimation(translate_left);
        immk41.startAnimation(translate_left);
        immk111.startAnimation(translate_left);
        immk211.startAnimation(translate_left);
        immk311.startAnimation(translate_left);
        immk411.startAnimation(translate_left);

        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
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
                immk111.setImageLevel(1);
                break;
            case 2:
                immk211.setImageLevel(1);
                break;
            case 3:
                immk311.setImageLevel(1);
                break;
            case 4:
                immk411.setImageLevel(1);
                vib.vibrate(500);
                try {
                    User guest = new User(passcode);
                    if (guest.getPasscode().compareTo(database.getCheckPass()) == 0){
                        AlertDialog.Builder c = new AlertDialog.Builder(this);
                        c.setTitle("Notification");
                        c.setMessage("Correct passcode. Please choose new passcode.");
                        c.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        c.create().show();
                    }
                    else{
                        AlertDialog.Builder c = new AlertDialog.Builder(this);
                        c.setTitle("Notification");
                        c.setMessage("Wrong passcode. Please retype.");
                        c.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        c.create().show();
                        passcode = "";
                        i = 0;
                        immk111.setImageLevel(0);
                        immk211.setImageLevel(0);
                        immk311.setImageLevel(0);
                        immk411.setImageLevel(0);
                    }
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
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
                passcode = "";
                break;

            case 5:
                immk1.setImageLevel(1);
                break;
            case 6:
                immk2.setImageLevel(1);
                break;
            case 7:
                immk3.setImageLevel(1);
                break;
            case 8:
                immk4.setImageLevel(1);
                vib.vibrate(500);
                try {
                    newUser = new User(passcode);
                    AlertDialog.Builder c = new AlertDialog.Builder(this);
                    c.setTitle("Notification");
                    c.setMessage("Please retype your new passcode");
                    c.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    c.create().show();
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
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
                passcode = "";
                break;

            case 9:
                immk11.setImageLevel(1);
                break;
            case 10:
                immk21.setImageLevel(1);
                break;
            case 11:
                immk31.setImageLevel(1);
                break;
            case 12:
                immk41.setImageLevel(1);
                vib.vibrate(500);
                User guest = null;
                try {
                    guest = new User(passcode);

                    if (guest.getPasscode().compareTo(newUser.getPasscode()) == 0){
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        database.updatePasscode(newUser);
                        finish();
                    }
                    else {
                        passcode = "";
                        i = 8;
                        immk11.setImageLevel(0);
                        immk21.setImageLevel(0);
                        immk31.setImageLevel(0);
                        immk41.setImageLevel(0);
                        AlertDialog.Builder b = new AlertDialog.Builder(this);
                        b.setTitle("Notification");
                        b.setMessage("Mismatch new passcode. Please retype.");
                        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        b.create().show();
                    }
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
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
                break;
        }
    }
}