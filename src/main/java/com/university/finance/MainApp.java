package com.university.finance;

import com.university.finance.model.Account;
import com.university.finance.model.User;
import com.university.finance.pattern.factory.AccountFactory;
import com.university.finance.pattern.factory.UserFactory;
import com.university.finance.pattern.observer.AuditLogger;
import com.university.finance.pattern.observer.NotificationService;
import com.university.finance.service.BankingService;
import com.university.finance.service.TransactionService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        // Services
        TransactionService transactionService = new TransactionService();
        BankingService bankingService = new BankingService(transactionService);

        // Observers
        transactionService.addObserver(new AuditLogger());
        transactionService.addObserver(new NotificationService());

        // Stockage dynamique des utilisateurs et comptes
        Map<String, User> users = new HashMap<>();
        Map<String, Account> accounts = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("=== Systeme Bancaire Propre ===");
            System.out.println("1. Afficher solde");
            System.out.println("2. Deposer argent");
            System.out.println("3. Retirer argent");
            System.out.println("4. Transfert");
            System.out.println("5. Historique");
            System.out.println("6. Ajouter utilisateur");
            System.out.println("0. Quitter");

            System.out.print("Choix: ");
            int choice = scanner.nextInt();

            try {

                switch (choice) {

                    case 1: {
                        System.out.print("Nom utilisateur: ");
                        String username = scanner.next();

                        Account acc = accounts.get(username);
                        if (acc != null) {
                            System.out.println("Solde: " + acc.getBalance());
                        } else {
                            System.out.println("Utilisateur non trouve");
                        }
                        break;
                    }

                    case 2: {
                        System.out.print("Utilisateur: ");
                        String username = scanner.next();
                        System.out.print("Montant: ");
                        double amount = scanner.nextDouble();

                        Account acc = accounts.get(username);
                        if (acc != null) {
                            bankingService.deposit(acc, amount);
                            System.out.println("Depot reussi");
                        } else {
                            System.out.println("Utilisateur non trouve");
                        }
                        break;
                    }

                    case 3: {
                        System.out.print("Utilisateur: ");
                        String username = scanner.next();
                        System.out.print("Montant: ");
                        double amount = scanner.nextDouble();

                        Account acc = accounts.get(username);
                        if (acc != null) {
                            bankingService.withdraw(acc, amount);
                        } else {
                            System.out.println("Utilisateur non trouve");
                        }
                        break;
                    }

                    case 4: {
                        System.out.print("De l utilisateur: ");
                        String fromUser = scanner.next();
                        System.out.print("Vers utilisateur: ");
                        String toUser = scanner.next();
                        System.out.print("Montant: ");
                        double amount = scanner.nextDouble();

                        Account fromAcc = accounts.get(fromUser);
                        Account toAcc = accounts.get(toUser);

                        if (fromAcc != null && toAcc != null) {
                            bankingService.transfer(fromAcc, toAcc, amount);
                        } else {
                            System.out.println("Utilisateur invalide");
                        }
                        break;
                    }

                    case 5: {
                        System.out.print("Utilisateur: ");
                        String username = scanner.next();

                        System.out.println("=== Historique ===");
                        transactionService.getHistory().stream()
                                .filter(t ->
                                        (t.getAccountFrom() != null &&
                                                t.getAccountFrom().getOwner().getName().equals(username))
                                                ||
                                                (t.getAccountTo() != null &&
                                                        t.getAccountTo().getOwner().getName().equals(username))
                                )
                                .forEach(System.out::println);
                        break;
                    }

                    case 6: {
                        scanner.nextLine(); // clear buffer

                        System.out.print("Nouvel utilisateur: ");
                        String name = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Telephone: ");
                        String phone = scanner.nextLine();

                        System.out.print("Depot initial: ");
                        double initialBalance = scanner.nextDouble();

                        User user = UserFactory.createUser(name, email, phone);
                        Account account = AccountFactory.createAccount(user, initialBalance);

                        users.put(name, user);
                        accounts.put(name, account);

                        System.out.println("Utilisateur cree avec succes");
                        break;
                    }

                    case 0:
                        running = false;
                        break;

                    default:
                        System.out.println("Choix invalide");
                }

            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Application terminee.");
    }
}
