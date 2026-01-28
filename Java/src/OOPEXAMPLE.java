// 1. IMPORT: Needed to print errors conveniently
import java.util.Random;

class BankAccount {

    // 2. STATIC VARIABLE (Shared Data)
    // 'static' means this belongs to the BANK, not the individual user.
    // Every time we make a new account, we will increase this number.
    private static int totalAccountsCreated = 0;

    // 3. FINAL VARIABLE (Constant)
    // 'final' means once this is set, it can NEVER change. 
    // You can't change an account number after the account is made.
    private final String accountNumber;
    
    private String owner;
    private double balance;

    public BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
        
        // Increment the shared counter
        totalAccountsCreated++;
        
        // Generate a fake random account number
        this.accountNumber = "ACCT-" + (1000 + totalAccountsCreated);
    }

    // 4. THROWING EXCEPTIONS (Error Logic)
    // We tell Java: "This method MIGHT fail."
    public void withdraw(double amount) throws Exception {
        if (amount > balance) {
            // Stop the function immediately and report an error.
            throw new Exception("Insufficient Funds! You only have $" + balance);
        }
        balance = balance - amount;
        System.out.println(owner + " withdrew $" + amount + ". New Balance: $" + balance);
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(owner + " deposited $" + amount + ".");
    }

    // A static method to read the static variable
    public static void printBankStats() {
        System.out.println("--- BANK REPORT: Total Accounts: " + totalAccountsCreated + " ---");
    }
    
    @Override
    public String toString() {
        return "[" + accountNumber + "] " + owner + ": $" + balance;
    }
}

// --- MAIN CLASS ---
public class OOPEXAMPLE {

    public static void main(String[] args) {

        System.out.println("--- BANK SYSTEM STARTING ---");

        // Create two accounts
        BankAccount acct1 = new BankAccount("Bruce Wayne", 5000);
        BankAccount acct2 = new BankAccount("Clark Kent", 100);

        // Check the static counter (It should say 2)
        BankAccount.printBankStats();

        // 5. EXCEPTION HANDLING (try-catch)
        // We are attempting risky code. If it explodes, the 'catch' block saves us.
        try {
            // This transaction is fine
            acct1.withdraw(1000);
            
            // This transaction will FAIL (1000 is more than Clark has)
            System.out.println("Attempting to withdraw from Clark...");
            acct2.withdraw(1000); 

            // This line will NEVER run because the error happens above
            System.out.println("This text will never be printed.");

        } catch (Exception e) {
            // 6. HANDLING THE ERROR
            // Instead of the program crashing with red text, we handle it gracefully.
            System.out.println("TRANSACTION FAILED: " + e.getMessage());
        }

        System.out.println("--- SYSTEM END ---");
    }
}