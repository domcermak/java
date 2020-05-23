package cmd;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
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
         return new CommandState(dirToString(actualDir, files), actualDir);
      } else if (params.length == 1 && params[0].equals("-o")) {
         Arrays.sort(files);
         return new CommandState(dirToString(actualDir, files), actualDir);
      } else if (params.length == 2) {
         switch (params[0]) {
            case "-e":
               message = dirToString(actualDir, selectWithExtension(files, params));
               return new CommandState(message, actualDir);
            case "-s":
               message = dirToString(actualDir, selectBySize(files, params));
               return new CommandState(message, actualDir);
            case "--tree":
               Integer maxDepth = Integer.parseInt(params[1]);
               message = dirTreeToString(actualDir, 0, maxDepth);
               return new CommandState(message, actualDir);
            default:

         }
      }

      return null;
   }

   private String dirToString(File currentDir, File[] files) {
      StringBuilder builder = new StringBuilder();
      builder.append(currentDir.getAbsolutePath());
      builder.append("\n");

      for (File file : files) {
         if (file.isDirectory()) {
            builder.append(String.format("%s%n", file.getName()));
         } else {
            builder.append(String.format("%-20s%6d", file.getName(), file.length()));
            builder.append(new Date(file.lastModified())).append("\n");
         }
      }
      return builder.toString();
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

   private String dirTreeToString(
         File currentDir,
         Integer currentDepth,
         Integer maxDepth
   ) {
      StringBuilder builder = new StringBuilder();
      File[] files = currentDir.listFiles();

      builder.append(prepender(currentDepth, true));
      if (currentDepth == 0) {
         builder.append(currentDir.getAbsolutePath());
      } else {
         builder.append(String.format("%s", currentDir.getName()));
      }
      builder.append("\n");

      if (files.length == 0) {
         return builder.toString();
      }

      for (File file : files) {
         if (!file.isDirectory()) {
            builder.append(prepender(currentDepth, false));
            builder.append(String.format("%-40s%6d", file.getName(), file.length()));
            builder.append("\n");
         }
      }

      for (File file : files) {
         if (file.isDirectory()) {
            if (maxDepth <= currentDepth) {
               builder.append(prepender(currentDepth, true));
               builder.append(String.format("%s%n", file.getName()));
            } else {
               String subtree = dirTreeToString(file, currentDepth + 1, maxDepth);
               builder.append(subtree);
            }
         }
      }
      return builder.toString();
   }

   private String prepender(Integer level, Boolean isDir) {
      StringBuilder builder = new StringBuilder();
      Integer newLevel = isDir ? level - 1 : level;

      for (int i = 0; i < newLevel; i++) {
         builder.append("|   ");
      }
      if (newLevel >= 0) {
         builder.append("+--- ");
      }

      return builder.toString();
   }
}