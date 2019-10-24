import java.io.*;
import java.util.*;
public class GenericStack<Type> {
	private class Node {
		Type item;
		Node next;
	}
	
	private Node first;
	
	public void push(Type item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	
	public Type pop() {
		Type item = first.item;
		first = first.next;
		return item;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public static void main(String[] args) {
		File file = new File("input2.txt");
		try {
			Scanner in = new Scanner(file);
			GenericStack<String> obj = new GenericStack<>();
			
			while(in.hasNext()) {
				String item = in.next();
				if(item.equals("-")&&!obj.isEmpty()) System.out.println(obj.pop());
				else obj.push(item);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}