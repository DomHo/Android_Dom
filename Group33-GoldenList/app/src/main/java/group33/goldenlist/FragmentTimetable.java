package group33.goldenlist;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import group33.goldenlist.Database.Database;
import group33.goldenlist.Database.TimeTable;

import static android.content.Context.ALARM_SERVICE;

public class FragmentTimetable extends Fragment {
    ListView listView;
    List<TimeTable> jobList;

    timetableAdapter adapterJob;
    Database database;

    int day;
    int posItem = -1;
    static TimeTable checkedJob = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment_listview = inflater.inflate(R.layout.fragment_timetable, container, false);

        database = new Database(this.getActivity());
        jobList = database.getAllJobs(day);

        adapterJob = new timetableAdapter(this.getActivity(), R.layout.item_timetable, jobList);
        adapterJob.notifyDataSetChanged();
        listView = (ListView) fragment_listview.findViewById(R.id.listview_timetable);
        listView.setAdapter(adapterJob);

        return fragment_listview;
    }

    private class timetableAdapter extends ArrayAdapter<TimeTable> {
        Context context;
        int resource;
        List<TimeTable> jobList;

        timetableAdapter(Context context, int resource, List<TimeTable> objects){
            super(context,resource,objects);

            this.context = context;
            this.resource = resource;
            this.jobList = objects;
        }

        @NonNull
        public View getView(final int position, View convertView, @NonNull ViewGroup parent){

            final int[] arrow1 = {0};

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowItem = inflater.inflate(resource,parent,false);

            final ImageButton imbCheck = (ImageButton) rowItem.findViewById(R.id.imbCheck);
            TextView tvName = (TextView) rowItem.findViewById(R.id.tvJobName);
            TextView tvTime = (TextView) rowItem.findViewById(R.id.tvJobTime);
            TextView tvPriority = (TextView) rowItem.findViewById(R.id.tvPriority);
            final TextView tvNote = (TextView) rowItem.findViewById(R.id.tvJobNote);
            final Button btnJobArrow = (Button) rowItem.findViewById(R.id.btnJobArrow);

            final TimeTable tempJob = jobList.get(position);
            tvName.setText(tempJob.getName());
            String[] time = tempJob.getDate().split("-");
            tvTime.setText(time[2] + "/" + time[1] + " - " + tempJob.getStartTime() + " - " + tempJob.getEndTime());
            tvNote.setText(tempJob.getNote());
            if(tempJob.getPriority() == 1)
            {
                tvPriority.setText("!");
            }
            else if(tempJob.getPriority() == 2)
            {
                tvPriority.setText("!!");
            }
            else if(tempJob.getPriority() == 3)
            {
                tvPriority.setText("!!!");
            }

            if (tempJob.getFinish() == 1)
                imbCheck.setBackgroundResource(R.drawable.item_timetable_checkon);

            if(tempJob.getNote().length() > 0)
                btnJobArrow.setVisibility(View.VISIBLE);

            btnJobArrow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    arrow1[0]++;
                    switch (arrow1[0]){
                        case 1:
                            btnJobArrow.setBackgroundResource(R.drawable.arrow_up);
                            tvNote.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            arrow1[0] = 0;
                            btnJobArrow.setBackgroundResource(R.drawable.arrow_down);
                            tvNote.setVisibility(View.GONE);
                            break;
                    }
                }
            });

            imbCheck.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder comfirm;
                    if (tempJob.getFinish() == 0)
                    {
                        comfirm = new AlertDialog.Builder(getActivity());
                        comfirm.setTitle("Notification");
                        comfirm.setMessage("Have you finished this job ?");
                        comfirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                jobList.get(position).setFinish(1);
                                tempJob.setFinish(1);
                                database.updateJob(tempJob);
                                imbCheck.setBackgroundResource(R.drawable.item_timetable_checkon);
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
                    else {
                        comfirm = new AlertDialog.Builder(getActivity());
                        comfirm.setTitle("Notification");
                        comfirm.setMessage("Haven't you finished this job ?");
                        comfirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                jobList.get(position).setFinish(0);
                                tempJob.setFinish(0);
                                database.updateJob(tempJob);
                                imbCheck.setBackgroundResource(R.drawable.item_timetable_checkoff);
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
            });

            rowItem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    listView.setItemChecked(position, true);
                    posItem = position;
                    checkedJob = (TimeTable) listView.getItemAtPosition(posItem);
                }
            });

            return rowItem;
        }
    }

    public void selectedDay (int day) {
        this.day = day;
    }

    public  void loadTimetable(int selectDay){
        day = selectDay;
        listView.setItemChecked(posItem, false);
        posItem = -1;
        jobList = database.getAllJobs(selectDay);
        adapterJob.clear();
        adapterJob.addAll(jobList);
        adapterJob.notifyDataSetChanged();
    }

    public void deleteData() {
        if (checkedJob.getPriority() != 1) {
            Intent intent = new Intent(this.getActivity(), MyBroadcastReceiver.class);
            intent.putExtra("TIME_ID", checkedJob.getId());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) this.getActivity().getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }

        database.deleteJob(checkedJob);
        Toast.makeText(getActivity(), "Deleted job", Toast.LENGTH_SHORT).show();
        listView.setItemChecked(posItem, false);
        posItem = -1;
    }
}