import java.util.LinkedList;

public class User {
	private LinkedList<User> friends;
	private String school;
	private String name;
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name, String school) {
		this.name = name;
		this.school = school;
	}
	
	public void addFriend(User u) {
		friends.add(u);
	}
}
