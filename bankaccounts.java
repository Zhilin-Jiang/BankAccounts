import java.io.*;
import java.util.Scanner;
public class bankaccounts {
	public static void main(String[] args)	throws IOException //added throw clause
	{
		final int MAX_NUM = 10;
		int[] acctNum = new int[MAX_NUM];
		double[] balance = new double[MAX_NUM];
		char choice;
		int numAccts;
		Scanner inputFile = new Scanner(System.in);
		PrintWriter outputFile = new PrintWriter("myoutput.txt");
		numAccts = readAccts(acctNum, balance, MAX_NUM);
		printAccts(acctNum, balance, numAccts);
		do {
			menu();
			System.out.print("Enter a choice:");
			choice = inputFile.next().charAt(0);
			switch (choice) {
			case 'Q':
			case 'q':
				printAccts(acctNum, balance, numAccts);
				break;
			case 'W':
			case 'w':
				withdrawal(acctNum, balance, numAccts, outputFile, inputFile);
				break;
			case 'D':
			case 'd':
				deposit(acctNum, balance, numAccts, outputFile, inputFile);
				break;
			case 'N':
			case 'n':
				newAcct(acctNum, balance, numAccts, outputFile, inputFile);
				break;
			case 'B':
			case 'b':
				balance(acctNum, balance, numAccts, outputFile, inputFile);
				break;
			default: 
				System.out.println("An error has occurred\n");
				menu();
				System.out.print("Enter a choice:");
				choice = inputFile.next().charAt(0);
				break;
			}
		}while (choice != 'Q' && choice != 'q');
		inputFile.close();
		outputFile.close();
	}
   /*Input:
    *acctNum:account numbers
    *balance:account balances
    *maxAccts:max of account you can have
    * Process:
    *reads data of account numbers and balance
    * Output:
    *prints the actual number of accounts read in
    */
	public static int readAccts(int[] acctNum, double[] balance,
			int maxAccts)throws IOException
	{
		int count = 0;
		File myinput = new File("C:/Users/tohsa/eclipse-workspace/BankAccounts/myinput.txt");
		Scanner inputFile = new Scanner(myinput);
		while (inputFile.hasNext() && count < maxAccts) 
		{
			acctNum[count] = inputFile.nextInt();
			balance[count] = inputFile.nextDouble();
			count++;
		}
		inputFile.close();
		return count; 
	}
    /*Input:
     *numAccts:number of account
     * Process:
     *makes a table for account numbers and balances
     * Output:
     *prints a table of customer account number and balance
     */
	public static void printAccts(int[] acctNum, double[] balance, int numAccts)
	{
		System.out.println("Initial database of accounts and balances");
		System.out.println("Account \tBalance");
		for (int count = 0; count < numAccts; count++)
		{
		   System.out.printf("%d \t\t$%7.2f", acctNum[count], balance[count]);
		   System.out.println();
		}
	}
    /* Process:
     * display a menu
     * Output:
     * prints the menu
     */
	public static void menu() {
		System.out.println("\nSelect one of the following:\n");
		System.out.println("W - Withdrawal");
		System.out.println("D - Deposit");
		System.out.println("N - New account");
		System.out.println("B - Balance");
		System.out.println("Q - Quit\n");
	}
   /*Process:
    *finds the account number whether the account exists, or not
    *Output:
    *prints the index of account in acctNum array if the account exists, 
    *and -1 if it doesn’t.
    */
	public static int findAcct(int[] acctNum, int numAccts, int account)
	{
		for (int count = 0; count < numAccts; count++) {
			if (acctNum[count] == account) {
				return count;
			}
		}
		return -1;
	}
   /*Process:
    *finds the right account number and its info,
    *and prints an error if the account is invalid.
    *Output:
    *prints the current account balance
    */
	public static void balance(int[] acctNum, double[] balance, int numAccts,
			PrintWriter outputFile, Scanner inputFile)
	{
		int account;
		int count;
		System.out.print("Enter an account number:");
		account = inputFile.nextInt();
		count = findAcct(acctNum, numAccts, account);
		if (count == -1) {
			outputFile.println("Transaction Type: Balance");
			outputFile.println("Error,the account number:" + account +
					", does not exist");
		} else { 
			outputFile.println("Transaction Type: Balance");
			outputFile.println("Account number:" + account);
			outputFile.printf("Current Balance:$%.2f", balance[count]);
			outputFile.println();
		}
		outputFile.println();
	}
   /*Process:
    *finds the account if it exists, and the amount of money being deposited
    * Output:
    *prints the amount of money being deposited, 
    *or an error message due to invalid account
    */
	public static void deposit(int[] acctNum, double[] balance, int numAccts, 
			PrintWriter outputFile, Scanner inputFile) 
	{
		int account;
		int count; 
		double amounttodeposit;
		System.out.print("Enter an account number:");
		account = inputFile.nextInt();
		count = findAcct(acctNum, numAccts , account);
		if (count == -1) {
			outputFile.println("Transaction Type: Deposit");
			outputFile.println("Error,account:" + account + " does not exist");
			outputFile.println();
		} else {
			System.out.print("Enter the amount to deposit:");
			amounttodeposit = inputFile.nextDouble();
			if (amounttodeposit < 0.00 || amounttodeposit > 10000.00) {
				outputFile.println("Transaction Type: Deposit");
				outputFile.println("Account number:" + account);
				outputFile.println("Amount to Deposit:$" + amounttodeposit);
				outputFile.println("Error,can not deposit "
						+ "this amount of money");
				outputFile.println();
			} else {
			outputFile.println("Transaction Type: Deposit");
			outputFile.println("Account number:" + account);
			outputFile.printf("Current Balance:$%.2f", balance[count]);
			outputFile.println();
			outputFile.println("Amount to Deposit:$" + amounttodeposit);
			balance[count]+=amounttodeposit;
			outputFile.printf("New Balance:$%.2f", balance[count]);
			outputFile.println("\n");
		    }
	  }	
}
   /*Process:
    *finds account number if it exists, the amount of money being withdrawal,
    *and if there is sufficient funds
    * Output:
    *prints the amount of money withdrawal, or an error message saying non sufficient funds
    */
	public static void withdrawal(int[] acctNum, double[] balance, int numAccts,
			PrintWriter outputFile,Scanner inputFile) 
	{
		int account;
		int count;
		double amounttowithdrawal = 0;
		System.out.print("Enter an account number:");
		account = inputFile.nextInt();
		count = findAcct(acctNum, numAccts , account);
		if (count == -1) {
			outputFile.println("Transaction Type: Withdrawal");
			outputFile.println("Error,account:" + account + " does not exist");
			outputFile.println();
		} else {
			System.out.print("Enter the amount to withdraw:");
			amounttowithdrawal = inputFile.nextDouble();
		
		if (amounttowithdrawal > balance[count] || amounttowithdrawal < 0) {
			outputFile.println("Transaction Type: Withdrawal");
			outputFile.println("Account number:" + account);
			outputFile.printf("Current Balance:$%.2f", balance[count]);
			outputFile.println();
			outputFile.println("Amount to Withdraw:" + amounttowithdrawal);
			outputFile.println("Error: Insufficient Funds Available\n\n");
		}
		else {
			outputFile.println("Transaction Type: Withdrawal");
			outputFile.println("Account number:" + account);
			outputFile.printf("Current Balance:$%.2f", balance[count]);
			outputFile.println();
			outputFile.println("Amount to withdraw:$" + amounttowithdrawal);
			balance[count]-=amounttowithdrawal;
			outputFile.printf("New Balance:$%.2f", balance[count]);
			outputFile.println("\n");
		}
		}
	}
   /*Process:
    *prompts id the user entered and checks to see if the account is already taken. 
    *If it is, prints an error message, and if not,
    * adds the account to acctNum array
    *Output:
    *prints a new account with an initial balance of 0
    */
	public static int newAcct(int[] acctNum, double[] balance, int numAccts, 
			PrintWriter outputFile, Scanner inputFile) 
	{
		int newaccount;
		int count = 0; 
		System.out.print("Enter a new account number:");
		newaccount = inputFile.nextInt();
		if (count == newaccount) {
			outputFile.println("Transaction Type: New Account");
			outputFile.println("Error, the account you entered already exist");
			return numAccts;
		}
		if (newaccount < 0 || newaccount > 9999) {
			outputFile.println("Transaction Type: New Account");
			outputFile.println("Error, the account number you"
					+ " entered does not exist");
			return numAccts;
		}
			acctNum[count] = newaccount;
			balance[count] = 0.00;
			outputFile.println("Transaction Type: New Account");
			outputFile.println("account:" + newaccount);
			return ++numAccts;
	}

}
