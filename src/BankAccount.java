import java.text.DecimalFormat;
public class BankAccount {
    private double balance;
    private AccountOwner accountOwner;
    private long accountNumber;
    public BankAccount(AccountOwner accountOwner, double balance) {
        this.balance = balance;
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
    }
    public AccountOwner getAccountOwner() {
        return accountOwner;
    }
    public long getAccountNumber() {
        return this.accountNumber;
    }
    public String getMaskedAccountNumber() {
        long num = accountNumber;
        String accountString = String.valueOf(num);
        return "*".repeat(Math.max(0, accountString.length() - 4)) +
                accountString.substring(accountString.length() - 4);
    }
    public int deposit (double deposit) {
        if (deposit <= 0) {
            return 2;
        }
        this.balance += deposit;
        return 1;
    }
    public int withdraw (double withdrawal) {
        if (withdrawal < 0) {
            return 2;
        }
        else if (withdrawal > this.balance) {
            return 3;
        }
        this.balance -= withdrawal;
        return 1;
    }
    public int transfer(long destination, double amount) {
        BankAccount targetAccount = ATM.lookup(destination);
        if (targetAccount == null) {
            return 4;
        }
        else if (amount < 0) {
            return 2;
        }
        else if (amount > this.balance) {
            return 3;
        }
        this.withdraw(amount);
        targetAccount.deposit(amount);
        return 1;
    }
    public String getFormattedBalance() {
        DecimalFormat cashFormat = new DecimalFormat("#,###.00");
        return "$" + cashFormat.format(this.balance);
    }
    public long setAccountNumber() {
        long accountNumber;
        while (true) {
            accountNumber = (long) (Math.random() * Math.pow(10, 9));
            BankAccount valid = ATM.lookup(accountNumber);
            if (valid == null) {
                return accountNumber;
            }
        }
    }
}
