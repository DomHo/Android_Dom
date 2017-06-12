package group33.goldenlist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GoldenList";

    // User table
    private static final String USER_TABLE = "USER";
    private static final String USER_ID = "USER_ID";
    private static final String USER_PASSCODE = "USER_PASSCODE";
    private static final String USER_BACKGROUND = "USER_BACKGROUND";

    // Time table
    private static final String TIME_TABLE = "TIMETABLE";
    private static final String TIME_ID = "TIME_ID";
    private static final String TIME_NAME = "TIME_NAME";
    private static final String TIME_NOTE = "TIME_NOTE";
    private static final String TIME_PRIORITY = "TIME_PRIORITY";
    private static final String TIME_DATE = "TIME_DATE";
    private static final String TIME_DAY = "TIME_DAY";
    private static final String TIME_START = "TIME_START";
    private static final String TIME_END = "TIME_END";
    private static final String TIME_FINISH = "TIME_FINISH";

    // Schedule table
    private static final String SCHED_TABLE = "SCHEDULE";
    private static final String SCHED_ID = "SCHED_ID";
    private static final String SCHED_NAME = "SCHED_NAME";
    private static final String SCHED_NOTE = "SCHED_NOTE";
    private static final String SCHED_ROOM = "SCHED_ROOM";
    private static final String SCHED_DATE = "SCHED_DATE";
    private static final String SCHED_DAY = "SCHED_DAY";
    private static final String SCHED_START = "SCHED_START";
    private static final String SCHED_END = "SCHED_END";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "("
                + USER_ID + " INTEGER PRIMARY KEY,"
                + USER_PASSCODE + " TEXT,"
                + USER_BACKGROUND + " INTEGER" + ")";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_TIME_TABLE = "CREATE TABLE " + TIME_TABLE + "("
                + TIME_ID + " INTEGER PRIMARY KEY,"
                + TIME_NAME + " TEXT,"
                + TIME_NOTE + " TEXT,"
                + TIME_PRIORITY + " INTEGER,"
                + TIME_DATE + " TEXT,"
                + TIME_DAY + " INTEGER,"
                + TIME_START + " TEXT,"
                + TIME_END + " TEXT,"
                + TIME_FINISH + " INTEGER" + ")";
        db.execSQL(CREATE_TIME_TABLE);

        String CREATE_SCHED_TABLE = "CREATE TABLE " + SCHED_TABLE + "("
                + SCHED_ID + " INTEGER PRIMARY KEY,"
                + SCHED_NAME + " TEXT,"
                + SCHED_NOTE + " TEXT,"
                + SCHED_ROOM + " TEXT,"
                + SCHED_DATE + " TEXT,"
                + SCHED_DAY + " INTEGER,"
                + SCHED_START + " TEXT,"
                + SCHED_END + " TEXT" + ")";
        db.execSQL(CREATE_SCHED_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TIME_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SCHED_TABLE);
        onCreate(db);
    }

    // Add passcode for first use
    public void addPasscode(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_PASSCODE, user.getPasscode());
        values.put(USER_BACKGROUND, 0);

        // Inserting Row
        db.insert(USER_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Add new job
    public void addJob(TimeTable job) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TIME_NAME, job.getName());
        values.put(TIME_NOTE, job.getNote());
        values.put(TIME_PRIORITY, job.getPriority());
        values.put(TIME_DATE, job.getDate());
        values.put(TIME_DAY, job.getDay());
        values.put(TIME_START, job.getStartTime());
        values.put(TIME_END, job.getEndTime());
        values.put(TIME_FINISH, job.getFinish());

        // Inserting Row
        db.insert(TIME_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Add new subject
    public void addSubject(Schedule subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SCHED_NAME, subject.getName());
        values.put(SCHED_NOTE, subject.getNote());
        values.put(SCHED_ROOM, subject.getRoom());
        values.put(SCHED_DATE, subject.getDate());
        values.put(SCHED_DAY, subject.getDay());
        values.put(SCHED_START, subject.getStartTime());
        values.put(SCHED_END, subject.getEndTime());

        // Inserting Row
        db.insert(SCHED_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Get passcode for check
    public String getCheckPass() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USER_TABLE, new String[] {USER_ID, USER_PASSCODE}, USER_ID + "=?",
                new String[] { String.valueOf(1) }, null, null, null, null);

        String checkPass = null;
        if (cursor != null) {
            cursor.moveToFirst();
            checkPass = cursor.getString(1);
            cursor.close();
        }
        return checkPass;
    }

    // Get user count
    public int getUsersCount() {
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT  * FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Get current background
    public String getCurBackground() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USER_TABLE, new String[] {USER_ID, USER_BACKGROUND}, USER_ID + "=?",
                new String[] { String.valueOf(1) }, null, null, null, null);

        String curBack = null;
        if (cursor != null) {
            cursor.moveToFirst();
            curBack = cursor.getString(1);
            cursor.close();
        }
        return curBack;
    }

    // Get ALL job in day
    public ArrayList<TimeTable> getAllJobs(int day) {
        ArrayList<TimeTable> jobList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TIME_TABLE
                + " WHERE " + TIME_DAY + " = " + Integer.toString(day)
                + " ORDER BY " + TIME_DATE + ", "+ TIME_START + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
            do {
                jobList.add(new TimeTable(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(8)
                ));
            } while (cursor.moveToNext());
        cursor.close();
        db.close();
        // return job list
        return jobList;
    }

    public String getIDLastRowJob() {
        String selectQuery = "SELECT * FROM " + TIME_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToLast();
        String id = cursor.getString(0);
        cursor.close();
        return id;
    }

    // Get ALL subject in day
    public ArrayList<Schedule> getAllSubjects(int day) {
        ArrayList<Schedule> subjectList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + SCHED_TABLE
                + " WHERE " + SCHED_DAY + " = " + Integer.toString(day)
                + " ORDER BY " + SCHED_DATE + ", "+ SCHED_START + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
            do {
                subjectList.add(new Schedule(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return subjectList;
    }

    // Update passcode
    public int updatePasscode(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_PASSCODE, user.getPasscode());

        // updating row
        db.update(USER_TABLE, values, USER_ID + " = ?",
                new String[] { "1" });
        db.close();
        return 1;
    }

    // Update background
    public int updateBackground(int background) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_BACKGROUND, background);

        // updating row
        db.update(USER_TABLE, values, USER_ID + " = ?",
                new String[] { "1" });
        db.close();
        return 1;
    }

    // Update single job
    public int updateJob(TimeTable job) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TIME_NAME, job.getName());
        values.put(TIME_NOTE, job.getNote());
        values.put(TIME_PRIORITY, job.getPriority());
        values.put(TIME_DATE, job.getDate());
        values.put(TIME_DAY, job.getDay());
        values.put(TIME_START,job.getStartTime());
        values.put(TIME_END,job.getEndTime());
        values.put(TIME_FINISH, job.getFinish());

        // updating row
        db.update(TIME_TABLE, values, TIME_ID + " = ?",
                new String[] { String.valueOf(job.getId()) });
        db.close();
        return 1;
    }

    // Update single subject
    public int updateSubject(Schedule subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SCHED_NAME, subject.getName());
        values.put(SCHED_NOTE, subject.getNote());
        values.put(SCHED_ROOM, subject.getRoom());
        values.put(SCHED_DATE, subject.getDate());
        values.put(SCHED_DAY, subject.getDay());
        values.put(SCHED_START, subject.getStartTime());
        values.put(SCHED_END, subject.getEndTime());

        // updating row
        db.update(SCHED_TABLE, values, SCHED_ID + " = ?",
                new String[] { String.valueOf(subject.getId()) });
        db.close();
        return 1;
    }

    // Delete single job
    public void deleteJob(TimeTable job) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TIME_TABLE, TIME_ID + " = ?",
                new String[]{String.valueOf(job.getId())});
        db.close();
    }

    // Delete single subject
    public void deleteSubject(Schedule delSubject) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SCHED_TABLE, SCHED_ID + " = ?",
                new String[]{String.valueOf(delSubject.getId())});
        db.close();
    }
}