package com.maciej.app;

import java.util.List;
import java.util.Scanner;

public class AppApplication {
	public static void main(String[] args) {
		System.out.println("Please provide path to file");
		Scanner scanner = new Scanner(System.in);
		String path = scanner.nextLine();

		EventFileParser eventFileParser = new EventFileParser();
		List<Event> events = eventFileParser.parseEventFile(path);

		for (Event event : events) {
			System.out.println(event.toString());
		}
	}
}