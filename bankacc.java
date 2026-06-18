import java.util.Scanner;
class BankAccount {
    private String accountNumber;
    private String owner;
    private double balance;

    public BankAccount(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAccountNumber() { 
        return accountNumber; 
    }
    public String getOwner() { 
        return owner; 
    }
    public double getBalance() { 
        return balance; 
    }

    public void setAccountNumber(String accountNumber) { 
        this.accountNumber = accountNumber; 
    }
    public void setOwner(String owner) { 
        this.owner = owner; 
    }
    public void setBalance(double balance) { 
        this.balance = balance; 
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Enter a positive amount to deposit.");
            return;
        }
        setBalance(getBalance() + amount);
        System.out.println("Deposited: " + String.format("%.2f", amount));
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Enter a positive amount to withdraw.");
            return false;
        }
        if (amount > getBalance()) {
            System.out.println("Insufficient funds.");
            return false;
        }
        setBalance(getBalance() - amount);
        System.out.println("Withdrew: " + String.format("%.2f", amount));
        return true;
    }

    public String toString() {
        return "Account: " + getAccountNumber() + ", Owner: " + getOwner() + ", Balance: " + String.format("%.2f", getBalance());
    }
}

public class bankacc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Simple Bank Account (getters/setters demo)");
        System.out.print("Owner name: ");
        String owner = sc.nextLine().trim();
        System.out.print("Account number: ");
        String accNum = sc.nextLine().trim();

        BankAccount account = new BankAccount(accNum, owner, 0.0);

        while (true) {
            System.out.println("\nChoose: 1) Deposit  2) Withdraw  3) Balance  4) Info  0) Exit");
            System.out.print("> ");
            String choice = sc.nextLine().trim();
            if (choice.equals("1")) {
                System.out.print("Amount to deposit: ");
                double amt = readDouble(sc);
                account.deposit(amt);
            } else if (choice.equals("2")) {
                System.out.print("Amount to withdraw: ");
                double amt = readDouble(sc);
                account.withdraw(amt);
            } else if (choice.equals("3")) {
                System.out.println("Balance: " + String.format("%.2f", account.getBalance()));
            } else if (choice.equals("4")) {
                System.out.println(account.toString());
            } else if (choice.equals("0")) {
                System.out.println("Goodbye.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    private static double readDouble(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
