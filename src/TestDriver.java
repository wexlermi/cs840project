
import java.io.*;
import java.util.*;

import com.javamex.classmexer.MemoryUtil;
import com.javamex.classmexer.MemoryUtil.VisibilityFilter;

import net.sourceforge.sizeof.SizeOf;
//
//import net.sourceforge.sizeof.SizeOf;

import objectexplorer.MemoryMeasurer;

public class TestDriver {
	public static void main(String[] args) throws IOException {
//		SizeOf.setLogOutputStream(new FileOutputStream("size_of_log"));

		Scanner in = new Scanner(new FileReader("testdata.txt"));
		List<Long> numberList = new ArrayList<Long>();
		while (in.hasNextLong()) {
			long num = in.nextLong();
			numberList.add(num);
		}

		List<Long> requestList = new ArrayList<Long>();
		for (Long num : numberList) {
			requestList.add(num);
		}
		Collections.shuffle(requestList);

		Set avlTree = new AvlTree();
		Set skipList = new SkipList();
		Set skipLift = new SkipLift();
		Set treap = new Treap();
		Set jumpList = new JumpList();
		
		loadSet(jumpList, numberList);

		long startTime = 0;
		long endTime = 0;

		long duration = 0;

		ObjectOutput out;

		startTime = System.nanoTime();

		loadSet(avlTree, numberList);

		endTime = System.nanoTime();
		duration = endTime - startTime;
		long avlMemory = MemoryUtil.deepMemoryUsageOf(avlTree, VisibilityFilter.ALL);
		System.out.println("avltreesize sizeof:"  + avlMemory);

		System.out.println("AVL Tree duration: " + (double) duration
				/ Math.pow(10, 6) + " ms");

		startTime = System.nanoTime();
		loadSet(skipList, numberList);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		
		long skipListMemory = MemoryUtil.deepMemoryUsageOf(skipList, VisibilityFilter.ALL);
		System.out.println("skipListMemory sizeof:"  + skipListMemory);
		System.out.println("skipList duration: " + (double) duration
				/ Math.pow(10, 6) + " ms");

		startTime = System.nanoTime();
		loadSet(skipLift, numberList);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		
		long skipLiftMemory = MemoryUtil.deepMemoryUsageOf(skipLift, VisibilityFilter.ALL);
		System.out.println("skipLiftMemory sizeof:"  + skipLiftMemory);

		System.out.println("skipLift duration: " + (double) duration
				/ Math.pow(10, 6) + " ms");

		startTime = System.nanoTime();
		loadSet(treap, numberList);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		
		long treapMemory = MemoryUtil.deepMemoryUsageOf(treap, VisibilityFilter.ALL);
		System.out.println("treapMemory sizeof:"  + treapMemory);
		
		System.out.println("treap duration: " + (double) duration
				/ Math.pow(10, 6) + " ms");
		
		startTime = System.nanoTime();
		loadSet(jumpList, numberList);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		
		long jumpListMemory = MemoryUtil.deepMemoryUsageOf(jumpList, VisibilityFilter.ALL);
		System.out.println("treapMemory sizeof:"  + jumpListMemory);
		
		System.out.println("jumpList duration: " + (double) duration
				/ Math.pow(10, 6) + " ms");

	}

	private static void loadSet(Set set, List<Long> numberList) {
		for (long num : numberList) {
			try {
				set.insert(num);	
			}
			catch (Exception e){
				System.out.println("hello " + num);
			}
			
		}
	}
}
