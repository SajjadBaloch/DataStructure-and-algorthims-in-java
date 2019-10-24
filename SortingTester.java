import java.io.*;
import java.util.*;
@SuppressWarnings("unchecked")
public class SortingTester extends mergeSort {
	private static Comparable[] a;
	private SortingTester(){ a = new Comparable[10]; }
	public static void main(String args[]) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("sortOut.txt"))) {
			File fileIn = new File("sortIn.txt");
			Scanner in = new Scanner(fileIn);
			int i = 0;
			SortingTester obj = new SortingTester();
			while(in.hasNext()) {
				Comparable item = in.next();
				a[i] = item;
				i++;
				if(i == a.length) resize(a.length*2);
			}
			resize(i);
			a = sort(a);
			for(int j = 0;j < a.length; j++) bw.write((a[j])+" ");
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	
	private static void resize(int n) {
		Comparable[] b = new Comparable[n];
		for(int i = 0; i<a.length && a[i] != null; i++) b[i] = a[i];
		a = b;
	}
	
}