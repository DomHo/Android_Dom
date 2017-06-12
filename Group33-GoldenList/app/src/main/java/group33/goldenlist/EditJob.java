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

public class EditJob extends AppCompatActivity {

    EditText edtJobName, edtJobNote;
    RadioButton rbPri1, rbPri2, rbPri3;
    TextView tvJobDate, tvJobStart, tvJobEnd, tveditJob, tvPriority, tvTime;
    Button btnJobDate, btnJobStart, btnJobEnd, btnJobSave;

    Calendar calendar1, calendar2;
    SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf1 = new SimpleDateFormat("MMM d, yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

    int priority;
    TimeTable editJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job);
        addControls();
        addEvents();

        calendar1 = Calendar.getInstance();
        calendar2 = Calendar.getInstance();
        editJob = FragmentTimetable.checkedJob;

        edtJobName.setText(editJob.getName());
        edtJobNote.setText(editJob.getNote());
        priority = editJob.getPriority();
        if (priority == 1)
            rbPri1.setChecked(true);
        else
        if (priority == 2)
            rbPri2.setChecked(true);
        else
            rbPri3.setChecked(true);

        String[] date = editJob.getDate().split("-");
        String[] start = editJob.getStartTime().split(":");
        String[] end = editJob.getEndTime().split(":");

        calendar1.set(Calendar.YEAR, Integer.parseInt(date[0]));
        calendar1.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
        calendar1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
        calendar1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(start[0]));
        calendar1.set(Calendar.MINUTE, Integer.parseInt(start[1]));

        calendar1.set(Calendar.YEAR, Integer.parseInt(date[0]));
        calendar1.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
        calendar1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
        calendar2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(end[0]));
        calendar2.set(Calendar.MINUTE, Integer.parseInt(end[1]));

        tvJobDate.setText(sdf1.format(calendar1.getTime()));
        tvJobStart.setText(sdf2.format(calendar1.getTime()));
        tvJobEnd.setText(sdf2.format(calendar2.getTime()));

        Animation translate_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right);
        Animation translate_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left);
        Animation translate_bottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_bottom);
        Animation translate_top = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_top);

        tveditJob.startAnimation(translate_bottom);
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
        tveditJob = (TextView) findViewById(R.id.editJob);

        edtJobName = (EditText) findViewById(R.id.edtEJobName);
        edtJobNote = (EditText) findViewById(R.id.edtEJobNote);
        tvPriority = (TextView) findViewById(R.id.tvPriority);
        rbPri1 = (RadioButton) findViewById(R.id.rbEPri1);
        rbPri2 = (RadioButton) findViewById(R.id.rbEPri2);
        rbPri3 = (RadioButton) findViewById(R.id.rbEPri3);

        tvTime = (TextView) findViewById(R.id.tvTime);
        tvJobDate= (TextView) findViewById(R.id.tvEJobDate);
        btnJobDate = (Button) findViewById(R.id.btnEJobDate);

        tvJobStart= (TextView) findViewById(R.id.tvEJobStart);
        btnJobStart = (Button) findViewById(R.id.btnEJobStart);

        tvJobEnd = (TextView) findViewById(R.id.tvEJobEnd);
        btnJobEnd = (Button) findViewById(R.id.btnEJobEnd);

        btnJobSave = (Button) findViewById(R.id.btnEJobSave);
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
                editJob();
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

    private void editJob(){
        if (edtJobName.getText().toString().length() < 1)
        {
            Toast.makeText(this, "Please enter job's name", Toast.LENGTH_SHORT).show();
            return;
        }

        editJob.setName(edtJobName.getText().toString());
        editJob.setNote(edtJobNote.getText().toString());
        editJob.setPriority(priority);
        editJob.setDate(sdf0.format(calendar1.getTime()));
        editJob.setDay(calendar1.get(Calendar.DAY_OF_WEEK));
        editJob.setStartTime(tvJobStart.getText().toString());
        editJob.setEndTime(tvJobEnd.getText().toString());
        editJob.setFinish(0);

        Database database = new Database(this);
        database.updateJob(editJob);

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        intent.putExtra("TIME_ID", editJob.getId());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        if (priority == 1) {
            Toast.makeText(this, "Edited job", Toast.LENGTH_SHORT).show();
        }
        else  {
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

            Intent intent1 = new Intent(this, MyBroadcastReceiver.class);
            intent1.putExtra("TIME_ID", editJob.getId());
            intent1.putExtra("TIME_NAME", edtJobName.getText().toString());
            intent1.putExtra("TIME_NOTE", edtJobNote.getText().toString());
            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);

            if (priority == 2)
                alarmManager1.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent1);
            else
                alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 15*60*1000, pendingIntent1);

            Toast.makeText(this, "Alarm at: " + sdf2.format(calendar.getTime()) + " on " + sdf1.format(calendar.getTime()), Toast.LENGTH_LONG).show();
        }
        finish();
    }
}