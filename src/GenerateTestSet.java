import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.Random;
public class GenerateTestSet {
	private static final Random random = new Random();
	public static void main(String[] args) throws FileNotFoundException{
		PrintWriter writer= new PrintWriter("bin/testdata.txt");
		for (long i = 1; i <= 1*(int)Math.pow(10, 4); i++){
			writer.println(random.nextInt());
		}
		writer.close();
	}
}
