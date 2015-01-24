package org.weyoung.notebook.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
