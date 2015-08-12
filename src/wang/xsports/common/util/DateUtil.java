package wang.xsports.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final Long DAY = 1000 * 60 * 60 * 24L;
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    public static String getTowmorrowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date(System.currentTimeMillis() + DAY)) + "235959";
    }


    public static void main(String[] args) {
        System.out.println(getCurrentDate());
        System.out.println(getTowmorrowDate());
    }

    public static String formatDateTime8(String time, String Separator) {
        if (time == null) {
            return "";
        }
        StringBuffer ret = new StringBuffer();
        if (time != null && time.length() == 6) {
            ret.append("20" + time.substring(0, 2));
            ret.append(Separator);
            ret.append(time.substring(2, 4));
            ret.append(Separator);
            ret.append(time.substring(4, 6));
            return ret.toString();
        } else if (time != null && time.length() >= 8) {
            ret.append(time.substring(0, 4));
            ret.append(Separator);
            ret.append(time.substring(4, 6));
            ret.append(Separator);
            ret.append(time.substring(6, 8));
            return ret.toString();
        } else {
            return time;
        }
    }

    public static String formatDateTime12(String time, String Separator) {
        if (time == null) {
            return "";
        }
        StringBuffer ret = new StringBuffer();
        if (time != null && time.length() == 6) {
            ret.append("20" + time.substring(0, 2));
            ret.append(Separator);
            ret.append(time.substring(2, 4));
            ret.append(Separator);
            ret.append(time.substring(4, 6));
            ret.append(" ");
            ret.append("00");
            ret.append(":");
            ret.append("00");
            return ret.toString();
        } else if (time != null && time.length() == 8) {
            ret.append(time.substring(0, 4));
            ret.append(Separator);
            ret.append(time.substring(4, 6));
            ret.append(Separator);
            ret.append(time.substring(6, 8));
            ret.append(" ");
            ret.append("00");
            ret.append(":");
            ret.append("00");
            return ret.toString();
        } else if (time != null && time.length() == 14) {
            ret.append(time.substring(0, 4));
            ret.append(Separator);
            ret.append(time.substring(4, 6));
            ret.append(Separator);
            ret.append(time.substring(6, 8));
            ret.append(" ");
            ret.append(time.substring(8, 10));
            ret.append(":");
            ret.append(time.substring(10, 12));
            return ret.toString();
        } else {
            return time;
        }
    }

    public static Integer getDifferDays(String startDateStr, String endDateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        Date date1 = null, date2 = null;
        int days = 0;
        try {
            date1 = f.parse(startDateStr);
            date2 = f.parse(endDateStr);
            days = (int) ((date2.getTime() - date1.getTime()) / 86400000);
        } catch (Exception e) {
            return null;
        }

        return days;

    }
}
