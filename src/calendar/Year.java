package calendar;

public class Year {
   public static boolean isLeap(Integer year) {
      Integer theYear = year;

      if (theYear < 100) {
         if (theYear > 40) {
            theYear += 1900;
         } else {
            theYear += 2000;
         }
      }

      if (theYear % 4 == 0) {
         if (theYear % 100 != 0) {
            return true;
         } else return theYear % 400 == 0;
      } else {
         return false;
      }
   }

   public static Integer[] daysInMonths(Integer year) {
      Integer[] list = new Integer[12];

      for (int i = 0; i < 12; i++) {
         list[i] = Month.daysIn(i + 1, year);
      }

      return list;
   }
}
