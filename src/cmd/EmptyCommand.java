package cmd;

import java.io.File;

public class EmptyCommand extends Command {
   @Override
   public CommandState execute(File actualDir) {
      return new CommandState("", actualDir);
   }
}
