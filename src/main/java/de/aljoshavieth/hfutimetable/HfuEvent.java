package de.aljoshavieth.hfutimetable;

/**
 * Created by Aljosha on 16.02.2019
 */
public class HfuEvent {
    private String startTime;
    private String prof;
    private String name;
    private String endTime;
    private String timePeriod;
    private String location;


    HfuEvent(String prof, String name, String timePeriod, String location) {
        this.prof = prof;
        this.name = name;
        this.timePeriod = timePeriod;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }


}
