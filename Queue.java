import java.io.*;
import java.util.*;
public class Queue {
	private class Node {
		int item;
		Node next;
	}
	private Node first, last;
	
	public void push(int item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()) first = last;
		else oldLast.next=last;
	}
	
	public int pop() {
		if(isEmpty()) return -1;
		int item = first.item;
		first = first.next;
		if(isEmpty()) last = null;
		return item;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public static void main(String[] args) {
		File file = new File("input.txt");
		try {
			Queue obj = new Queue();
			Scanner in = new Scanner(file);
			while(in.hasNext()) {
				int item = in.nextInt();
				if(item == -1) System.out.println(obj.pop());
				else obj.push(item);
			}
		}
		catch(FileNotFoundException e) {
				e.printStackTrace();
		}
	}
}