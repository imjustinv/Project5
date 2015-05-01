//Justin Valeroso
//Parth Mehrotra

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class Friends {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input file name.");
		String input = sc.nextLine();
		
		//Reads file and builds it. Prints out the names|school as well as who the person's friends with.
		ArrayList<Node> Graph = GraphBuild(input);
		for (Node n : Graph){
			System.out.println(n.name + "|"+ n.school);
			for (Node l: n.adjacent){
				System.out.println(n.name + " is friends with " + l.name);
			}
		}
		
		System.out.println("Select option: 1. Shortest Path 2. Cliques 3. Connectors 4. Quit ");
		input = sc.nextLine();
		
		while (!input.equals("4")){
			if (input.equals("1")){
				System.out.println("You picked 1");
				input = sc.nextLine();
			}
			else if (input.equals("2")){
				System.out.println("Please type in a school.");
				input = sc.nextLine();
				Cliques(Graph, input);
			}
			else if (input.equals("3")){
				System.out.println("You picked 3");
				input = sc.nextLine();
			}
			else if (!input.equals("4")){
				System.out.println("Not a viable option. Pick another.");
				input = sc.nextLine();
			}
			else{
			}
		}
	}
	
	static ArrayList<Node> GraphBuild(String fileName) throws FileNotFoundException{
		ArrayList<Node> Graph = new ArrayList<Node>();
		
		Scanner sc1 = new Scanner(new File(fileName));
		String line = sc1.nextLine();
		int numOfStudents = Integer.parseInt(line);
		
		
		for(int j = 0; j < numOfStudents; j++){
			line =  sc1.nextLine();
			for (int i = 0; i < line.length(); i++){
				if(line.charAt(i) == '|'){
					//if student
					if (line.charAt(i+1) == 'y'){
						String name = line.substring(0,i);
						String school = line.substring(i+3,line.length());
						ArrayList<Node> temp2 = new ArrayList<Node>();
						Node temp = new Node (name, school, temp2, j);
						Graph.add(temp);
					}
					//if not a student
					else if (line.charAt(i+1) == 'n'){
						String name = line.substring(0,i);
						ArrayList<Node> temp2 = new ArrayList<Node>();
						Node temp = new Node (name, null, temp2, j);
						Graph.add(temp);
					}
				}
			}
		}
		while (sc1.hasNext()){
			line = sc1.nextLine();
			String name1 = line.substring(0, line.indexOf('|'));
			String name2 = line.substring(line.indexOf('|')+1, line.length());
			Node ptr1 = null;
			Node ptr2 = null;
			
			// assigns the nodes to ptr variables
			for(Node n : Graph){
				if (name1.equals(n.name)){
					ptr1 = n;
				}
				else if (name2.equals(n.name)){
					ptr2 = n;
				}
			}
			
			if (ptr1 != null && ptr2 != null){
				ptr1.adjacent.add(ptr2);
				ptr2.adjacent.add(ptr1);
			}
		}
		return Graph;
	}
	
	static void Cliques (ArrayList<Node> Graph, String school){
		ArrayList<Node> visited = new ArrayList<Node>();
		ArrayList<ArrayList<Node>> cliques = new ArrayList<ArrayList<Node>>();
		boolean isVisit = false;
		for (Node n : Graph){
			if (visited.size() != Graph.size()){
				for (Node l : visited){
					//if node has already been visited
					if (n.name.equals(l.name)){
						isVisit = true;
						break;
					}
					else{
						
					}
				}
			}
			else{
				
			}
		}
	}
}




