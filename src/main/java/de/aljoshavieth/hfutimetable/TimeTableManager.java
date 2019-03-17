package de.aljoshavieth.hfutimetable;

import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.io.text.ICalReader;
import biweekly.property.DateStart;
import biweekly.property.Description;
import biweekly.util.ICalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/** Created by Aljosha on 16.02.2019 */
public class TimeTableManager {

  public ArrayList<HfuEvent> getEventsPerDate(Date date, File calender) {
    ArrayList<HfuEvent> hfuEvents = new ArrayList<>();
    ICalReader reader = null;
    try {
      reader = new ICalReader(calender);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      ICalendar ical;
      if (reader != null) {
        while ((ical = reader.readNext()) != null) {
          for (VEvent event : ical.getEvents()) {
            DateStart dateStart = event.getDateStart();
            if (getZeroTimeDate(dateStart.getValue()).equals(new ICalDate(date))) {
              Description description = event.getDescription();
              String location =
                  event.getLocation().getValue().substring(2).replaceAll("([A-Z])(.*)", "$1 $2");
              System.out.println(location);
              String[] values = description.getValue().split("\n");
              int startHour = event.getDateStart().getValue().getRawComponents().getHour();
              int endHour = event.getDateEnd().getValue().getRawComponents().getHour();
              int startMin = event.getDateStart().getValue().getRawComponents().getMinute();
              int endMin = event.getDateStart().getValue().getRawComponents().getMinute();
              HfuEvent hfuEvent =
                  new HfuEvent(
                      values[1],
                      values[0],
                      String.format("%02d:%02d - %02d:%02d", startHour, startMin, endHour, endMin),
                      location);
              hfuEvents.add(hfuEvent);
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        assert reader != null;
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    Collections.reverse(hfuEvents);
    return hfuEvents;
  }

  private Date getZeroTimeDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    date = calendar.getTime();
    return date;
  }

  /*
  String sDate1="22/01/2019";
  Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
   */

}
