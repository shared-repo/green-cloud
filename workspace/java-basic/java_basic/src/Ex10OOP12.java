import java.util.Date;

public class Ex10OOP12 {

	public static void main(String[] args) {
		
		BankAccount[] accounts = new BankAccount[10];
		
		for (int i = 0; i < accounts.length; i++) {
			if (i % 3 == 0) {
				accounts[i] = new SavingsAccount();
			} else if (i % 3 == 1) {
				accounts[i] = new FixedAccount();
			} else {
				accounts[i] = new MinusAccount();
			}
		}
		
		for (int i = 0; i < accounts.length; i++) {
			accounts[i].deposit(1000);
			accounts[i].withdraw(500);
		}	
	}
}

abstract class BankAccount {
	private int accountNo;
	private String owner;
	private double balanace;
	
	abstract public void deposit(double amount); 
	abstract public void withdraw(double amount);	
}

class SavingsAccount extends BankAccount {
	
	private boolean isVIP;
	
	@Override
	public void deposit(double amount) {
	}
	@Override
	public void withdraw(double amount) {
	}
}

class FixedAccount extends BankAccount {

	private Date dueDate;
	private double interestRate;
	private double monthlyDepositAmount;
	
	@Override
	public void deposit(double amount) {
	}
	@Override
	public void withdraw(double amount) {
	}
	
}

class MinusAccount extends BankAccount {
	
	private Date dueDate;
	private double interestRate;
	private double limit;

	@Override
	public void deposit(double amount) {
	}
	@Override
	public void withdraw(double amount) {
	}
	
}







