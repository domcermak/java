package cmd;

import java.util.Arrays;

public class Parser {
   public static Command parse(String line) { //dir  -e .java
      String[] p = line.split(" +"); //p[0] dir; p[1] -e p[2] .java
      if (p[0].isEmpty()) {
         return new EmptyCommand();
      }

      char first = Character.toUpperCase(p[0].charAt(0)); //D
      String name =  Command.COMMAND_PACKAGE + "." + first + p[0].substring(1); //cmd.Dir


      if (p.length >= 1) {
         p = Arrays.copyOfRange(p, 1, p.length);
      }

      try{
         Class c = Class.forName(name);
         Command command = (Command) c.newInstance();
         command.setParams(p);
         return command;
      } catch (Exception e){
         throw new RuntimeException("Nepodarilo se prikaz naparsovat.");
      }
   }
}