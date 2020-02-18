package com.klimavicius.ui;

import java.util.Scanner;

import com.klimavicius.dao.AccountDaoImpl;
import com.klimavicius.dao.UserDaoImpl;
import com.klimavicius.model.Account;
import com.klimavicius.model.User;

import org.mindrot.jbcrypt.BCrypt;

public class UserUI {
	User userLogin;
	Scanner input = new Scanner(System.in);

	public void startUI() {

		char intInput = mainUI();

		do {
			switch (intInput) {
			case '1':
				loginUI();
				break;
			case '2':
				SignUpUI();
				break;
			case '3':
				exitUI();
				break;
			default:
				mainUI();
				break;
			}
			intInput = mainUI();
		} while (intInput != '3');
		exitUI();
	}

	public char mainUI() {

		char menuChoice;
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println(" --------------------");
		System.out.println("|Welcome to BankForMe|");
		System.out.println(" --------------------");
		System.out.println("What can we do for you today?");
		System.out.println("1.Login");
		System.out.println("2.Sign up");
		System.out.println("3.Exit");
		System.out.println(" -------VK 2020------");
		System.out.println("Option: ");

		menuChoice = input.next().charAt(0);

		return menuChoice;
	}

	public void loginUI() {

		UserDaoImpl loggedUser = new UserDaoImpl();

		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println(" --------------------");
		System.out.println("|Welcome to BankForMe|");
		System.out.println(" --------------------");
		System.out.println(" ------- Login ------");
		System.out.println("-Username:           ");
		String username = input.next().toLowerCase();
		System.out.println("-Password:           ");
		String password = input.next().toString();
		password = password.trim();
		System.out.println(" --------------------");
		userLogin = loggedUser.getUserByUsername(username);
		if (userLogin == null) {
			System.out.println("User doesn't exists!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			startUI();
		}
		String checkPass = userLogin.getPassword();
		if (BCrypt.checkpw(password, checkPass)){
			accountInfo(userLogin);
		} else {
			System.out.println("Password doesn't match!!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			startUI();
		}
			
	}
		

	public void SignUpUI() {

		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println(" --------------------");
		System.out.println("|Welcome to BankForMe|");
		System.out.println(" --------------------");
		System.out.println(" --New Account form--");
		System.out.println("- Username:          ");
		String username = input.next().toLowerCase();
		System.out.println("- Email:             ");
		String email = input.next().toLowerCase();
		System.out.println("- Password:          ");
		String password = input.next();
		System.out.println("- Open balance:      ");
		double balance = input.nextDouble();
		UserDaoImpl userController = new UserDaoImpl();
		AccountDaoImpl accountController = new AccountDaoImpl();
		User checkUser = new User(email, username, password);
		checkUser = userController.getUserByUsername(username);
		if (checkUser == null){
			User newUser = new User(email, username, password);
			userController.createUser(newUser);
			newUser = userController.getUserByUsername(username);
			Account newAccount = new Account(newUser.getUserId(), balance);
			accountController.createAccount(newAccount);
		} else {
			System.out.println("User already exists!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			startUI();
		}
		
	}

	public char accountInfo(User u) {
		char intInput;
		String next = input.nextLine();
		int userId = u.getUserId();
		AccountDaoImpl accountController = new AccountDaoImpl();
		Account currentAccount = accountController.getAccountByUser(userId);
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println(" --------------------");
		System.out.println("|Welcome to BankForMe|");
		System.out.println("---------------------");
		System.out.println("Hello " + u.getUsername() + "!");
		System.out.println("Balance : " + currentAccount.getBalance());
		System.out.println("---------------------");
		System.out.println(" 1.Deposit");
		System.out.println(" 2.Withdraw");
		System.out.println(" 3.Logout");

		intInput = input.next().charAt(0);

		do {

			switch (intInput) {
			case '1':
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println(" --------------------");
				System.out.println("|Welcome to BankForMe|");
				System.out.println(" --------------------");
				System.out.println("How much you want to deposit: ");
				if(input.hasNextDouble()){
					double addBalance = input.nextDouble();
					if (addBalance < 0.0){
						System.out.println("You can't deposit negative values!");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						accountController.deposit(currentAccount, addBalance);
						System.out.println("Moving your money...");
						currentAccount = accountController.getAccountByUser(userId);
						System.out.println("New balance: " + currentAccount.getBalance());
						if (next.isEmpty()) {
							System.out.println("Press Enter Key.");
						}
		
						if (input.hasNextLine()) {
							next = input.nextLine();
						} else {
							next = null;
						}
					}

				} else {
					System.out.println("Incorrect input!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				accountInfo(userLogin);
				break;
			case '2':
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println(" --------------------");
				System.out.println("|Welcome to BankForMe|");
				System.out.println(" --------------------");
				System.out.println("How much you want to withdraw: ");
				if(input.hasNextDouble()){
					double withdrawBalance = input.nextDouble();
					if (withdrawBalance < 0.0) {
						System.out.println("You can't withdraw negative values!");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						if (currentAccount.getBalance() < withdrawBalance) {
							System.out.println("Sorry, you don't have enough money!");
							if (next.isEmpty()) {
								System.out.println("Press Enter Key.");
							}
		
							if (input.hasNextLine()) {
								next = input.nextLine();
							} else {
								next = null;
							}
						} else {
							accountController.withdraw(currentAccount, withdrawBalance);
							System.out.println("Moving your money...");
							currentAccount = accountController.getAccountByUser(userId);
							System.out.println("New balance: " + currentAccount.getBalance());
							if (next.isEmpty()) {
								System.out.println("Press Enter Key.");
							}
		
							if (input.hasNextLine()) {
								next = input.nextLine();
							} else {
								next = null;
							}
						}
						
					}

				} else {
					System.out.println("Incorrect input!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				accountInfo(userLogin);
				break;
				case '3':
				startUI();
				break;
				default:
				accountInfo(userLogin);
				break;
			}
		} while(intInput != '3');

		return intInput;
		
	}

	public void exitUI() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println(" --------------------");
		System.out.println("|      Goodbye!     |");
		System.out.println("|       VK2020      |");
		System.out.println(" --------------------");
		System.exit(0);
	}
}
