package com.klimavicius.ui;

import java.util.Scanner;

public class UserUI {

	public int startUI() {
		Scanner input = new Scanner(System.in);
		System.out.println(" --------------------");
		System.out.println("|Welcome to BankForMe|");
		System.out.println(" --------------------");
		System.out.println("What can we do for you today?");
		System.out.println("1");
		System.out.println("2");
		System.out.println("3");
		System.out.println("4");
		
		int menuChoice = input.nextInt();
		System.out.println(menuChoice);
		
		
		return 0;
	}
}
