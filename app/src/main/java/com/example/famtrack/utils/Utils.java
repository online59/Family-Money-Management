package com.example.famtrack.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String getDateTime(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        Date date = calendar.getTime();
        return date.toString();
    }

    public static String getDate(long timestamp) {
        Date paymentDate = new Date();
        paymentDate.setTime(timestamp);
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        return format.format(paymentDate);
    }

    public static String getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        return format.format(calendar.getTime());
    }

    public static long getDateInLong(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime().getTime();
    }

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        return format.format(calendar.getTime());
    }

    public static long getSystemCurrentTimeInMilli() {
        return System.currentTimeMillis();
    }
}
