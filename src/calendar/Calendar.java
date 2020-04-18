package calendar;

import java.util.ArrayList;

public class Calendar {
   private ArrayList<Date> datesInMonth;
   private Date currentDate;

   public static boolean isLeapYear(Integer year) {
      return Year.isLeap(year);
   }

   public static Calendar MonthForCurrentDay(Date currentDate) {
      return new Calendar(currentDate);
   }

   public Calendar prevMonth() {
      return new Calendar(currentDate.firstDayOfPrevMonth());
   }

   public Date getToday() {
      return this.currentDate;
   }

   public Calendar nextMonth() {
      return new Calendar(currentDate.firstDayOfNextMonth());
   }

   public String toTable() {
      //              1/2019
      //  mon tue wed thu fri sat sun
      // +---+---+---+---+---+---+---+
      // |   | 1 | 2 | 3 | 4 | 5 | 6 |
      // +---+---+---+---+---+---+---+
      // | 7 | 8 | 9 | 10| 11| 12| 13|
      // +---+---+---+---+---+---+---+
      // | 14| 15| 16| 17| 18| 19| 20|
      // +---+---+---+---+---+---+---+
      // | 21| 22| 23| 24| 25| 26| 27|
      // +---+---+---+---+---+---+---+
      // | 28| 29| 30| 31|   |   |   |
      // +---+---+---+---+---+---+---+


      String verticalSpacer = "+---+---+---+---+---+---+---+\n";
      String title = currentDate.getMonth() + "/" + currentDate.getYear();
      StringBuilder builder = new StringBuilder("            " + title + "\n");
      Integer inLine = 0;
      Date firstDate = datesInMonth.get(0);

      builder.append(" mon tue wed thu fri sat sun\n");
      builder.append(verticalSpacer);

      for (int i = 1; i < firstDate.dayInWeek(); i++) {
         builder.append("|   ");
         inLine++;
      }

      for (Date date : datesInMonth) {
         builder.append("| ").append(date.getDay());
         if (date.getDay().toString().length() == 1) {
            builder.append(" ");
         }
         inLine++;
         if (inLine % 7 == 0) {
            builder.append("|\n");
            builder.append(verticalSpacer);
            inLine = 0;
         }
      }

      if (inLine != 0) {
         for (Integer i = 7 - inLine; i > 0; i--) {
            builder.append("|   ");
         }
         builder.append("|\n");
         builder.append(verticalSpacer);
      }


      return builder.toString();
   }

   private static Integer[] daysCountInMonthsInYear(Integer year) {
      return Year.daysInMonths(year);
   }

   private Calendar(Date currentDate) {
      this.currentDate = currentDate;
      this.datesInMonth = new ArrayList<>();

      Integer daysCnt = Month.daysIn(currentDate.getMonth(), currentDate.getYear());
      for (int i = 1; i <= daysCnt; i++) {
         this.datesInMonth.add(
               new Date(i, currentDate.getMonth(), currentDate.getYear())
         );
      }
   }
}
