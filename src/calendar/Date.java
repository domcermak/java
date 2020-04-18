package calendar;

public class Date {
   private Integer day, month, year;
   private boolean current;

   public Date(Integer day, Integer month, Integer year) {
      if (!isValidDate(day, month, year)) {
         throw new IllegalArgumentException("Invalid date " + day + "/" + month + "/" + year);
      }
      this.day = day;
      this.month = month;
      this.year = year;
   }

   @Override
   public String toString() {
      return day + "/" + month + "/" + year;
   }

   // Proudly stolen from:
   //
   public Integer dayInWeek() {
      Integer dc = day, mc = month, yc = year;

      if (mc == 1 || mc == 2) {
         mc = (mc == 1) ? 13 : 14;
         yc--;
      }

      // Calculate day of the week
      int dayOfWeek = (dc + (26 * (mc + 1)) / 10 + (yc % 100)
            + (yc % 100) / 4 + (yc / 100) / 4 + 5 * (yc / 100)) % 7;

      switch (dayOfWeek) {
         case 0:
            return 6;
         case 1:
            return 7;
         case 2:
            return 1;
         case 3:
            return 2;
         case 4:
            return 3;
         case 5:
            return 4;
         default:
            return 5;
      }
   }

   public Integer getDay() {
      return this.day;
   }

   public Integer getMonth() {
      return this.month;
   }

   public Integer getYear() {
      return this.year;
   }

   public Date firstDayOfNextMonth() {
      if (month == 12) {
         return new Date(1, 1, year + 1);
      }
      return new Date(1, month + 1, year);
   }

   public Date firstDayOfPrevMonth() {
      if (month == 1) {
         return new Date(1, 12, year - 1);
      }
      return new Date(1, month - 1, year);
   }

   private boolean isValidDate(Integer day, Integer month, Integer year) {
      return !(month < 1 || month > 12 ||
            day < 1 || day > Month.daysIn(month, year));
   }
}
