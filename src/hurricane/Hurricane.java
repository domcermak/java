package hurricane;

public class Hurricane {
   private Integer year, month;
   private Double pressureInMillibars, speedInKnotsPerHour;
   private String name;
   private final Double SPEED_CONVERSION_COEFFICIENT = 1.852;

   public Hurricane(
         Integer year,
         Integer month,
         Double pressureInMillibars,
         Double speedInKnotsPerHour,
         String name
   ) {
      this.year = year;
      this.month = month;
      this.pressureInMillibars = pressureInMillibars;
      this.speedInKnotsPerHour = speedInKnotsPerHour;
      this.name = name;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();

      builder.append("name: ");
      builder.append(String.format("%10s", name));
      builder.append("; period: ");
      builder.append(String.format("%5d",month));
      builder.append("/");
      builder.append(year);
      builder.append("; pressure: ");
      builder.append(String.format("%10.2f", pressureInMillibars));
      builder.append(" mb");
      builder.append("; speed: ");
      builder.append(String.format("%10.2f", speedInKnotsPerHour));
      builder.append(" kt/h (");
      builder.append(String.format("%.2f", getSpeedInKilometersPerHour()));
      builder.append(" km/h)");
      builder.append("; category: ");
      builder.append(getCategory());

      return builder.toString();
   }

   public Integer getYear() {
      return year;
   }

   public Integer getMonth() {
      return month;
   }

   public Double getPressureInMillibars() {
      return pressureInMillibars;
   }

   public Double getSpeedInKnotsPerHour() {
      return speedInKnotsPerHour;
   }

   public Integer getCategory() {
      if (getSpeedInKilometersPerHour() < 153.0) {
         return 1;
      } else if (getSpeedInKilometersPerHour() < 177.0) {
         return 2;
      } else if (getSpeedInKilometersPerHour() < 208.0) {
         return 3;
      } else if (getSpeedInKilometersPerHour() < 251.0) {
         return 4;
      } else {
         return 5;
      }
   }

   public Double getSpeedInKilometersPerHour() {
      return speedInKnotsPerHour * SPEED_CONVERSION_COEFFICIENT;
   }

   public String getName() {
      return name;
   }
}
