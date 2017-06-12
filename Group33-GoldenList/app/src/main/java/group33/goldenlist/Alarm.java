package group33.goldenlist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Alarm extends AppCompatActivity {

    MediaPlayer mp;
    Vibrator v;
    TextView tvAlarmName, tvAlarmNote;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        tvAlarmName = (TextView) findViewById(R.id.tvAlarmName);
        tvAlarmNote = (TextView) findViewById(R.id.tvAlarmNote);

        tvAlarmName.setText(getIntent().getExtras().getString("TIME_NAME"));
        tvAlarmNote.setText(getIntent().getExtras().getString("TIME_NOTE"));

        v = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 500, 1000};
        v.vibrate(pattern, 0);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        mp = MediaPlayer.create(this.getApplicationContext(), R.raw.alarm);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.start();

    }

    @Override
    public void onBackPressed(){
        mp.stop();
        v.cancel();
        finish();
    }

    public void YES(View view) {
        mp.stop();
        v.cancel();
        Intent i = new Intent(this, Passcode.class);
        startActivity(i);
        finish();
    }

    public void NO(View view) {
        mp.stop();
        v.cancel();

        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(this);
        notBuilder.setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Golden List")
                .setWhen(System.currentTimeMillis() + 1)
                .setContentTitle(getIntent().getExtras().getString("TIME_NAME"))
                .setContentText(getIntent().getExtras().getString("TIME_NOTE"));

        NotificationManager notificationService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = notBuilder.build();
        notificationService.notify(0, notification);

        finish();
    }
}