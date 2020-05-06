package cmd;

import java.io.File;

public class CmdEditor implements CmdInterface {
   private boolean isRunning;
   private File actualDir;
   private Command command;

   public CmdEditor() {
      isRunning = true;
      actualDir = new File(System.getProperty("user.dir"));
   }

   @Override
   public boolean isRunning() {
      return isRunning;
   }

   @Override
   public String getActualDir() {
      return actualDir.getAbsolutePath();
   }

   @Override
   public String parseAndExecute(String line) {
      command = Parser.parse(line);
      try {
         CommandState state = command.execute(actualDir);
         actualDir = state.getCurrentDir();
         return state.getMessage();
      } catch (ExitCommandException e) {
         isRunning = false;
         return e.getMessage();
      }
   }
}