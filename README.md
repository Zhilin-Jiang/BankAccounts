# CISC 1115
## Homework – Topic 7 – Bank Accounts

You have been hired as a programmer by a major bank. Your first project is a small banking transaction system. Each account
consists of a number and a balance. The user of the program (the teller) can create a new account, as well as perform deposits,
withdrawals, and balance inquiries.

Initially, the account information of existing customers is to be read into a pair of parallel arrays--one for account numbers, the
other for balances. The bank can handle up to MAX_NUM accounts. Use the following method to read in the data values:
public static int readAccts(int[] acctNum, double[] balance)

This method fills up the account number and balance arrays by reading from an input file until EOF is reached, and counting
how many accounts are read in. It returns the actual number of accounts read in (later referred to as numAccts).
After initialization, the main program prints the initial database of accounts and balances. Use method printAccts()
described below.
The program then allows the user to select from the following menu of transactions:
Select one of the following:
 W - Withdrawal
 D - Deposit
 N - New account
 B - Balance
 Q – Quit
 X – Delete Account – Extra Credit
Use the following method to produce the menu:
public static void menu()

This method only displays the menu. The main program then prompts the user for a selection. You should verify that the user
has typed in a valid selection (otherwise print out an error message and repeat the prompt).
Once the user has entered a selection, one of the following methods should be called to perform the specific transaction. At the
end, before the user quits, the program prints the final contents of the account and balance arrays.
public static int findAcct(int[] acctNum, int numAccts, int account);

This method returns the index of account in the acctNum array if the account exists, and -1 if it doesn't. It is called by all the
remaining methods.
public static void withdrawal(int[] acctNum, double[] balance, int numAccts);

This method prompts the user for the account number. If the account does not exist, it prints an error message. Otherwise, it asks
the user for the amount of the withdrawal. If the account does not contain sufficient funds, it prints an error message and does not
perform the transaction.
pubic static void deposit(int[] acctNum, double[] balance, int num_accts);

This method prompts the user for the account number. If the account does not exist, it prints an error message. Otherwise, it asks
the user for the amount of the deposit.
public static int newAcct(int[] acctNum, double[] balance, int numAccts);

This method prompts the user for a new account number. If the account already exists, it prints an error message. Otherwise, it
adds the account to the acctNum array with an initial balance of 0. It returns the new number of accounts.
public static void balance(int[] acctNum, double[] balance, int numAccts);

This method prompts the user for an account number. If the account does not exist, it prints an error message. Otherwise, it prints
the account balance.
 public static void printAccts(int[] acctNum, double[] balance, int numAccts);

This method prints a 2-column table (with column headings) of all customer information--account numbers and balances.
