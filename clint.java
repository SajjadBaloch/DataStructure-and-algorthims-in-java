import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
@SuppressWarnings("unchecked")
public class clint {
	public static void main(String[] args) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))){
			File f = new File("input.txt");
			Scanner in = new Scanner(f);
			MinPq<Integer> pq = new MinPq<Integer>();
			while(in.hasNext()) {
				int d = in.nextInt();
				pq.insert(d);
			}
			while(!pq.isEmpty()) bw.write(pq.DelMin().toString()+"    ");
			in.close();
		}
		catch(Exception e) { e.printStackTrace(); }
	}
}