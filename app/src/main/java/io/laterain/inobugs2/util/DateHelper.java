package io.laterain.inobugs2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dengyuchi on 2/17/17.
 */

public class DateHelper {

    public final static String STR_DATE_FLRMAT = "yyyy-MM-dd";
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat(STR_DATE_FLRMAT);

    public static String getFormattedDateStringForNow() {
        return dateFormat.format(new Date());
    }

    public static long convertToTimeStampFromString(String str) throws ParseException {
        dateFormat.setLenient(false);
        return dateFormat.parse(str).getTime();
    }

    public static String convertToFormattedDateString(long timeStamp) {
        Date date = new Date();
        date.setTime(timeStamp);
        return dateFormat.format(date);
    }
}
