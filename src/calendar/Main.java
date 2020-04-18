package calendar;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
   public static void main(String[] args) {
      Date today = new Date(28, 2, 2019);
      Calendar march = Calendar.MonthForCurrentDay(today);

      System.out.println(march.prevMonth().toTable());
      System.out.println(march.toTable());
      System.out.println(march.nextMonth().toTable());

      System.out.println(Arrays.toString(Year.daysInMonths(2020)));

      System.out.println(march.getToday().toString().equals("28/2/2019"));
      System.out.println(today.toString().equals("28/2/2019"));
      System.out.println(today.dayInWeek() == 4);

      System.out.println(!Calendar.isLeapYear(2019));
      System.out.println(Calendar.isLeapYear(2020));
   }
}
