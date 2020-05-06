package cmd;

import java.io.File;

public class Mkdir extends Command {
   @Override
   public CommandState execute(File actualDir) {
      if (params.length != 1) {
         return new CommandState("mkdir očekává 1 parametr, dostalo: " + params.length, actualDir);
      }

      File newDir = new File(actualDir, params[0]);
      if (newDir.mkdir()) {
         return new CommandState(
               String.format("Složka %s byla úspěšně vytvořena", params[0]),
               actualDir
         );
      } else {
         return new CommandState(
               String.format("Složku %s nebylo možné vytvořit", params[0]),
               actualDir
         );
      }
   }
}
