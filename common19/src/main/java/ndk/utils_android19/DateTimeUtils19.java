package ndk.utils_android19;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils19 {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Format a date with the given pattern
     * @param date the date to format
     * @param pattern the pattern to use
     * @return formatted date string
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * Format a date with the default date format
     * @param date the date to format
     * @return formatted date string
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * Format current date with the given pattern
     * @param pattern the pattern to use
     * @return formatted date string
     */
    public static String getCurrentDate(String pattern) {
        return formatDate(new Date(), pattern);
    }

    /**
     * Get current date with default format
     * @return formatted current date string
     */
    public static String getCurrentDate() {
        return getCurrentDate(DEFAULT_DATE_FORMAT);
    }

    /**
     * Get current time with default format
     * @return formatted current time string
     */
    public static String getCurrentTime() {
        return formatDate(new Date(), DEFAULT_TIME_FORMAT);
    }

    /**
     * Get current date and time with default format
     * @return formatted current datetime string
     */
    public static String getCurrentDateTime() {
        return formatDate(new Date(), DEFAULT_DATETIME_FORMAT);
    }

    /**
     * Parse a date string with the given pattern
     * @param dateString the date string to parse
     * @param pattern the pattern to use
     * @return parsed Date object or null if parsing fails
     */
    public static Date parseDate(String dateString, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Parse a date string with default format
     * @param dateString the date string to parse
     * @return parsed Date object or null if parsing fails
     */
    public static Date parseDate(String dateString) {
        return parseDate(dateString, DEFAULT_DATE_FORMAT);
    }

    /**
     * Get the difference between two dates in the specified time unit
     * @param date1 first date
     * @param date2 second date
     * @param timeUnit the time unit for the result
     * @return difference in the specified time unit
     */
    public static long getDateDifference(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillis = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Get the difference between two dates in days
     * @param date1 first date
     * @param date2 second date
     * @return difference in days
     */
    public static long getDateDifferenceInDays(Date date1, Date date2) {
        return getDateDifference(date1, date2, TimeUnit.DAYS);
    }

    /**
     * Add days to a date
     * @param date the date to add days to
     * @param days number of days to add (can be negative)
     * @return new Date object with days added
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * Add months to a date
     * @param date the date to add months to
     * @param months number of months to add (can be negative)
     * @return new Date object with months added
     */
    public static Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * Add years to a date
     * @param date the date to add years to
     * @param years number of years to add (can be negative)
     * @return new Date object with years added
     */
    public static Date addYears(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }

    /**
     * Check if a date is today
     * @param date the date to check
     * @return true if the date is today, false otherwise
     */
    public static boolean isToday(Date date) {
        Calendar today = Calendar.getInstance();
        Calendar checkDate = Calendar.getInstance();
        checkDate.setTime(date);
        
        return today.get(Calendar.YEAR) == checkDate.get(Calendar.YEAR) &&
               today.get(Calendar.DAY_OF_YEAR) == checkDate.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Check if a date is in the past
     * @param date the date to check
     * @return true if the date is in the past, false otherwise
     */
    public static boolean isPast(Date date) {
        return date.before(new Date());
    }

    /**
     * Check if a date is in the future
     * @param date the date to check
     * @return true if the date is in the future, false otherwise
     */
    public static boolean isFuture(Date date) {
        return date.after(new Date());
    }

    /**
     * Get a user-friendly time ago string (e.g., "2 hours ago", "3 days ago")
     * @param date the date to format
     * @return user-friendly time ago string
     */
    public static String getTimeAgo(Date date) {
        long diffInMillis = new Date().getTime() - date.getTime();
        
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
        long hours = TimeUnit.MILLISECONDS.toHours(diffInMillis);
        long days = TimeUnit.MILLISECONDS.toDays(diffInMillis);
        
        if (seconds < 60) {
            return "just now";
        } else if (minutes < 60) {
            return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago";
        } else if (hours < 24) {
            return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
        } else if (days < 30) {
            return days + " day" + (days > 1 ? "s" : "") + " ago";
        } else if (days < 365) {
            long months = days / 30;
            return months + " month" + (months > 1 ? "s" : "") + " ago";
        } else {
            long years = days / 365;
            return years + " year" + (years > 1 ? "s" : "") + " ago";
        }
    }
}
