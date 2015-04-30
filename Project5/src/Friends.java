//Justin Valeroso
//Parth Mehrotra

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class Friends {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please input file name.");
		String input = sc.nextLine();
		ArrayList<Node> Graph = GraphBuild(input);
		
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
	
	static ArrayList<Node> GraphBuild(String fileName){
		ArrayList<Node> Graph = new ArrayList<Node>();
			Scanner sc1 = new Scanner(fileName);
			String line = sc1.nextLine();
			while (sc1.hasNext()){
				line =  sc1.nextLine();
				for (int i = 0; i < line.length()-1; i++){
					if(line.charAt(i) == '|'){
						System.out.println("At " + i);
						break;
					}
				}
			}
		return Graph;
	}

}




