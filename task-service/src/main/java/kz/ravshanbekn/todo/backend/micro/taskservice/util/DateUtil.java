package kz.ravshanbekn.todo.backend.micro.taskservice.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date getDateBeginning(Date date) {
        Calendar calendarFrom = Calendar.getInstance();
        calendarFrom.setTime(date);
        calendarFrom.set(Calendar.HOUR_OF_DAY, 0);
        calendarFrom.set(Calendar.MINUTE, 1);
        calendarFrom.set(Calendar.SECOND, 1);
        calendarFrom.set(Calendar.MILLISECOND, 1);

        return calendarFrom.getTime();
    }

    public static Date getDateEnding(Date date) {
        Calendar calendarTo = Calendar.getInstance();
        calendarTo.setTime(date);
        calendarTo.set(Calendar.HOUR_OF_DAY, 23);
        calendarTo.set(Calendar.MINUTE, 59);
        calendarTo.set(Calendar.SECOND, 59);
        calendarTo.set(Calendar.MILLISECOND, 999);

        return calendarTo.getTime();
    }
}
