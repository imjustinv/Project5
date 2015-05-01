//Justin Valeroso
//Parth Mehrotra

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Friends {

	private ArrayList<User> users;

	public Friends(String file) {
		users = build(file);
	}

	public Friends(ArrayList<User> users) {
		this.users = users;
	}
	
	public String shortestPath(String name1, String name2) {
		return null;
	}

	public String findCliques(String school) {
		return null;
	}
	
	public String findConnectors() {
		return null;
	}

	public ArrayList<User> build(String filename) {
		return null;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input file for the graph.");
		String input = sc.nextLine();

		Friends f = new Friends(input);

		System.out
				.println("Select option: 1. Shortest Path 2. Cliques 3. Connectors 4. Quit ");
		input = sc.nextLine();

		while (!input.equals("4")) {
			if (input.equals("1")){
				System.out.println("Shortest Path");
				System.out
						.println("Please enter the name of the starting person");
				String name1 = sc.nextLine();
				System.out
						.println("Please enter the name of the second person");
				String name2 = sc.nextLine();
				System.out.println(f.shortestPath(name1, name2));
			} else if (input.equals("2")) {
				System.out.println("Clique finder");
				System.out.println("What school are you trying to find cliques in?");
				String school = sc.nextLine();
				System.out.println(f.findCliques(school));
			} else if (input.equals("3")) {
				System.out.println("Find Connectors");
				System.out.println(f.findConnectors());
			} else {
				System.out.println("Not a viable option. Pick another.");
				input = sc.nextLine();
			}
		}
	}
}