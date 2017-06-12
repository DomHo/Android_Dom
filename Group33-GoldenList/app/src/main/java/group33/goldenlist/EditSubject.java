package group33.goldenlist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import group33.goldenlist.Database.Database;
import group33.goldenlist.Database.Schedule;

public class EditSubject extends AppCompatActivity {
    EditText edtSubName, edtSubRoom, edtSubNote;
    TextView tvSubDate, tvSubStart, tvSubEnd,  tveditSub, tvTime;
    Button btnSubDate, btnSubStart, btnSubEnd, btnSubSave;

    Schedule editSub;

    Calendar calendar1, calendar2;
    SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf1 = new SimpleDateFormat("MMM d, yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subject);
        addControls();
        addEvents();

        calendar1 = Calendar.getInstance();
        calendar2 = Calendar.getInstance();
        editSub = FragmentSchedule.checkedSub;

        edtSubName.setText(editSub.getName());
        edtSubRoom.setText(editSub.getRoom());
        edtSubNote.setText(editSub.getNote());

        String[] date = editSub.getDate().split("-");
        String[] start = editSub.getStartTime().split(":");
        String[] end = editSub.getEndTime().split(":");

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

        tvSubDate.setText(sdf1.format(calendar1.getTime()));
        tvSubStart.setText(sdf2.format(calendar1.getTime()));
        tvSubEnd.setText(sdf2.format(calendar2.getTime()));

        Animation translate_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right);
        Animation translate_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left);
        Animation translate_bottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_bottom);
        Animation translate_top = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_top);

        tveditSub.startAnimation(translate_bottom);
        edtSubName.startAnimation(translate_left);
        edtSubNote.startAnimation(translate_left);
        edtSubRoom.startAnimation(translate_left);
        tvTime.startAnimation(translate_right);
        tvSubDate.startAnimation(translate_right);
        tvSubStart.startAnimation(translate_right);
        tvSubEnd.startAnimation(translate_right);
        btnSubDate.startAnimation(translate_left);
        btnSubStart.startAnimation(translate_left);
        btnSubEnd.startAnimation(translate_left);
        btnSubSave.startAnimation(translate_top);
    }

    private void addControls() {
        tveditSub = (TextView) findViewById(R.id.editSub);
        edtSubName = (EditText) findViewById(R.id.edtESubName);
        edtSubRoom = (EditText) findViewById(R.id.edtESubRoom);
        edtSubNote = (EditText) findViewById(R.id.edtESubNote);

        tvTime = (TextView) findViewById(R.id.tvTime);
        tvSubDate = (TextView) findViewById(R.id.tvESubDate);
        btnSubDate = (Button) findViewById(R.id.btnESubDate);

        tvSubStart = (TextView) findViewById(R.id.tvESubStart);
        btnSubStart = (Button) findViewById(R.id.btnESubStart);

        tvSubEnd = (TextView) findViewById(R.id.tvESubEnd);
        btnSubEnd = (Button) findViewById(R.id.btnESubEnd);

        btnSubSave = (Button) findViewById(R.id.btnESubSave);
    }

    private void addEvents() {
        btnSubDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSubDate();
            }
        });
        btnSubStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSubTimeStart();
            }
        });
        btnSubEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSubTimeEnd();
            }
        });
        btnSubSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSubject();
            }
        });
    }

    private void showSubDate() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar1.set(Calendar.YEAR,i);
                calendar1.set(Calendar.MONTH,i1);
                calendar1.set(Calendar.DATE,i2);
                tvSubDate.setText(sdf1.format(calendar1.getTime()));
            }
        };
        DatePickerDialog datePick = new DatePickerDialog(this,callBack,
                calendar1.get(Calendar.YEAR),
                calendar1.get(Calendar.MONTH),
                calendar1.get(Calendar.DATE));
        datePick.show();
    }

    private void showSubTimeStart() {
        TimePickerDialog.OnTimeSetListener callBack = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar1.set(Calendar.HOUR_OF_DAY,i);
                calendar1.set(Calendar.MINUTE,i1);
                tvSubStart.setText(sdf2.format(calendar1.getTime()));

                if ((calendar1.get(Calendar.HOUR_OF_DAY) > calendar2.get(Calendar.HOUR_OF_DAY)) ||
                        ((calendar1.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY)) && (calendar1.get(Calendar.MINUTE) >= calendar2.get(Calendar.MINUTE))))
                {
                    calendar2.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY));
                    calendar2.set(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
                    calendar2.add(Calendar.MINUTE, 1);
                    tvSubEnd.setText(sdf2.format(calendar2.getTime()));
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

    private void showSubTimeEnd() {
        TimePickerDialog.OnTimeSetListener callBack = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar2.set(Calendar.HOUR_OF_DAY,i);
                calendar2.set(Calendar.MINUTE,i1);
                tvSubEnd.setText(sdf2.format(calendar2.getTime()));

                if ((calendar1.get(Calendar.HOUR_OF_DAY) > calendar2.get(Calendar.HOUR_OF_DAY)) ||
                        ((calendar1.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY)) && (calendar1.get(Calendar.MINUTE) >= calendar2.get(Calendar.MINUTE))))
                {
                    calendar1.set(Calendar.HOUR_OF_DAY, calendar2.get(Calendar.HOUR_OF_DAY));
                    calendar1.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));
                    calendar1.add(Calendar.MINUTE, -1);
                    tvSubStart.setText(sdf2.format(calendar1.getTime()));
                }
            }
        };
        TimePickerDialog timePicker=new TimePickerDialog(this, callBack,
                calendar2.get(Calendar.HOUR_OF_DAY),
                calendar2.get(Calendar.MINUTE),
                true
        );
        timePicker.show();
    }

    private void editSubject(){
        if (edtSubName.getText().toString().length() < 1)
        {
            Toast.makeText(this, "Please enter subject's name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtSubRoom.getText().toString().length() < 1)
        {
            Toast.makeText(this, "Please enter subject's room", Toast.LENGTH_SHORT).show();
            return;
        }
        editSub.setName(edtSubName.getText().toString());
        editSub.setNote(edtSubNote.getText().toString());
        editSub.setRoom(edtSubRoom.getText().toString());
        editSub.setDate(sdf0.format(calendar1.getTime()));
        editSub.setDay(calendar1.get(Calendar.DAY_OF_WEEK));
        editSub.setStartTime(tvSubStart.getText().toString());
        editSub.setEndTime(tvSubEnd.getText().toString());

        Database database = new Database(this);
        database.updateSubject(editSub);
        Toast.makeText(this, "Edited subject", Toast.LENGTH_SHORT).show();
        finish();
    }
}
