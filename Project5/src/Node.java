import java.util.ArrayList;

public class Node {
	String name;
	String school;
	ArrayList<Node> adjacent;
	int vNumber;
	
	Node (String name, String school, ArrayList<Node> adjacent, int vNumber){
		this.name = name;
		this.school = school;
		this.adjacent = adjacent;
		this.vNumber = vNumber;
	}
}
