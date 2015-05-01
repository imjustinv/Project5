import java.util.Stack;
import java.util.LinkedList;

public class Test {
	public static void main(String[] args) {
		Stack<String> s1 = new Stack<String>();
		s1.push("hi");
		s1.push("2");
		s1.push("3");
		Stack<String> s2 = s1;
		Stack<String> s3 = (Stack) s2.clone();
		LinkedList<String> ll = (LinkedList) s2.clone();

		s1.pop();

		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(ll);
	}
}
