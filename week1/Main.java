import java.util.ArrayList;
import java.util.Scanner;

class Account {
    long accountNumber;
    String name;
    String accountType;
    double balance;

    Account(long accountNumber, String name, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.accountType = accountType;
        this.balance = balance;
    }

    void display() {
        System.out.println("-----------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + name);
        System.out.println("Account Type   : " + accountType);
        System.out.println("Balance        : Rs." + balance);
    }
}

public class Main {

    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Find Account
    static Account findAccount(long accountNumber) {
        for (Account acc : accounts) {
            if (acc.accountNumber == accountNumber) {
                return acc;
            }
        }
        return null;
    }

    // Create Account
    static void createAccount() {
        System.out.print("Enter Account Number: ");
        long accountNumber = sc.nextLong();

        if (findAccount(accountNumber) != null) {
            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Enter Account Holder Name: ");
        String name = sc.next();

        System.out.print("Enter Account Type (Savings/Current): ");
        String accountType = sc.next();

        System.out.print("Enter Initial Deposit: ");
        double balance = sc.nextDouble();

        if (balance < 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        Account acc = new Account(
            accountNumber,
            name,
            accountType,
            balance
        );

        accounts.add(acc);

        System.out.println("Account Created Successfully.");
    }

    // Deposit Money
    static void depositMoney() {
        System.out.print("Enter Account Number: ");
        long accountNumber = sc.nextLong();

        Account acc = findAccount(accountNumber);

        if (acc == null) {
            System.out.println("Account Not Found.");
            return;
        }

        System.out.print("Enter Deposit Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid Amount.");
            return;
        }

        acc.balance += amount;

        System.out.println("Amount Deposited Successfully.");
        System.out.println("Updated Balance: Rs." + acc.balance);
    }

    // Withdraw Money
    static void withdrawMoney() {
        System.out.print("Enter Account Number: ");
        long accountNumber = sc.nextLong();

        Account acc = findAccount(accountNumber);

        if (acc == null) {
            System.out.println("Account Not Found.");
            return;
        }

        System.out.print("Enter Withdrawal Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid Amount.");
            return;
        }

        if (amount > acc.balance) {
            System.out.println("Insufficient Balance.");
            return;
        }

        acc.balance -= amount;

        System.out.println("Amount Withdrawn Successfully.");
        System.out.println("Updated Balance: Rs." + acc.balance);
    }

    // Check Balance
    static void checkBalance() {
        System.out.print("Enter Account Number: ");
        long accountNumber = sc.nextLong();

        Account acc = findAccount(accountNumber);

        if (acc == null) {
            System.out.println("Account Not Found.");
            return;
        }

        System.out.println("Current Balance: Rs." + acc.balance);
    }

    // Transfer Money
    static void transferMoney() {
        System.out.print("Enter Sender Account Number: ");
        long senderNumber = sc.nextLong();

        System.out.print("Enter Receiver Account Number: ");
        long receiverNumber = sc.nextLong();

        Account sender = findAccount(senderNumber);
        Account receiver = findAccount(receiverNumber);

        if (sender == null || receiver == null) {
            System.out.println("One or both accounts not found.");
            return;
        }

        System.out.print("Enter Transfer Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid Amount.");
            return;
        }

        if (amount > sender.balance) {
            System.out.println("Insufficient Balance.");
            return;
        }

        sender.balance -= amount;
        receiver.balance += amount;

        System.out.println("Money Transferred Successfully.");
        System.out.println("Sender Balance: Rs." + sender.balance);
    }

    // View Account Details
    static void viewAccount() {
        System.out.print("Enter Account Number: ");
        long accountNumber = sc.nextLong();

        Account acc = findAccount(accountNumber);

        if (acc == null) {
            System.out.println("Account Not Found.");
            return;
        }

        acc.display();
    }

    // View All Accounts
    static void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No Accounts Found.");
            return;
        }

        System.out.println("\n===== ALL ACCOUNTS =====");

        for (Account acc : accounts) {
            acc.display();
        }
    }

    // Main Menu
    static void menu() {

        while (true) {

            System.out.println("\n===== BANKING MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Money");
            System.out.println("6. View Account Details");
            System.out.println("7. View All Accounts");
            System.out.println("8. Exit");

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    depositMoney();
                    break;

                case 3:
                    withdrawMoney();
                    break;

                case 4:
                    checkBalance();
                    break;

                case 5:
                    transferMoney();
                    break;

                case 6:
                    viewAccount();
                    break;

                case 7:
                    viewAllAccounts();
                    break;

                case 8:
                    System.out.println("Thank You for Using Banking Management System.");
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    public static void main(String[] args) {
        menu();
    }
}