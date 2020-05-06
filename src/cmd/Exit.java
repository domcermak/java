package cmd;

import java.io.File;

public class Exit extends Command {
   @Override
   public CommandState execute(File actualDir) throws ExitCommandException {
      throw new ExitCommandException("Vypínám program...");
   }
}
