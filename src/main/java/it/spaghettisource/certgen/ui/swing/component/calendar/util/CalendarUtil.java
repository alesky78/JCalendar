package it.spaghettisource.certgen.ui.swing.component.calendar.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import it.spaghettisource.certgen.ui.swing.component.calendar.model.CalendarEvent;

/** 
 * 
 * @author Alessandro D'Ottavio
 */
public class CalendarUtil {

    public static boolean isSameDay(final Date date1, final Date date2) {
        return stripTime(date1).equals(stripTime(date2));
    }
    
    public static boolean isDayBefore(final Date date1, final Date date2) {
        return stripTime(date1).before(stripTime(date2));
    }
    
    public static boolean isDayAfter(final Date date1, final Date date2) {
        return stripTime(date1).after(stripTime(date2));
    }

    public static boolean isSameMonth(final Calendar c1, final Calendar c2) {
        return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
    }
    
    public static boolean isToday(final Date date) {
        final Calendar now = Calendar.getInstance();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().equals(now.getTime());
    }

    public static Calendar copyCalendar(final Calendar calendar, final boolean stripTime) {
        final Calendar c = Calendar.getInstance();
        c.setTime(calendar.getTime());
        if (stripTime) {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
        }
        return c;
    }

    public static Calendar getCalendar(final Date date, final boolean stripTime) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (stripTime) {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
        }
        return c;
    }
    

    public static boolean isInRange(final Calendar toCheck, final Calendar startRange, final Calendar endRange) {
        return (toCheck.after(startRange) && toCheck.before(endRange));
    }
    

    public static Date createDate(final int year, final int month, final int day, final int hour,final int minutes, final int seconds, final int miliseconds) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        calendar.set(Calendar.MILLISECOND, miliseconds);
        return calendar.getTime();
    }

    public static Date stripTime(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date createInDays(final Date from, final int amount) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        cal.add(Calendar.DATE, amount);
        return cal.getTime();
    }

    public static Date createInWeeks(final Date date, final int amount) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, amount);
        return cal.getTime();
    }

    public static Date createInMonths(final Date date, final int amount) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, amount);
        return cal.getTime();
    }

    public static Collection<Date> getDates(final Date start, final Date end) {
        final Set<Date> result = new HashSet<Date>();
        final Date endDay = stripTime(end);
        Date date = stripTime(start);
        result.add(date);
        while ((date = stripTime(createInDays(date, 1))).before(endDay))
            result.add(date);

        result.add(endDay);
        return result;
    }
    
    public static ArrayList<Date> getDatesSort(final Date start, final Date end) {
    	ArrayList<Date> result = new ArrayList<Date>(getDates(start, end));
        Collections.sort(result);
        return result;
    }
    
    public static long daysBetween(Date start,Date end) {
    	long dateBeforeInMs = start.getTime();
    	long dateAfterInMs = end.getTime();
    	long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
    	return TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
    }
    
    public static int amountOfDays(Date start,Date end) {
    	return getDates(start, end).size();
    }

    public static long getTotalSeconds(final Date date) {
        final Calendar c = CalendarUtil.getCalendar(date, false);
        long seconds = c.get(Calendar.HOUR_OF_DAY) * 60 * 60;
        seconds += c.get(Calendar.MINUTE) * 60;
        seconds += c.get(Calendar.SECOND);
        return seconds;
    }
    


    public static int secondsToPixels(final Date date, final int maxHeight) {
        final long seconds = getTotalSeconds(date);
        return Math.round(seconds * maxHeight / 86400.0f);
    }

    public static Date pixelToDate(final Date day, final int posY, final int maxHeight) {
        final long seconds = Math.round(posY * 86400.0f / maxHeight);
        final Calendar c = CalendarUtil.getCalendar(day, false);
        c.set(Calendar.HOUR_OF_DAY, (int) (seconds / 3600));
        c.set(Calendar.MINUTE, (int) (seconds % 3600) / 60);
        c.set(Calendar.SECOND, (int) (seconds % 3600) % 60);
        return c.getTime();
    }

    public static Map<CalendarEvent, List<CalendarEvent>> getConflicting(final Collection<CalendarEvent> calendarEvents) {
        final List<CalendarEvent> clonedCollection = new ArrayList<CalendarEvent>(calendarEvents);

        final Map<CalendarEvent, List<CalendarEvent>> conflictingEvents = new HashMap<CalendarEvent, List<CalendarEvent>>();

        for (int i = 0; i < clonedCollection.size(); i++) {
            final CalendarEvent event1 = clonedCollection.get(i);
             conflictingEvents.put(event1, new ArrayList<CalendarEvent>());
            for (int j = 0; j < clonedCollection.size(); j++) {
                final CalendarEvent event2 = clonedCollection.get(j);
                final Date startA = event1.getStart();
                final Date endA = event1.getEnd();
                final Date startB = event2.getStart();
                final Date endB = event2.getEnd();

                final boolean isStartABeforeEndB = (startA.compareTo(endB)) < 0;
                final boolean isEndAAfterStartB = (endA.compareTo(startB)) > 0;

                boolean isCurrentPairOverlap = false;

                isCurrentPairOverlap = isStartABeforeEndB && isEndAAfterStartB;

                if (isCurrentPairOverlap) {
                    conflictingEvents.get(event1).add(event2);
                }
            }

            Collections.sort(conflictingEvents.get(event1));
        }
        final Set<CalendarEvent> keys = new HashSet<CalendarEvent>(conflictingEvents.keySet());
        final Map<CalendarEvent, List<CalendarEvent>> result = new HashMap<CalendarEvent, List<CalendarEvent>>();

        for (final CalendarEvent event : keys) {
            final Set<CalendarEvent> visitedEvents = new HashSet<CalendarEvent>();
            final Set<CalendarEvent> tempSet = new HashSet<CalendarEvent>();
            copyAll(visitedEvents, tempSet, conflictingEvents, event);
            final List<CalendarEvent> newConflictingEventsList = new ArrayList<CalendarEvent>(tempSet);
            Collections.sort(newConflictingEventsList);
            result.put(event, newConflictingEventsList);
        }

        return result;
    }

    private static void copyAll(final Set<CalendarEvent> visitedEvents, final Set<CalendarEvent> tempSet,
                                final Map<CalendarEvent, List<CalendarEvent>> conflictingEvents, final CalendarEvent event) {

        for (final CalendarEvent ce : conflictingEvents.get(event)) {

            if (!visitedEvents.contains(ce)) {
                visitedEvents.add(ce);
                tempSet.addAll(conflictingEvents.get(ce));
                copyAll(visitedEvents, tempSet, conflictingEvents, ce);
            }
        }
    }

    public static Date roundDateToHalfAnHour(Date date, boolean roundUp) {
        Calendar c = getCalendar(date, false);
        int time = c.get(Calendar.MINUTE);

        if (time <= 30) {
            c.set(Calendar.MINUTE, roundUp ? 30 : 0);
        } else if (time > 30) {
            c.set(Calendar.MINUTE, roundUp ? 0 : 30);
            if(roundUp)
            c.add(Calendar.HOUR, 1);
        }
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

}
