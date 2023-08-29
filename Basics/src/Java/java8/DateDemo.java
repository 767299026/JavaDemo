package Java.java8;

import java.time.*;

//Java8定义了新的时间API来进一步加强对日期和时间的处理
public class DateDemo {
    public static void main(String[] args) {
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("当前日期: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();

        System.out.println("当前月: " + month + ", 当前日: " + day);

        //指定时间
        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }
}
