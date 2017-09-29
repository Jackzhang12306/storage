package cn.com.ecict.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    /**
     * 获取默认格式的当前日期时间
     *
     * @return
     */
    public static String getCurrentDatetime() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }
    /**
     * 计算两个日期之间的间隔月份
     * @return
     */
    public static int between(String date1,String date2){
        LocalDate date_1=LocalDate.parse(date1.substring(0,10));
        LocalDate date_2=LocalDate.parse(date2.substring(0,10));
        Period period = Period.between(date_1,date_2);
        return period.getMonths();
    }

    /**
     * 计算两个日期之间的间隔月份
     * @return
     */
    public static int between(LocalDate date1,LocalDate date2){
        Period period = Period.between(date1,date2);
        return period.getMonths();
    }

}
