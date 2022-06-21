package org.managebank;

//Base - Abstract Class
public abstract class Account{

    //account number
    private int accountNumber;

    //account holder name
    private String accountHolderName;

    //balance
    private  double balance;

    public Account() {
    }

    public Account(int accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //Abstract methods

    //function to validate deposit amount
    public abstract boolean validateDeposit(double amount);

    //function to validate withdraw amount
    public abstract boolean validateWithdraw(double amount);

    //function to deposit amount in the account
    //accepts amount to be credited as the parameter
    public abstract void deposit(double amount);

    //function to withdraw amount from the account
    //accepts amount to be debited as the parameter
    public abstract void withdraw(double amount);

}
