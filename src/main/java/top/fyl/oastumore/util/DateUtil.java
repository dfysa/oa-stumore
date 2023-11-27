package top.fyl.oastumore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author dfysa
 * @data 26/11/2023 下午6:50
 * @description
 */
public class DateUtil {
    public static Boolean checkHours(String startTimeStr, String endTimeStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startTime = format.parse(startTimeStr);
            Date endTime = format.parse(endTimeStr);
            long diff = endTime.getTime() - startTime.getTime();
            long hours = diff / (1000 * 60 * 60);
            return hours >= 72L;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean checkHours(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toHours() >= 72L;
    }

    public static String dateFormat(LocalDateTime time) {
        int year = time.getYear();
        int month = time.getMonthValue();
        int day = time.getDayOfMonth();
        int hour = time.getHour();
        return String.format("%04d-%02d-%02d-%02d 时", year, month, day, hour);
    }
}