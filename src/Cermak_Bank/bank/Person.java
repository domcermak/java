package Cermak_Bank.bank;

public class Person extends Client {
   public Person(String name) {
      super(name);
   }

   public Person(String name, Account[] accounts) {
      super(name, accounts);
   }

   public String salutation() {
      return type() + " " + this.name;
   }

   private String type() {
      if (this.name.endsWith("ova") || this.name.endsWith("ová")) {
         return "paní";
      } else {
         return "pan";
      }
   }
}
