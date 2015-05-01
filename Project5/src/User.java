import java.util.LinkedList;

public class User {
	private LinkedList<User> friends;
<<<<<<< HEAD
	private String school;
	private String name;
	private int vnumber;
=======
	String school;
	String name;
	int vertexNumber;
>>>>>>> origin/master
	
	public User(String name) {
		this.name = name;
	}
	
<<<<<<< HEAD
	public User(String name, String school, int vnumber) {
		this.name = name;
		this.school = school;
		this.vnumber = vnumber;
=======
	public User(String name, String school, LinkedList<User> friends, int vertexNumber) {
		this.name = name;
		this.school = school;
		this.friends = friends;
		this.vertexNumber = vertexNumber;
>>>>>>> origin/master
	}
	
	public void addFriend(User u) {
		friends.add(u);
	}
}
