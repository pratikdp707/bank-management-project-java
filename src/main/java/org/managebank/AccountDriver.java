package org.managebank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class AccountDriver {

    private static final int asciiOpeningDoubleQuote = 8220;
    private static final int asciiClosingDoubleQuote = 8221;
    //hashmap to store distinct object for every account
    private HashMap<Integer, AccountOps> accountNumbersMap;

    //static variable to generate account numbers starting from 1001
    private static int accountNumberCount = 1001;

    //default constructor
    public AccountDriver() {
        accountNumbersMap = new HashMap<>();
    }

    //function to check if account number is valid
    public boolean validAccountNumberCheck(int accountNumber){
        return accountNumbersMap.containsKey(accountNumber);
    }

    //function which accepts input from input file and processes the command accordingly
    /*
    * There are five commands:
    *   1. CREATE - takes account holder name as input, creates a account and returns account details
    *   2. DEPOSIT - takes account number and amount to be deposied as input and return balance after performing operations
    *   3. WITHDRAW - takes account number and amount to be debited as input and return balance after performing operations
    *   4. BALANCE - takes account number as input and returns the balance
    *   5. TRANSFER - takes source account number, target account number, amount to be transfered and returns the transaction success or failure after performing operation
    * */
    public void acceptInput(String str){
        StringTokenizer input = new StringTokenizer(str, " ");

        List<String> commands = new ArrayList<>();

        while(input.hasMoreTokens()){
            commands.add(input.nextToken());
        }

        System.out.print("Input Command : ");
        for(String command : commands){
            System.out.print(command + " ");
        }

        System.out.println();

        int accountNumber, sourceAccountNumber, targetAccountNumber;
        AccountOps accountOps, sourceAccountOps, targetAccountOps;

        switch(commands.get(0).toUpperCase()){
            case "CREATE":
                //checks if the leading characters in the name is '"'
                String fname = ((int)commands.get(1).charAt(0) == asciiOpeningDoubleQuote) ? commands.get(1).substring(1) : commands.get(1);
                String mname = "";
                String lname;
                if(commands.size() == 4){
                    mname = commands.get(2);
                    lname = ((int)commands.get(3).charAt(commands.get(3).length() - 1) == asciiClosingDoubleQuote) ? commands.get(3).substring(0, commands.get(3).length() - 1) : commands.get(3);
                }
                else{
                    lname = ((int)commands.get(2).charAt(commands.get(2).length() - 1) == asciiClosingDoubleQuote) ? commands.get(2).substring(0, commands.get(2).length() - 1) : commands.get(2);
                }

                //add account number and its object in the hashmap
                accountNumbersMap.put(accountNumberCount, new AccountOps(accountNumberCount, fname + " " + mname + " " + lname));

                //displays account details
                accountNumbersMap.get(accountNumberCount).displayAccountDetails();
                accountNumberCount++;
                break;

            case "DEPOSIT":
                //checks whether the account number is valid or not
                accountNumber = Integer.parseInt(commands.get(1));
                if(validAccountNumberCheck(accountNumber))
                    accountOps = accountNumbersMap.get(accountNumber);
                else {
                    System.out.println("Account number not available with us... Please enter correct account number");
                    break;
                }

                //if account number is valid then it performs deposit operation
                if(accountOps.getDepositCount() < 3) {
                    if (accountOps.validateDeposit(Integer.parseInt(commands.get(2)))) {
                        accountOps.deposit(Integer.parseInt(commands.get(2)));
                        accountOps.displayBalance();
                    }
                }
                else System.out.println("Only 3 deposits are allowed in a day");

                break;

            case "WITHDRAW":
                //checks whether the account number is valid or not
                accountNumber = Integer.parseInt(commands.get(1));
                if(validAccountNumberCheck(accountNumber))
                    accountOps = accountNumbersMap.get(accountNumber);
                else {
                    System.out.println("Account number not available with us... Please enter correct account number");
                    break;
                }

                //if account number is valid, then perform withdraw operation
                if(accountOps.getWithdrawCount() < 3) {
                    if (accountOps.validateWithdraw(Integer.parseInt(commands.get(2)))) {
                        accountOps.withdraw(Integer.parseInt(commands.get(2)));
                        accountOps.displayBalance();
                    }
                }
                else System.out.println("Only 3 withdrawals are allowed in a day");
                break;

            case "BALANCE":
                //checks whether the account number is valid or not
                accountNumber = Integer.parseInt(commands.get(1));
                if(validAccountNumberCheck(accountNumber))
                    accountOps = accountNumbersMap.get(accountNumber);
                else {
                    System.out.println("Account number not available with us... Please enter correct account number");
                    break;
                }

                //calls display balance function
                accountOps.displayBalance();
                break;

            case "TRANSFER":
                //checks if both the source and target account numbers are valid or not
                sourceAccountNumber = Integer.parseInt(commands.get(1));
                targetAccountNumber = Integer.parseInt(commands.get(2));
                if(!validAccountNumberCheck(sourceAccountNumber)){
                    System.out.println("Account number " + sourceAccountNumber + " not available with us... Please enter correct account number");
                    break;
                }

                if(!validAccountNumberCheck(targetAccountNumber)){
                    System.out.println("Account number " + targetAccountNumber + " not available with us... Please enter correct account number");
                    break;
                }

                sourceAccountOps = accountNumbersMap.get(sourceAccountNumber);
                targetAccountOps = accountNumbersMap.get(targetAccountNumber);

                //if account numbers are valid, perform transfer operation
                double amount = Integer.parseInt(commands.get(3));
                if(sourceAccountOps.getWithdrawCount() < 3 && targetAccountOps.getDepositCount() < 3) {
                    if (sourceAccountOps.validateWithdraw(amount) && targetAccountOps.validateDeposit(amount)){
                        sourceAccountOps.withdraw(amount);
                        targetAccountOps.deposit(amount);
                        System.out.println("Successful");
                    }
                }
                else if(sourceAccountOps.getWithdrawCount() >= 3)
                    System.out.println("Only 3 withdrawals are allowed in a day for " + sourceAccountNumber);
                else
                    System.out.println("Only 3 deposits are allowed in a day for " + targetAccountNumber);
                break;

            default:
                System.out.println("input command doesn't match");
        }
        System.out.println();
    }
}
