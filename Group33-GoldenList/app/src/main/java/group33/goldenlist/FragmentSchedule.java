package group33.goldenlist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import group33.goldenlist.Database.Database;
import group33.goldenlist.Database.Schedule;

public class FragmentSchedule extends Fragment {
    ListView listView;
    List<Schedule> subjectList;

    scheduleAdapter adapterSubject;
    Database database;

    int day;
    int posItem = -1;
    static Schedule checkedSub = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment_listview = inflater.inflate(R.layout.fragment_schedule, container, false);

        database = new Database(this.getActivity());
        subjectList = database.getAllSubjects(day);

        adapterSubject = new scheduleAdapter(this.getActivity(), R.layout.item_schedule, subjectList);
        adapterSubject.notifyDataSetChanged();
        listView = (ListView) fragment_listview.findViewById(R.id.listview_schedule);
        listView.setAdapter(adapterSubject);

        return fragment_listview;
    }

    private class scheduleAdapter extends ArrayAdapter<Schedule> {
        Context context;
        int resource;
        List<Schedule> subjectList;

        scheduleAdapter(Context context, int resource, List<Schedule> objects) {
            super(context, resource, objects);

            this.context = context;
            this.resource = resource;
            this.subjectList = objects;
        }

        @NonNull
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

            final int[] arrow = {0};

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowItem = inflater.inflate(resource,parent,false);

            final TextView tvName = (TextView) rowItem.findViewById(R.id.tvSubjectName);
            final TextView tvNote = (TextView) rowItem.findViewById(R.id.tvSubjectNote);
            TextView tvRoom = (TextView) rowItem.findViewById(R.id.tvSubjectRoom);
            TextView tvStartTime = (TextView) rowItem.findViewById(R.id.tvSubjectStart);
            TextView tvEndTime = (TextView) rowItem.findViewById(R.id.tvSubjectEnd);
            final Button btnSubArrow = (Button) rowItem.findViewById(R.id.btnSubjectArrow);

            Schedule tempSub = subjectList.get(position);
            tvName.setText(tempSub.getName());
            tvNote.setText(tempSub.getNote());
            tvRoom.setText(tempSub.getRoom());
            tvStartTime.setText(tempSub.getStartTime());
            tvEndTime.setText(tempSub.getEndTime());

            if (tempSub.getNote().length() > 0)
                btnSubArrow.setVisibility(View.VISIBLE);

            btnSubArrow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    arrow[0]++;
                    switch (arrow[0]){
                        case 1:
                            btnSubArrow.setBackgroundResource(R.drawable.arrow_up);
                            tvNote.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            arrow[0] = 0;
                            btnSubArrow.setBackgroundResource(R.drawable.arrow_down);
                            tvNote.setVisibility(View.GONE);
                            break;
                    }
                }
            });

            rowItem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    listView.setItemChecked(position, true);
                    posItem = position;
                    checkedSub = (Schedule) listView.getItemAtPosition(posItem);
                }
            });

            return rowItem;
        }
    }

    public void selectedDay (int day) {
        this.day = day;
    }

    public  void loadSchedule(int selectDay){
        day = selectDay;
        listView.setItemChecked(posItem, false);
        posItem = -1;
        subjectList = database.getAllSubjects(selectDay);
        adapterSubject.clear();
        adapterSubject.addAll(subjectList);
        adapterSubject.notifyDataSetChanged();
    }

    public void deleteData() {
        database.deleteSubject(checkedSub);
        Toast.makeText(getActivity(), "Deleted subject", Toast.LENGTH_SHORT).show();
        listView.setItemChecked(posItem, false);
        posItem = -1;
    }
}