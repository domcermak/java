package Cermak_Bank.bank;

public class Company extends Client {
   public Company(String name) {
      super(name);
   }

   public Company(String name, Account[] accounts) {
      super(name, accounts);
   }

   public String salutation() {
      return "firma " + this.name;
   }
}
