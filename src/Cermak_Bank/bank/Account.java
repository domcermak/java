package Cermak_Bank.bank;

public class Account {
   private Double balance;

   public static Account emptyAccount() {
      return new Account(0.);
   }

   public static Account withInitialBalance(Double initialBalance) {
      return new Account(initialBalance);
   }

   public void withdraw(Double amount) throws IllegalArgumentException {
      validatePositiveAmount("withdraw", amount);
      this.balance -= amount;
   }

   public void deposit(Double amount) throws IllegalArgumentException {
      validatePositiveAmount("deposit", amount);
      this.balance += amount;
   }

   public Double getBalance() {
      return this.balance;
   }

   private Account(Double initBalance) {
      this.balance = initBalance;
   }

   private void validatePositiveAmount(String name, Double value) throws IllegalArgumentException {
      if (value <= 0) {
         throw new IllegalArgumentException(name.trim() + " amount must be positive value");
      }
   }
}
