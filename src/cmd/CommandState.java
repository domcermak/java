package cmd;

import java.io.File;

public class CommandState {
   private String message;
   private File currentDir;

   public CommandState(String message, File currentDir) {
      this.message = message;
      this.currentDir = currentDir;
   }

   public String getMessage() {
      return message;
   }

   public File getCurrentDir() {
      return currentDir;
   }
}
