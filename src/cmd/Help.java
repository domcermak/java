package cmd;

import java.io.File;

public class Help extends Command {
   @Override
   public CommandState execute(File actualDir) {
      String help = "HELP\n"
            + String.format("%-40s %s\n", "dir", "Display list of files and folders") // done
            + String.format("%-40s %s\n", "dir [-o]", "Display of files and folders with specific extension")
            + String.format("%-40s %s\n", "dir [-e] [file extension]", "Display an ordered list of files and folders")
            + String.format("%-40s %s\n", "dir [-s] [size]", "Display list of files and folders bigger than specified size")
            + String.format("%-40s %s\n", "cd [folder name]", "Change directory - move to specific folder")
            + String.format("%-40s %s\n", "cd ..", "Change directory - move to the higher folder")
            + String.format("%-40s %s\n", "mkdir [folder name]", "Create new folder")
            + String.format("%-40s %s\n", "mv [name from] [name to]", "Rename folder of file")
            + String.format("%-40s %s\n", "exit", "Quit the program")
            + String.format("%-40s %s\n", "help", "Display help");  // done
      return new CommandState(help, actualDir);
   }
}