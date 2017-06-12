package group33.goldenlist;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar_golden;
    RadioGroup rgicon_date, rgTimeSche;
    RadioButton radio, radio_date, rdthu2, rdthu3, rdthu4, rdthu5, rdthu6, rdthu7, rdthu1;

    FragmentTransaction fragmentTransaction;
    FragmentSchedule frag_Sche;
    FragmentTimetable frag_Time;
    FragmentCalendar frag_Cal;

    TextView tvCal, goldenList, t2, t3, t4, t5, t6, t7, t8;
    boolean fragCal;

    int today;
    int currenDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goldenList = (TextView) findViewById(R.id.goldenList);
        Animation translate_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right);
        goldenList.startAnimation(translate_right);
        toolbar_golden = (Toolbar) findViewById(R.id.toolbar_golden);
        setSupportActionBar(toolbar_golden);

        rgicon_date = (RadioGroup) findViewById(R.id.icon_date);
        rdthu2 = (RadioButton) findViewById(R.id.rdThu2);
        rdthu3 = (RadioButton) findViewById(R.id.rdThu3);
        rdthu4 = (RadioButton) findViewById(R.id.rdThu4);
        rdthu5 = (RadioButton) findViewById(R.id.rdThu5);
        rdthu6 = (RadioButton) findViewById(R.id.rdThu6);
        rdthu7 = (RadioButton) findViewById(R.id.rdThu7);
        rdthu1 = (RadioButton) findViewById(R.id.rdThu1);

        tvCal = (TextView) findViewById(R.id.tvCal);
        rgTimeSche = (RadioGroup) findViewById(R.id.rgTimeSche);

        Calendar calendar = Calendar.getInstance();
        today = calendar.get(Calendar.DAY_OF_WEEK);

        // Set the calendar to monday of the current week
        if (today == 1)
            calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        // Print dates of the current week starting on Monday
        SimpleDateFormat df = new SimpleDateFormat("dd");
        rdthu2.setText(df.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1);
        rdthu3.setText(df.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1);
        rdthu4.setText(df.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1);
        rdthu5.setText(df.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1);
        rdthu6.setText(df.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1);
        rdthu7.setText(df.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1);
        rdthu1.setText(df.format(calendar.getTime()));

        int rd = rgicon_date.getCheckedRadioButtonId();
        radio_date = (RadioButton) findViewById(rd);
        switch (today){
            case Calendar.MONDAY:
                rdthu2.setChecked(true);
                break;
            case Calendar.TUESDAY:
                rdthu3.setChecked(true);
                break;
            case Calendar.WEDNESDAY:
                rdthu4.setChecked(true);
                break;
            case Calendar.THURSDAY:
                rdthu5.setChecked(true);
                break;
            case Calendar.FRIDAY:
                rdthu6.setChecked(true);
                break;
            case Calendar.SATURDAY:
                rdthu7.setChecked(true);
                break;
            case Calendar.SUNDAY:
                rdthu1.setChecked(true);
                break;
        }

        frag_Sche = new FragmentSchedule();
        frag_Time = new FragmentTimetable();
        frag_Cal = new FragmentCalendar();

        fragCal = false;
        currenDay = today;
        frag_Sche.day = today;
        frag_Time.day = today;

        loadTable("Timetable");

        int idThu[] = {R.id.rdThu2, R.id.rdThu3, R.id.rdThu4, R.id.rdThu5, R.id.rdThu6, R.id.rdThu7, R.id.rdThu1};
        for(int id:idThu){
            View v = findViewById(id);
            v.setOnClickListener(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadItem(currenDay);
    }

    public void loadTable(String name) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        if (name.compareTo("Schedule") == 0){
            frag_Sche.selectedDay(currenDay);
            fragmentTransaction.replace(R.id.framelayout, frag_Sche);
        }
        else{
            if (name.compareTo("Timetable") == 0) {
                frag_Time.selectedDay(currenDay);
                fragmentTransaction.replace(R.id.framelayout, frag_Time);
            }
            else
                fragmentTransaction.replace(R.id.framelayout, frag_Cal);
        }
        fragmentTransaction.commit();
    }

    public void loadItem(int DAY) {
        int rd = rgTimeSche.getCheckedRadioButtonId();
        radio = (RadioButton) findViewById(rd);
        if (radio.getId() == R.id.rdSchedule)
            frag_Sche.loadSchedule(DAY);
        else
            frag_Time.loadTimetable(DAY);
    }

    public  void onClickTimetable(View v){
        fragCal = false;
        loadTable("Timetable");
    }

    public  void onClickSchedule(View v){
        fragCal = false;
        loadTable("Schedule");
    }

    public void onClickCalendar(View v) {
        fragCal = true;
        loadTable("Calendar");
    }

    public void deleteItem(View v){
        if (!fragCal)
        {
            int rd = rgTimeSche.getCheckedRadioButtonId();
            radio = (RadioButton) findViewById(rd);
            if (radio.getId() == R.id.rdSchedule) {
                if (frag_Sche.posItem != -1){
                    AlertDialog.Builder comfirm = new AlertDialog.Builder(this);
                    comfirm.setTitle("Notification");
                    comfirm.setMessage("Do you want delete this subject ?");
                    comfirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            frag_Sche.deleteData();
                            loadItem(currenDay);
                        }
                    });
                    comfirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }) ;
                    comfirm.create().show();
                }
            }
            else {
                if (frag_Time.posItem != -1) {
                    AlertDialog.Builder comfirm = new AlertDialog.Builder(this);
                    comfirm.setTitle("Notification");
                    comfirm.setMessage("Do you want delete this job ?");
                    comfirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            frag_Time.deleteData();
                            loadItem(currenDay);
                        }
                    });
                    comfirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    comfirm.create().show();
                }
            }
        }
    }

    public void editItem(View v) {
        int rd = rgTimeSche.getCheckedRadioButtonId();
        radio = (RadioButton) findViewById(rd);
        Intent iadd_item;
        if (radio.getId() == R.id.rdSchedule) {
            if (FragmentSchedule.checkedSub != null) {
                iadd_item = new Intent(this, EditSubject.class);
                startActivityForResult(iadd_item, 1);
            }
        }
        else {
            if (FragmentTimetable.checkedJob != null) {
                iadd_item = new Intent(this, EditJob.class);
                startActivityForResult(iadd_item, 1);
            }
        }
    }

    public void addItem(View v) {
        int rd = rgTimeSche.getCheckedRadioButtonId();
        radio = (RadioButton) findViewById(rd);
        Intent iadd_item;
        if (radio.getId() == R.id.rdSchedule) {
            iadd_item = new Intent(this, AddSubject.class);
            startActivityForResult(iadd_item, 1);
        }
        else {
            iadd_item = new Intent(this, AddJob.class);
            startActivityForResult(iadd_item, 1);
        }
    }

    public void today(View v) {
        switch (today){
            case Calendar.MONDAY:
                rdthu2.setChecked(true);
                break;
            case Calendar.TUESDAY:
                rdthu3.setChecked(true);
                break;
            case Calendar.WEDNESDAY:
                rdthu4.setChecked(true);
                break;
            case Calendar.THURSDAY:
                rdthu5.setChecked(true);
                break;
            case Calendar.FRIDAY:
                rdthu6.setChecked(true);
                break;
            case Calendar.SATURDAY:
                rdthu7.setChecked(true);
                break;
            case Calendar.SUNDAY:
                rdthu1.setChecked(true);
                break;
        }
        currenDay = today;
        loadItem(today);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id ){
            case R.id.itSetPc:
                Intent iSetPc = new Intent(this, PasscodeBackground.class);
                startActivity(iSetPc);
                break;
            case R.id.itEditPc:
                Intent iEditPc = new Intent(this, PasscodeEdit.class);
                startActivity(iEditPc);
                break;
            case R.id.itAbout:
                AlertDialog.Builder c = new AlertDialog.Builder(this);
                c.setTitle("About");
                c.setMessage("This apps is created by:\n" +
                        "   - Đặng Tấn Tài\n" +
                        "   - Hồ Ngọc Sơn\n" +
                        "   - Nguyễn Thị Hoa\n" +
                        "   - Trần Văn Huy\n" +
                        "   - Nguyễn Trọng Nghĩa");
                c.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                c.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rdThu2:
                currenDay = 2;
                loadItem(currenDay);
                break;
            case R.id.rdThu3:
                currenDay = 3;
                loadItem(currenDay);
                break;
            case R.id.rdThu4:
                currenDay = 4;
                loadItem(currenDay);
                break;
            case R.id.rdThu5:
                currenDay = 5;
                loadItem(currenDay);
                break;
            case R.id.rdThu6:
                currenDay = 6;
                loadItem(currenDay);
                break;
            case R.id.rdThu7:
                currenDay = 7;
                loadItem(currenDay);
                break;
            case R.id.rdThu1:
                currenDay = 1;
                loadItem(currenDay);
                break;
        }
    }
}