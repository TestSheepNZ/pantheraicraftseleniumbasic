package testsheepnz.panther.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogNamingUtil {

    private static String prevUniqueName;
    private static int increment;

    public LogNamingUtil() {
        prevUniqueName = "";
        increment = 0;
    }



    public static String getCurrentDate () {
        String dateString = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        return dateString;
    }

    public static String getCurrentDateWithSeconds () {
        String dateString = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return dateString;
    }

    public static String getCurrentDateToMillisecond() {
        String dateString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return dateString;
    }

    public static String getJustTime() {
        String dateString = new SimpleDateFormat("HHmm:ss").format(new Date());
        return dateString;
    }

    public static String getJustDate() {
        String dateString = new SimpleDateFormat("dd/MM/YYYY").format(new Date());
        return dateString;
    }
}