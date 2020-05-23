package hurricane;

import java.io.IOException;

public class Main {
   public static void main(String[] args) {
      DataReader reader = DataReader.InitWithFilepath(System.getProperty("user.dir") + "/src/hurricane/data/hurricane.txt");
      HurricaneData data;

      try {
         data = reader.readHurricanes();
      } catch (IOException e) {
         System.out.println(e.getMessage());
         return;
      }

      System.out.println("raw data");
      System.out.println(data.toString());

      System.out.println("select by year range");
      HurricaneData selectedDataByDateRange = data.select(hurricane -> {
         return hurricane.getYear() >= 2001 && hurricane.getYear() <= 2011;
      });
      System.out.println(selectedDataByDateRange.toString());

      System.out.println("select by name");
      HurricaneData selectedDataByName = data.select(hurricane -> {
         return hurricane.getName().equals("Cathrin");
      });
      StringBuilder builder = new StringBuilder();
      if (selectedDataByName.isEmpty()) {
         builder.append("Hurricane with name not found");
      } else {
         for (Hurricane hurricane : selectedDataByName) {
            builder.append("name: ");
            builder.append(String.format("%10s", hurricane.getName()));
            builder.append("; speed: ");
            builder.append(String.format("%10.2f", hurricane.getSpeedInKnotsPerHour()));
            builder.append(" kt/h (");
            builder.append(String.format("%.2f", hurricane.getSpeedInKilometersPerHour()));
            builder.append(" km/h)");
            builder.append("; category: ");
            builder.append(hurricane.getCategory());
            builder.append("\n");
         }
      }
      System.out.println(builder.toString());

      System.out.println("sort by speed");
      data.sort(new HurricaneComparatorBySpeed());
      System.out.println(data.toString());
   }
}
