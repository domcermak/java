package Cermak_ALG2_sem_Company.ui;

public class Options {
   public static String helpText() {
      return "Options:\n"
            + String.format("%-80s %s\n", "list", "Lists whole company")
            + String.format("%-80s %s\n", "add [first name] [surname] [male|female] [birth date (dd.mm.yyyy)] [position]", "Creates new employee")
            + String.format("%-80s %s\n", "delete [id]", "Deletes employee with specified id")
            + String.format("%-80s %s\n", "sort --first_name", "Sort employees by first name")
            + String.format("%-80s %s\n", "sort --age", "Sort employees by age")
            + String.format("%-80s %s\n", "export", "Export current state of company to YAML file")
            + String.format("%-80s %s\n", "exit", "Exit the program")
            + String.format("%-80s %s\n", "help", "Show this help text");
   }
}
