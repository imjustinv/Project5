//Justin Valeroso
//Parth Mehrotra

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Friends {

	private ArrayList<User> users;

	public Friends(String file) throws Exception {
		users = build(file);
	}

	public Friends(ArrayList<User> users) {
		this.users = users;
	}

	public Stack<User> shortestPath(String name1, String name2) {
		/**
		 * Go through users find name1 set it's num to 1 add name1 to the stack
		 * Goto the lowest neighbor Set that vertex to 1+1= 2 add it to the
		 * stack go to it's first non -1 neighbor add it to the stack and then
		 * so forth every time you're going to check wether you've reached the
		 * end or the name2
		 * 
		 * Everytime you reach name2 create a linkedlist of all the things on
		 * the stack create another stack with everything but name2 and pop
		 * elements of the stack until you reach the first element that has
		 * unexplored neighbors
		 * 
		 * Everytime your reach a dead end, pop elements...
		 */
		clear();
		Stack<User> path = new Stack<User>();
		Stack<User> shortest = new Stack<User>();
		boolean noVisitedFriends = false;
		for (User u : users) {
			if (u.name.equals(name1)) {
				u.vertexNumber = 1;
				path.push(u);
			}
		}

		while (!path.empty()) {
			noVisitedFriends = false;
			for (User u : path.peek().friends) {
				// if the user is the last person of the path and has been
				// unvisited or is the best path
				if (u.name.equals(name2)
						&& (u.vertexNumber > path.peek().vertexNumber + 1 || u.vertexNumber == 0)) {
					u.vertexNumber = path.peek().vertexNumber + 1;
					path.push(u);
					shortest = (Stack<User>) path.clone();
					path.pop();
					noVisitedFriends = true;
					break;
				}

				// if the user has been unvisited
				else if (u.vertexNumber == 0) {
					u.vertexNumber = path.peek().vertexNumber + 1;
					path.push(u);
					noVisitedFriends = true;
					break;
				} else {
				}

			}
			if (!noVisitedFriends) {
				path.pop();
			}
		}

		return shortest;
	}

	public ArrayList<ArrayList<User>> findCliques(String school) {
		/*
		 * Finds cliques via breadth first search.
		 * Returns ArrayList<ArrayList<User>>. The vertexNumber was used to determine
		 * visited nodes. Any nodes that haven't been visited and are adjacent to the
		 * first in the queue are added to the queue's tempClique.
		 */
		clear();
		ArrayList<ArrayList<User>> cliques = new ArrayList<ArrayList<User>>();
		Queue<User> tempclique = new LinkedList<User>();

		for (User u : users) {
			if (u.vertexNumber == 0 && u.school != null
					&& u.school.equals(school)) {

				ArrayList<User> temp = new ArrayList<User>();
				temp.add(u);
				tempclique.add(u);
				u.vertexNumber = 1;

				while (!tempclique.isEmpty()) {
					for (User friend : tempclique.peek().friends) {
						if (friend.vertexNumber == 0 && friend.school != null
								&& friend.school.equals(school)) {
							friend.vertexNumber = 1;
							tempclique.add(friend);
							temp.add(friend);
						}

					}
					tempclique.remove();
				}
				cliques.add(temp);
			}
		}
		return cliques;
	}

	public LinkedList<User> findConnectors() {
		/*
		 * Returns LinkedList of Users that are determined connectors
		 * It was determined that in order to find connectors a for loop that iterates
		 * the user list was needed. Every user would be tested as a connector by comparing
		 * whether or not it had the same adjacents in the list if it were taken out of the
		 * graph.
		 */
		clear();
		LinkedList<User> listOfConnectors = new LinkedList<User>();
		for (int i = 0; i < users.size(); i++) {
			clear();
			if (users.get(i).friends.size() <= 1) {
				continue;
			} else {
				users.get(i).vertexNumber = 1;
				LinkedList<User> rest = dfs(users.get(i).friends.get(0));
				LinkedList<User> compare = new LinkedList<User>();
				for (int g = 1; g < users.get(i).friends.size(); g++) {
					compare.add(users.get(i).friends.get(g));
				}
				
				boolean notFound = false;
				for (int g = 0; g < compare.size(); g++) {
					if (!rest.contains(compare.get(g))) {
						notFound = true;
					}
				}
				
				if (notFound) {
					listOfConnectors.add(users.get(i));
				}
			}
		}
		return listOfConnectors;
	}

	public ArrayList<User> build(String filename) throws Exception {
		/*
		 * Builds the tree using the given file format.
		 */
		ArrayList<User> users = new ArrayList<User>();
		Scanner sc1 = new Scanner(new File(filename));
		String line = sc1.nextLine();
		line = line.toLowerCase();
		int numOfStudents = Integer.parseInt(line);

		for (int j = 0; j < numOfStudents; j++) {
			line = sc1.nextLine();
			line = line.toLowerCase();
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '|') {
					// if student
					if (line.charAt(i + 1) == 'y') {
						String name = line.substring(0, i);
						String school = line.substring(i + 3, line.length());
						LinkedList<User> temp2 = new LinkedList<User>();
						User temp = new User(name, school, temp2, 0);
						users.add(temp);

					}
					// if not a student
					else if (line.charAt(i + 1) == 'n') {
						String name = line.substring(0, i);
						LinkedList<User> temp2 = new LinkedList<User>();
						User temp = new User(name, null, temp2, 0);
						users.add(temp);
					}
				}
			}
		}
		while (sc1.hasNext()) {
			line = sc1.nextLine();
			line = line.toLowerCase();
			String name1 = line.substring(0, line.indexOf('|'));
			String name2 = line.substring(line.indexOf('|') + 1, line.length());
			User ptr1 = null;
			User ptr2 = null;

			// assigns the nodes to ptr variables
			for (User n : users) {
				if (name1.equals(n.name)) {
					ptr1 = n;
				} else if (name2.equals(n.name)) {
					ptr2 = n;
				}
			}

			if (ptr1 != null && ptr2 != null) {
				ptr1.friends.add(ptr2);
				ptr2.friends.add(ptr1);
			}
		}

		return users;
	}

	public LinkedList<User> dfs(User u) {
		/*
		 * This dfs method provides easier access to depth first search using stacks.
		 * 
		 */
		Stack<User> s1 = new Stack<User>();
		LinkedList<User> pathList = new LinkedList<User>();
		boolean noVisitedFriends = false;

		s1.push(u);
		pathList.add(u);
		u.vertexNumber = 1;

		while (!s1.isEmpty()) {
			noVisitedFriends = false;
			for (User friend : s1.peek().friends) {
				if (friend.vertexNumber == 0) {
					s1.push(friend);
					pathList.add(friend);
					friend.vertexNumber = 1;
					noVisitedFriends = true;
					break;
				} else {

				}
			}
			if (!noVisitedFriends) {
				s1.pop();
			}
		}

		return pathList;
	}

	public void clear() {
		/*
		 * Resets all the vertexNumbers to 0. This is so that it is possible to use them as boolean
		 * values or trackers.
		 */
		for (User u : users) {
			u.vertexNumber = 0;
		}
	}

	public String toString() {
		String str = null;
		for (User u : users) {
			str += u.name + " goes to " + u.school + "\n";
			for (User f : u.friends) {
				str += u.name + " is friends with " + f.name + "\n";
			}
		}
		return str;
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
				name1 = name1.toLowerCase();
				name2 = name2.toLowerCase();
				if (f.shortestPath(name1,name2).empty()){
					System.out.println("Sorry, those names were not found.");
				}
				else{
					System.out.println(f.shortestPath(name1, name2));
				}
			} else if (input.equals("2")) {
				System.out.println("Clique finder");
				System.out
						.println("What school are you trying to find cliques in?");
				String school = sc.nextLine();
				school = school.toLowerCase();
				if (f.findCliques(school).isEmpty()){
					System.out.println("Sorry, there were no cliques in that school.");
				}
				else{
					System.out.println(f.findCliques(school));
				}
			} else if (input.equals("3")) {
				System.out.println("Find Connectors");
				System.out.println(f.findConnectors());
				break;
			} else {
				System.out.println("Not a viable option. Pick another.");
				input = sc.nextLine();
			}
		}
	}
}