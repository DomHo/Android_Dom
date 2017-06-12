package group33.goldenlist;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import group33.goldenlist.Database.Database;
import group33.goldenlist.Database.TimeTable;

public class AddJob extends AppCompatActivity {

    EditText edtJobName, edtJobNote;
    RadioButton rbPri1, rbPri2, rbPri3;
    TextView tvJobDate, tvJobStart, tvJobEnd, addJob, tvTime, tvPriority;
    Button btnJobDate, btnJobStart, btnJobEnd, btnJobSave;

    Calendar calendar1, calendar2;
    SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf1 = new SimpleDateFormat("MMM d, yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

    int priority = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        addControls();
        addEvents();

        calendar1 = Calendar.getInstance();
        calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.MINUTE, 1);

        tvJobDate.setText(sdf1.format(calendar1.getTime()));
        tvJobStart.setText(sdf2.format(calendar1.getTime()));
        tvJobEnd.setText(sdf2.format(calendar2.getTime()));

        Animation translate_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right);
        Animation translate_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left);
        Animation translate_bottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_bottom);
        Animation translate_top = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_top);

        addJob.startAnimation(translate_bottom);
        edtJobName.startAnimation(translate_left);
        edtJobNote.startAnimation(translate_left);
        tvPriority.startAnimation(translate_right);
        rbPri1.startAnimation(translate_left);
        rbPri2.startAnimation(translate_left);
        rbPri3.startAnimation(translate_left);
        tvTime.startAnimation(translate_right);
        tvJobDate.startAnimation(translate_right);
        tvJobStart.startAnimation(translate_right);
        tvJobEnd.startAnimation(translate_right);
        btnJobDate.startAnimation(translate_left);
        btnJobStart.startAnimation(translate_left);
        btnJobEnd.startAnimation(translate_left);
        btnJobSave.startAnimation(translate_top);
    }

    private void addControls() {
        addJob = (TextView) findViewById(R.id.addJob);

        edtJobName = (EditText) findViewById(R.id.edtAJobName);
        edtJobNote = (EditText) findViewById(R.id.edtAJobNote);

        tvPriority = (TextView) findViewById(R.id.tvPriority);
        rbPri1 = (RadioButton) findViewById(R.id.rbAPri1);
        rbPri2 = (RadioButton) findViewById(R.id.rbAPri2);
        rbPri3 = (RadioButton) findViewById(R.id.rbAPri3);

        tvTime = (TextView) findViewById(R.id.tvTime);

        tvJobDate= (TextView) findViewById(R.id.tvAJobDate);
        btnJobDate = (Button) findViewById(R.id.btnAJobDate);

        tvJobStart= (TextView) findViewById(R.id.tvAJobStart);
        btnJobStart = (Button) findViewById(R.id.btnAJobStart);

        tvJobEnd = (TextView) findViewById(R.id.tvAJobEnd);
        btnJobEnd = (Button) findViewById(R.id.btnAJobEnd);

        btnJobSave = (Button) findViewById(R.id.btnAJobSave);
    }

    private void addEvents() {
        rbPri1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = 1;
                rbPri1.setChecked(true);
            }
        });
        rbPri2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = 2;
                rbPri2.setChecked(true);
            }
        });
        rbPri3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = 3;
                rbPri3.setChecked(true);
            }
        });
        btnJobDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showJobDate();
            }
        });
        btnJobStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showJobTimeStart();
            }
        });
        btnJobEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showJobTimeEnd();
            }
        });
        btnJobSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addJob();
            }
        });
    }

    private void showJobDate() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar1.set(Calendar.YEAR,i);
                calendar1.set(Calendar.MONTH,i1);
                calendar1.set(Calendar.DATE,i2);
                tvJobDate.setText(sdf1.format(calendar1.getTime()));
            }
        };
        DatePickerDialog datePick = new DatePickerDialog(this,callBack,
                calendar1.get(Calendar.YEAR),
                calendar1.get(Calendar.MONTH),
                calendar1.get(Calendar.DATE));
        datePick.show();
    }

    private void showJobTimeStart() {
        TimePickerDialog.OnTimeSetListener callBack = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar1.set(Calendar.HOUR_OF_DAY,i);
                calendar1.set(Calendar.MINUTE,i1);
                tvJobStart.setText(sdf2.format(calendar1.getTime()));

                if ((calendar1.get(Calendar.HOUR_OF_DAY) > calendar2.get(Calendar.HOUR_OF_DAY)) ||
                        ((calendar1.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY)) && (calendar1.get(Calendar.MINUTE) >= calendar2.get(Calendar.MINUTE))))
                {
                    calendar2.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY));
                    calendar2.set(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
                    calendar2.add(Calendar.MINUTE, 1);
                    tvJobEnd.setText(sdf2.format(calendar2.getTime()));
                }
            }
        };
        TimePickerDialog timePicker = new TimePickerDialog(this, callBack,
                calendar1.get(Calendar.HOUR_OF_DAY),
                calendar1.get(Calendar.MINUTE),
                true
        );
        timePicker.show();
    }

    private void showJobTimeEnd() {
        TimePickerDialog.OnTimeSetListener callBack=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar2.set(Calendar.HOUR_OF_DAY,i);
                calendar2.set(Calendar.MINUTE,i1);
                tvJobEnd.setText(sdf2.format(calendar2.getTime()));

                if ((calendar1.get(Calendar.HOUR_OF_DAY) > calendar2.get(Calendar.HOUR_OF_DAY)) ||
                        ((calendar1.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY)) && (calendar1.get(Calendar.MINUTE) >= calendar2.get(Calendar.MINUTE))))
                {
                    calendar1.set(Calendar.HOUR_OF_DAY, calendar2.get(Calendar.HOUR_OF_DAY));
                    calendar1.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));
                    calendar1.add(Calendar.MINUTE, -1);
                    tvJobStart.setText(sdf2.format(calendar1.getTime()));
                }
            }
        };
        TimePickerDialog timePicker = new TimePickerDialog(this, callBack,
                calendar2.get(Calendar.HOUR_OF_DAY),
                calendar2.get(Calendar.MINUTE),
                true
        );
        timePicker.show();
    }

    private void addJob(){
        if (edtJobName.getText().toString().length() < 1)
        {
            Toast.makeText(this, "Please enter job's name", Toast.LENGTH_SHORT).show();
            return;
        }
        Database database = new Database(this);
        database.addJob(new TimeTable(
                999999,
                edtJobName.getText().toString(),
                edtJobNote.getText().toString(),
                priority,
                sdf0.format(calendar1.getTime()),
                calendar1.get(Calendar.DAY_OF_WEEK),
                tvJobStart.getText().toString(),
                tvJobEnd.getText().toString(),
                0
        ));

        // Notification & Alarm
        if (priority == 1) {
            Toast.makeText(this, "Added job", Toast.LENGTH_SHORT).show();
        }
        else {
            String[] currentDate_string  = sdf0.format(calendar1.getTime()).split("-");
            String[] currentTime_string  = tvJobStart.getText().toString().split(":");

            int year = Integer.parseInt(currentDate_string[0]);
            int month = Integer.parseInt(currentDate_string[1]);
            int date = Integer.parseInt(currentDate_string[2]);
            int Hour_Time = Integer.parseInt(currentTime_string[0]);
            int Min_Time = Integer.parseInt(currentTime_string[1]);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DATE, date);
            calendar.set(Calendar.HOUR_OF_DAY, Hour_Time);
            calendar.set(Calendar.MINUTE, Min_Time);
            calendar.add(Calendar.MINUTE, -30);

            Intent intent = new Intent(this, Alarm.class);
            intent.putExtra("TIME_ID", database.getIDLastRowJob());
            intent.putExtra("TIME_NAME", edtJobName.getText().toString());
            intent.putExtra("TIME_NOTE", edtJobNote.getText().toString());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            if (priority == 2)
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            else
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 15*60*1000, pendingIntent);

            Toast.makeText(this, "Alarm at: " + sdf2.format(calendar.getTime()) + " on " + sdf1.format(calendar.getTime()), Toast.LENGTH_LONG).show();
        }
        //
        finish();
    }
}