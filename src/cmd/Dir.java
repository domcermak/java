package cmd;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

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

      message = "Undefined option(s) " + Arrays.toString(params) + " for command `dir`\n\nSee `help`";
      return new CommandState(message, actualDir);
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
      builder.append(treeDir(currentDir, currentDepth));

      if (Objects.requireNonNull(currentDir.listFiles()).length == 0) {
         return builder.toString();
      }

      builder.append(treeSubfiles(currentDir, currentDepth));
      builder.append(treeSubdirs(currentDir, currentDepth, maxDepth));

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

   private String treeDir(File dir, Integer depth) {
      StringBuilder builder = new StringBuilder();

      builder.append(prepender(depth, true));
      if (depth == 0) {
         builder.append(dir.getAbsolutePath());
      } else {
         builder.append(String.format("%s", dir.getName()));
      }
      builder.append("\n");

      return builder.toString();
   }

   private String treeSubfiles(File dir, Integer depth) {
      StringBuilder builder = new StringBuilder();

      for (File file : Objects.requireNonNull(dir.listFiles())) {
         if (!file.isDirectory()) {
            builder.append(prepender(depth, false));
            builder.append(String.format("%-40s%6d", file.getName(), file.length()));
            builder.append("\n");
         }
      }

      return builder.toString();
   }

   private String treeSubdirs(File dir, Integer depth, Integer maxDepth) {
      StringBuilder builder = new StringBuilder();

      for (File file : Objects.requireNonNull(dir.listFiles())) {
         if (file.isDirectory()) {
            if (maxDepth <= depth) {
               builder.append(prepender(depth, true));
               builder.append(String.format("%s%n", file.getName()));
            } else {
               String subtree = dirTreeToString(file, depth + 1, maxDepth); // and here is the recursion
               builder.append(subtree);
            }
         }
      }

      return builder.toString();
   }
}