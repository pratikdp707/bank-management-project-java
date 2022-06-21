package org.managebank;

public class AccountOps extends Account{

    private static final double MIN_DEPOSIT_AMOUNT = 500;
    private static final double MAX_DEPOSIT_AMOUNT = 50000;
    private static final double MIN_WITHDRAWAL_AMOUNT = 1000;
    private static final double MAX_WITHDRAWAL_AMOUNT = 25000;
    private static final double MIN_ACCOUNT_BALANCE = 0;
    private static final double MAX_ACCOUNT_BALANCE = 100000;

    //default constructor
    public AccountOps() {
        super();
    }

    //to keep track of deposit count in the account
    private int depositCount;

    //to keep track of withdraw count in the account
    private int withdrawCount;

    //getter method for deposit count
    public int getDepositCount() {
        return depositCount;
    }

    //getter method for withdraw count
    public int getWithdrawCount() {
        return withdrawCount;
    }

    //parameterized constructor
    public AccountOps(int accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName);
        depositCount = 0;
        withdrawCount = 0;
    }

    /*
    function to perform validations on deposit operation
     * applies below listed validations:
     *   1. checks if the balance after depositing the input amount exceeds Rs. 1,00,000
     *   2. checks if the amount to be deposited lies in the range of minimum and maximum deposit amount
     * */
    @Override
    public boolean validateDeposit(double amount){
        double balance = getBalance();
        if(balance + amount <= MAX_ACCOUNT_BALANCE){
            if(amount >= MIN_DEPOSIT_AMOUNT && amount <= MAX_DEPOSIT_AMOUNT){
                return true;
            }
            else if(amount < MIN_DEPOSIT_AMOUNT){
                new Exception("Minimum Deposit amount is Rs. 500 per trasaction for " + getAccountNumber());
                return false;
            }
            else {
                System.out.println("Maximum Deposit amount is Rs. 50,000 per transaction for " + getAccountNumber());
                return false;
            }
        }
        else {
            System.out.println("Account balance cannot exceed Rs. 1,00,000 for " + getAccountNumber());
            return false;
        }
    }

    /*
function to perform validations on withdraw operation
 * applies below listed validations:
 *   1. checks if the balance after withdrawing the input amount is less than zero
 *   2. checks if the amount to be withdrawn lies in the range of minimum and maximum withdraw amount
 * */
    @Override
    public boolean validateWithdraw(double amount){
        double balance = getBalance();
        if (balance - amount >= MIN_ACCOUNT_BALANCE) {
            if (amount >= MIN_WITHDRAWAL_AMOUNT && amount <= MAX_WITHDRAWAL_AMOUNT) {
                return true;
            }
            else if (amount < MIN_WITHDRAWAL_AMOUNT) {
                System.out.println("Minimum withdrawal amount is Rs. 1000 per transaction for " + getAccountNumber());
                return false;
            }
            else {
                System.out.println("Maximum withdrawal amount is Rs. 25,000 per transaction for " + getAccountNumber());
                return false;
            }
        } else {
            System.out.println("Insufficient Balance in account " + getAccountNumber());
            return false;
        }
    }

    //function to perform deposit operations
    @Override
    public void deposit(double amount) {
        double balance = getBalance();
        balance += amount;
        setBalance(balance);
        depositCount++;
    }

    //function to perform withdraw operation
    @Override
    public void withdraw(double amount) {
        double balance = getBalance();
        balance -= amount;
        setBalance(balance);
        withdrawCount++;
    }

    //function to display the account details
    public void displayAccountDetails(){
        System.out.println("Account Number : " + getAccountNumber());
        System.out.println("Account Holder Name : " + getAccountHolderName());
        System.out.println("Account Balance : " + getBalance());
    }

    //function to get account balance
    public void displayBalance(){
        System.out.println("Account Balance for " + getAccountNumber() + " is : " + getBalance());
    }
}
