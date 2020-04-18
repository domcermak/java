package Cermak_Bank.bank;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public abstract class Client {
   protected String name;
   protected ArrayList<Account> accounts;
   protected Double totalBalance;

   public Client(String name) {
      this.name = name;
      this.accounts = new ArrayList<>();
      this.totalBalance = 0.;
   }

   public Client(String name, Account[] accounts) {
      this(name);

      for (Account account : accounts) {
         addAccount(account);
      }
   }

   public Double getTotalBalance() {
      return this.totalBalance;
   }

   public abstract String salutation();

   public void addAccount(Double amount) {
      Account account = Account.withInitialBalance(amount);
      addAccount(account);
   }

   public void addAccount(Account account) {
      this.accounts.add(account);
      this.totalBalance += account.getBalance();
   }
}
