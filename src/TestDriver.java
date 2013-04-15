import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;


public class TestDriver {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader("testdata.txt"));
		LinkedList<Long> numberList = new LinkedList<Long>();
		while (in.hasNextLong()){
			long num = in.nextLong();
			numberList.add(num);
		}
		
		Set avlTree = new AvlTree();
		Set skipList = new SkipList();
		Set skipLift = new SkipLift();
		Set treap = new Treap();
		loadSet(avlTree, numberList);
		loadSet(skipList, numberList);
		loadSet(skipLift, numberList);
		loadSet(treap, numberList);
	}
	
	private static void loadSet(Set set, LinkedList<Long> numberList){
		for ( long num : numberList){
			set.insert(num);
		}
	}
}
