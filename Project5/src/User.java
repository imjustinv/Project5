import java.util.LinkedList;

public class User {
	private LinkedList<User> friends;
	private String school;
	private String name;
	private int vnumber;
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name, String school, int vnumber) {
		this.name = name;
		this.school = school;
		this.vnumber = vnumber;
	}
	
	public void addFriend(User u) {
		friends.add(u);
	}
}
