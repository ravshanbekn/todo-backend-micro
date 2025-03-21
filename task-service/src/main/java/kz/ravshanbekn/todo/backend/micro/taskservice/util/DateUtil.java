package kz.ravshanbekn.todo.backend.micro.taskservice.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static LocalDateTime getStartOfDay(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .atStartOfDay();
    }

    public static LocalDateTime getEndOfDay(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .atTime(LocalTime.MAX);
    }
}
