import java.util.LinkedList;

public class User {
	LinkedList<User> friends;
	String school;
	String name;
	int vertexNumber;
	
	public User(String name) {
		this.name = name;
	}

	public User(String name, String school, LinkedList<User> friends, int vertexNumber) {
		this.name = name;
		this.school = school;
		this.friends = friends;
		this.vertexNumber = vertexNumber;
	}
	
	public void addFriend(User u) {
		friends.add(u);
	}
}
