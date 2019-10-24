import java.io.*;
import java.util.*;
public class FileCreator {
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		try(BufferedWriter out = new BufferedWriter(new FileWriter(args[1]))) {
			Random rn = new Random();
			for(int i = 0; i < size; i++) {
				int item = rn.nextInt(size+1);
				out.write(Integer.toString(item)+" ");
			}
		}
		catch(IOException e) { e.printStackTrace(); }
	}
}