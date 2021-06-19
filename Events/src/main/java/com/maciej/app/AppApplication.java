package com.maciej.app;

import java.util.Scanner;

public class AppApplication {
	public static void main(String[] args) {
		System.out.println("Please provide a file path");
		Scanner scanner = new Scanner(System.in);
		String path = scanner.nextLine();
		DatabaseConnector databaseConnector = new DatabaseConnector();
		databaseConnector.handleEvents(EventFileParser.parseEventFile(path));
	}
}