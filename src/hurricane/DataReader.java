package hurricane;

import java.io.*;

public class DataReader {
   private String filepath;

   public static DataReader InitWithFilepath(String filepath) {
      return new DataReader(filepath);
   }

   public HurricaneData readHurricanes() throws IOException {
      HurricaneData data = new HurricaneData();

      try (BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)))) {
         String line;
         while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().replaceAll(" +", " ").split(" ");
            if (parts.length != 5) {
               throw new InvalidFileContentException("Line {" + line + "} in not valid");
            }

            Integer year = Integer.parseInt(parts[0]);
            Integer month = Integer.parseInt(parts[1]);
            Double pressure = Double.parseDouble(parts[2]);
            Double speed = Double.parseDouble(parts[3]);
            String name = parts[4];

            data.add(
                  new Hurricane(year, month, pressure, speed, name)
            );
         }
      }

      return data;
   }

   private DataReader(String filepath) {
      this.filepath = filepath;
   }

   static class InvalidFileContentException extends IOException {
      public InvalidFileContentException(String message) {
         super(message);
      }
   }
}
