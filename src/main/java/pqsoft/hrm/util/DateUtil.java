package pqsoft.hrm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {
  public static final String DATE_FORMAT = "yyyy-MM-dd";
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm";
  public static final String JSON_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
  public static final String DATE_FORMAT_FOR_DISPLAY = "MMM dd, yyyy";
  public static final Calendar calendar = Calendar.getInstance();

  public static Date parse(String dateString) {
    try {
      return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
    } catch (Exception e) {
      log.info("Cannot parse {} with pattern yyyy-MM-dd", dateString);
      return null;
    }
  }

  public static String format(Date date) {
    return format(date, DATE_FORMAT);
  }

  public static String format(Date date, String pattern) {
    return format(date, pattern, TimeZone.getDefault());
  }

  public static String format(Date date, String pattern, TimeZone timezone) {
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    if (timezone != null) {
      formatter.setTimeZone(timezone);
    }
    return Optional.ofNullable(date).map(value -> formatter.format(value)).orElse("");
  }

  private static void getStartOfDay() {
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
  }

  private static void getEndOfDay() {
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
  }

  public static Date getStartOfDay(Date date) {
    calendar.setTime(date);
    getStartOfDay();
    return calendar.getTime();
  }

  public static Date getStartOfDay(String dateString) {
    return getStartOfDay(DateUtil.parse(dateString));
  }

  public static Date getStartOfToday() {
    return getStartOfDay(new Date());
  }

  public static Date getStartOfThisWeek() {
    calendar.setTime(new Date());
    calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
    getStartOfDay();
    return calendar.getTime();
  }

  public static Date getStartOfLastWeek() {
    calendar.setTime(new Date());
    calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
    calendar.add(Calendar.DATE, -7);
    getStartOfDay();
    return calendar.getTime();
  }

  public static Date getStartOfThisMonth() {
    calendar.setTime(new Date());
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    getStartOfDay();
    return calendar.getTime();
  }

  public static Date getStartOfLastMonth() {
    calendar.setTime(new Date());
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.add(Calendar.MONTH, -1);
    getStartOfDay();
    return calendar.getTime();
  }

  public static Date getEndOfDay(Date date) {
    calendar.setTime(date);
    getEndOfDay();
    return calendar.getTime();
  }

  public static Date getEndOfDay(String dateString) {
    return getEndOfDay(DateUtil.parse(dateString));
  }

  public static Date getEndOfToday() {
    return getEndOfDay(new Date());
  }

  public static Date getEndOfLastWeek() {
    calendar.setTime(new Date());
    calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
    calendar.add(Calendar.DATE, -1);
    getEndOfDay();
    return calendar.getTime();
  }
}
