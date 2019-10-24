import java.util.*;
import java.io.*;
public class Stack {
	private class Node {
		int item;
		Node next;
	}
	Node first;
	
	public void push(int item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	
	public int pop() {
		if(isEmpty()) return -1;
		int item = first.item;
		first = first.next;
		return item;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public static void main(String[] args) {
		File file = new File("input.txt");
		try {
			Scanner in =new Scanner(file);
			Stack obj = new Stack();
			while(in.hasNext()) {
				int item = in.nextInt();
				if(item == -1) System.out.println(obj.pop());
				else obj.push(item);
			}
			System.out.println("End of file");
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}