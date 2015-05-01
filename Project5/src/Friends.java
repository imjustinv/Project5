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
		ArrayList<Node> Graph = GraphBuild(input);
		for (Node n : Graph){
			System.out.println(n.name + "|"+ n.school);
		}
		System.out.println("Select option: 1. Shortest Path 2. Cliques 3. Connectors 4. Quit ");
		input = sc.nextLine();
		
		while (input.compareTo("4") != 0){
			if (input.compareTo("1") == 0){
				System.out.println("You picked 1");
				input = sc.nextLine();
			}
			else if (input.compareTo("2") == 0){
				System.out.println("You picked 2");
				input = sc.nextLine();
			}
			else if (input.compareTo("3") == 0){
				System.out.println("You picked 3");
				input = sc.nextLine();
			}
			else{
				System.out.println("Not a viable option. Pick another.");
				input = sc.nextLine();
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
		return Graph;
	}

}




