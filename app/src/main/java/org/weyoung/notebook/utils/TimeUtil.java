package org.weyoung.notebook.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Weyoung Org CopyRight 2015
 * http://talentprince.github.io
 * Created by PrinceChen on 15/1/24.
 */
public class TimeUtil {
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    public static String getDiff(String old, String now) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long diff = 0;
        try {
            Date d1 = formatter.parse(old);
            Date d2 = formatter.parse(now);
            diff = d2.getTime() - d1.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

        if (days != 0) {
            if (days > 1)
                return String.format(Locale.US, "%s day ago", days);
            else
                return String.format(Locale.US, "%s days ago", days);
        } else if (hours != 0) {
            if (hours > 1)
                return String.format(Locale.US, "%s hour ago", hours);
            else
                return String.format(Locale.US, "%s hours ago", hours);
        } else if (minutes != 0) {
            if (minutes < 1)
                return "Just now";
            else
                return String.format(Locale.US, "%s minutes ago", minutes);
        }
        return "Just now";
    }
}
