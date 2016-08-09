package nz.co.liuming.cityfriends.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by sreepolavarapu on 8/03/16.
 */
public class DateUtils {

    public static final String DATE_FORMAT_DATE = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_FORMAT_MY_REPORT = "dd/MM/yy - hh:mm a";

    /**
     * Helper to get the date string from date object in specified format.
     * @param date  Date object
     * @param dateFormat    Expected Format of the date
     * @return  The formatted date.
     */
    public static String getFormattedDate(Date date, String dateFormat)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

 /**
     * Helper to get the date object from date string which is in specified format.
     * @param date  Date String
     * @param dateFormat    Expected Format of the date
     * @return  The Date object in positive case, else null
     */
    public static Date getDateFromString(String date, String dateFormat)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper to get the UTC date object from utc date string which is in specified format.
     * @param utcDate  UTC Date String. Assumes date is based on UTC timezone
     * @param dateFormat    Expected Format of the date
     * @return  The Date object in positive case, else null
     */
    public static Date getUTCDateFromString(String utcDate, String dateFormat)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            return simpleDateFormat.parse(utcDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert UTC date to local time zone.
     *
     * @param utcDate
     * 		UTC date to be converted.
     *
     * @return Date converted to local time zone.
     */
    public static Date convertUtcToLocalDate(Date utcDate) {
        Calendar utcCalendar = getNewUtcCalendar();

        utcCalendar.setTimeInMillis(utcDate.getTime());
        utcCalendar.setTimeZone(TimeZone.getDefault());
        return utcCalendar.getTime();
    }

    private static Calendar getNewUtcCalendar() {
        return new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    }
}
