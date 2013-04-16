import java.io.*;
import java.util.*;


public class TestDriver {
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new FileReader("testdata.txt"));
		List<Long> numberList = new ArrayList<Long>();
		while (in.hasNextLong()){
			long num = in.nextLong();
			numberList.add(num);
		}
		
		List<Long> requestList = new ArrayList<Long>();
		for (Long num : numberList){
			requestList.add(num);
		}
		Collections.shuffle(requestList);
		
		Set avlTree = new AvlTree();
		Set skipList = new SkipList();
		Set skipLift = new SkipLift();
		Set treap = new Treap();
		
		long startTime = 0; 
		long endTime = 0;

		long duration = 0;
		
		ObjectOutput out;


		startTime = System.nanoTime();
		loadSet(avlTree, numberList);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		
		out = new ObjectOutputStream(new FileOutputStream("avl_tree.ser"));
		out.writeObject(avlTree);
		out.close();
		System.out.println("AVL Tree duration: " + (double)duration/Math.pow(10, 6) + " ms");
		
		startTime = System.nanoTime();
		loadSet(skipList, numberList);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		
		out = new ObjectOutputStream(new FileOutputStream("skipList.ser"));
		out.writeObject(skipList);
		out.close();
		
		System.out.println("skipList duration: " + (double)duration/Math.pow(10, 6) + " ms");

		startTime = System.nanoTime();
		loadSet(skipLift, numberList);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		
		out = new ObjectOutputStream(new FileOutputStream("skipLift.ser"));
		out.writeObject(skipLift);
		out.close();
		
		System.out.println("skipLift duration: " + (double)duration/Math.pow(10, 6) + " ms");

		startTime = System.nanoTime();
		loadSet(treap, numberList);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		
		out = new ObjectOutputStream(new FileOutputStream("treap.ser"));
		out.writeObject(treap);
		out.close();
		
		System.out.println("treap duration: " + (double)duration/Math.pow(10, 6) + " ms");

	}
	
	private static void loadSet(Set set, List<Long> numberList){
		for ( long num : numberList){
			set.insert(num);
		}
	}
}
