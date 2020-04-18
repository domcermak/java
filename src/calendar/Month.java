package calendar;

public class Month {
   public static Integer daysIn(Integer month, Integer year) {
      if (month == 1 ||
            month == 3 ||
            month == 5 ||
            month == 7 ||
            month == 8 ||
            month == 10 ||
            month == 12) {
         return 31;
      }
      if (month == 4 ||
            month == 6 ||
            month == 9 ||
            month == 11) {
         return 30;
      }
      if (Year.isLeap(year)) {
         return 29;
      }

      return 28;
   }
}
