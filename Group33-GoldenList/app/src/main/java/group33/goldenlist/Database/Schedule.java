package group33.goldenlist.Database;

public class Schedule {
    private int    id;
    private String name;
    private String note;
    private String room;
    private String date;
    private int    day;
    private String startTime;
    private String endTime;

    public Schedule(int id, String name, String note, String room, String date, int day, String startTime, String endTime) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.room = room;
        this.date = date;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getRoom()
    {
        return room;
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

    public void setRoom(String room)
    {
        this.room = room;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}