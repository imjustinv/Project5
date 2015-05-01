//Justin Valeroso
//Parth Mehrotra

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Friends {

	private ArrayList<User> users;

	public Friends(String file) throws Exception {
		users = build(file);
	}

	public Friends(ArrayList<User> users) {
		this.users = users;
	}

	public String shortestPath(String name1, String name2) {
		
		
		return null;
	}

	public String findCliques(String school) {
		ArrayList<User> visited = new ArrayList<User>();
		ArrayList<ArrayList<User>> cliques = new ArrayList<ArrayList<User>>();
		
		for (User n : users){
			//if user hasn't been visited
			if (!isVisited(n, visited)){
				if (n.school.equals(school)){
					ArrayList<User> temp = new ArrayList<User>();
					temp.add(n);
					for (User l : n.friends){
						if (!isVisited(l, visited)){
							if (l.school.equals(school)){
								temp.add(l);
							}
						}
					}
				}
			}
		}
		
		return null;
	}

	public boolean isVisited(User n, ArrayList<User> visited){
		for (User l : visited){
			if (n.name.equals(l.name)){
				return true;
			}
		}
		visited.add(n);
		return false;
	}
	
	public String findConnectors() {
		return null;
	}

	public ArrayList<User> build(String filename) throws Exception {
		ArrayList<User> Graph = new ArrayList<User>();

		Scanner sc1 = new Scanner(new File(filename));
		String line = sc1.nextLine();
		int numOfStudents = Integer.parseInt(line);

		for (int j = 0; j < numOfStudents; j++) {
			line = sc1.nextLine();
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '|') {
					// if student
					if (line.charAt(i + 1) == 'y') {
						String name = line.substring(0, i);
						String school = line.substring(i + 3, line.length());
						LinkedList<User> temp2 = new LinkedList<User>();
						User temp = new User(name, school, temp2, 0);
						Graph.add(temp);
					}
					// if not a student
					else if (line.charAt(i + 1) == 'n') {
						String name = line.substring(0, i);
						LinkedList<User> temp2 = new LinkedList<User>();
						User temp = new User(name, null, temp2, 0);
						Graph.add(temp);
					}
				}
			}
		}
		while (sc1.hasNext()){
			line = sc1.nextLine();
			String name1 = line.substring(0, line.indexOf('|'));
			String name2 = line.substring(line.indexOf('|')+1, line.length());
			User ptr1 = null;
			User ptr2 = null;
			
			// assigns the nodes to ptr variables
			for(User n : Graph){
				if (name1.equals(n.name)){
					ptr1 = n;
				}
				else if (name2.equals(n.name)){
					ptr2 = n;
				}
			}
			
			if (ptr1 != null && ptr2 != null){
				ptr1.friends.add(ptr2);
				ptr2.friends.add(ptr1);
			}
		}
		
		for (User u : Graph){
			System.out.println(u.name + " goes to " +  u.school);
				for (User f : u.friends){
					System.out.println(u.name + " is friends with " + f.name);
				}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input file for the graph.");
		String input = sc.nextLine();

		Friends f = new Friends(input);
		
		
		System.out
				.println("Select option: 1. Shortest Path 2. Cliques 3. Connectors 4. Quit ");
		input = sc.nextLine();

		while (!input.equals("4")) {
			if (input.equals("1")) {
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
				System.out
						.println("What school are you trying to find cliques in?");
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
