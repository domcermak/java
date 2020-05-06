package cmd;

import java.io.File;

public class Mv extends Command {
   @Override
   public CommandState execute(File actualDir) {
      if (params.length != 2) {
         return new CommandState("mv očekává 2 parametry, dostalo: " + params.length, actualDir);
      }

      File currentName = new File(actualDir, params[0]);
      File newName = new File(actualDir, params[1]);

      if (!currentName.exists()) {
         return new CommandState(params[0] + " does not exist", actualDir);
      }
      if (newName.exists()) {
         return new CommandState(params[1] + " already exists", actualDir);
      }
      if (currentName.renameTo(newName)) {
         return new CommandState("", actualDir);
      } else {
         return new CommandState("Přejmenování se nezdařilo", actualDir);
      }
   }
}
