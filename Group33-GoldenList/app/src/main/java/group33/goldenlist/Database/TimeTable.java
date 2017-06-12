package group33.goldenlist.Database;

public class TimeTable {
    private int    id;
    private String name;
    private String note;
    private int priority;
    private String date;
    private int day;
    private String startTime;
    private String endTime;
    private int finish;

    public TimeTable(int id, String name, String note, int priority, String date, int day, String startTime, String endTime, int finish) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.priority = priority;
        this.date = date;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.finish = finish;
    }

    // GET METHODS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public int getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    public int getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getFinish() {
        return finish;
    }

    // SET METHODS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }
}