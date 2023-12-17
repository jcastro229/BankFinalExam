package finalExamJC;
import java.util.Scanner;
public class MainApplication {

	public static void main(String[] args) throws Exception {

		Bank only = new Bank();
		
		only.isCurrencyFileLoaded();
		int choice = UIManager();
		only.run(choice);
		while(choice!=8&&choice<8) {
			choice = UIManager();
			only.run(choice);
			}
		}
	public static int UIManager() {
		Scanner input = new Scanner(System.in);
		int choice;
		System.out.println("What would you like to do?\n1 - Open a Checking Account\n2 - Open a Saving Account\n3 - List Accounts"
				+ "\n4 - Account Transactions\n5 - Deposit Funds\n6 - Withdraw Funds\n7 - Close an Account\n8 - Exit");
		
		choice = input.nextInt();
		
		return choice;
	}


}
