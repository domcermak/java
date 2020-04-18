package Cermak_Bank.app;

import java.util.ArrayList;

import Cermak_Bank.bank.Client;
import Cermak_Bank.bank.Company;
import Cermak_Bank.bank.Person;

public class Main {
   public static void main(String[] args) {
      ArrayList<Client> clients = new ArrayList<>();
      Client backer = new Person("Pekař");
      Client shoemaker = new Person("Ševcová");
      Client skoda = new Company("Škoda");

      backer.addAccount(1000.);
      backer.addAccount(500.);

      shoemaker.addAccount(1200.);

      skoda.addAccount(120.);

      clients.add(backer);
      clients.add(shoemaker);
      clients.add(skoda);

      for (Client client : clients) {
         System.out.format("%s: %.2f$%n", client.salutation(), client.getTotalBalance());
      }
   }
}
