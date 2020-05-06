package cmd;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Dir extends Command {
   @Override
   public CommandState execute(File actualDir) {
      File[] files = actualDir.listFiles();
      String message;

      if (params.length > 2) {
         return new CommandState("dir očekává 0 - 2 parametry, dostalo: " + params.length, actualDir);
      }

      if (params.length == 0) {
         return new CommandState(dirToString(files), actualDir);
      } else if (params.length == 1 && params[0].equals("-o")) {
         Arrays.sort(files);
         return new CommandState(dirToString(files), actualDir);
      } else if (params.length == 2) {
         switch (params[0]) {
            case "-e":
               message = dirToString(selectWithExtension(files, params));
               return new CommandState(message, actualDir);
            case "-s":
               message = dirToString(selectBySize(files, params));
               return new CommandState(message, actualDir);
         }
      }

      return null;
   }

   private String dirToString(File[] files) {
      StringBuilder sb = new StringBuilder("");
      for (File file : files) {
         if (file.isDirectory()) {
            sb.append(String.format("%s%n", file.getName()));
         } else {
            sb.append(String.format("%-20s%6d", file.getName(), file.length()));
            sb.append(new Date(file.lastModified())).append("\n");
         }
      }
      return sb.toString();
   }

   private File[] selectWithExtension(File[] files, String[] params) {
      ArrayList<File> selected = new ArrayList<>();
      File tmp[];

      for (File file : files) {
         if (file.toString().endsWith(params[1])) {
            selected.add(file);
         }
      }
      tmp = new File[selected.size()];
      selected.toArray(tmp);

      return tmp;
   }

   private File[] selectBySize(File[] files, String[] params) {
      ArrayList<File> selected = new ArrayList<>();
      File tmp[];

      for (File file : files) {
         if (file.length() >= Integer.parseInt(params[1])) {
            selected.add(file);
         }
      }
      tmp = new File[selected.size()];
      selected.toArray(tmp);

      return tmp;
   }
}