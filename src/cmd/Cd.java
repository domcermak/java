package cmd;

import java.io.File;

public class Cd extends Command {
   @Override
   public CommandState execute(File actualDir) {
      if (params.length != 1) {
         return new CommandState("cd očekává 1 parametr, dostalo: " + params.length, actualDir);
      }

      if (params[0].equals(".")) {
         return new CommandState("", actualDir);
      } else if (params[0].equals("..")) {
         return new CommandState("", parentDirFrom(actualDir));
      } else {
         File newDir = new File(actualDir.toString(), params[0]);
         if (newDir.exists() && newDir.isDirectory()) {
            return new CommandState("", newDir);
         } else {
            return new CommandState("Složka neexistuje", actualDir);
         }
      }
   }

   private File parentDirFrom(File dir) {
      String[] parts = dir.toString().split("/");

      StringBuilder pathBuilder = new StringBuilder("/");
      for (int i = 0; i < parts.length - 1; i++) {
         pathBuilder.append(parts[i]);
         pathBuilder.append("/");
      }
      pathBuilder = pathBuilder.deleteCharAt(pathBuilder.length() - 1);

      return new File(pathBuilder.toString());
   }
}
