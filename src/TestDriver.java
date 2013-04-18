
import java.io.*;
import java.util.*;

import com.javamex.classmexer.MemoryUtil;
import com.javamex.classmexer.MemoryUtil.VisibilityFilter;

//
//import net.sourceforge.sizeof.SizeOf;

public class TestDriver {
	static List<Long> numberList;
	static List<Long> requestList;
	public static void main(String[] args) throws IOException {
//		SizeOf.setLogOutputStream(new FileOutputStream("size_of_log"));

		Scanner in = new Scanner(new FileReader("testdata.txt"));
		numberList = new ArrayList<Long>();
		while (in.hasNextLong()) {
			long num = in.nextLong();
			numberList.add(num);
		}

		requestList = new ArrayList<Long>();
		for (Long num : numberList) {
			requestList.add(num);
		}
		Collections.shuffle(requestList);

		Set avlTree = new AvlTree();
		Set skipList = new SkipList();
		Set skipLift = new SkipLift();
		Set treap = new Treap();
		Set jumpList = new JumpList();
		
		runTest(avlTree, "avltree");
		runTest(skipList, "skipList");
		runTest(skipLift, "skipLift");
		runTest(treap, "treap");
		runTest(jumpList, "jumpList");
		
//		loadSet(jumpList, numberList);
//
//		long startTime = 0;
//		long endTime = 0;
//
//		long duration = 0;
//
//
//		startTime = System.nanoTime();
//		loadSet(avlTree, numberList);
//		endTime = System.nanoTime();
//		duration = endTime - startTime;
//		System.out.println("AVL Tree insert duration: " + (double) duration
//				/ Math.pow(10, 6) + " ms");
//		
//		
//		
//		
//		lookupRequests(avlTree, requestList);
//		
//		
//		
//		long avlMemory = MemoryUtil.deepMemoryUsageOf(avlTree, VisibilityFilter.ALL);
//		
//		System.out.println("avltreesize sizeof:"  + avlMemory/1048576.0);
//
//
//		startTime = System.nanoTime();
//		loadSet(skipList, numberList);
//		lookupRequests(skipList, requestList);
//		endTime = System.nanoTime();
//		duration = endTime - startTime;
//		
//		long skipListMemory = MemoryUtil.deepMemoryUsageOf(skipList, VisibilityFilter.ALL);
//		System.out.println("skipListMemory sizeof:"  + skipListMemory/1048576.0);
//		System.out.println("skipList duration: " + (double) duration
//				/ Math.pow(10, 6) + " ms");
//
//		startTime = System.nanoTime();
//		loadSet(skipLift, numberList);
//		lookupRequests(skipLift, requestList);
//		endTime = System.nanoTime();
//		duration = endTime - startTime;
//		
//		long skipLiftMemory = MemoryUtil.deepMemoryUsageOf(skipLift, VisibilityFilter.ALL);
//		System.out.println("skipLiftMemory sizeof:"  + skipLiftMemory/1048576.0);
//
//		System.out.println("skipLift duration: " + (double) duration
//				/ Math.pow(10, 6) + " ms");
//
//		startTime = System.nanoTime();
//		loadSet(treap, numberList);
//		lookupRequests(treap, requestList);
//		endTime = System.nanoTime();
//		duration = endTime - startTime;
//		
//		long treapMemory = MemoryUtil.deepMemoryUsageOf(treap, VisibilityFilter.ALL);
//		System.out.println("treapMemory sizeof:"  + treapMemory/1048576.0);
//		
//		System.out.println("treap duration: " + (double) duration
//				/ Math.pow(10, 6) + " ms");
//		
//		startTime = System.nanoTime();
//		loadSet(jumpList, numberList);
//		lookupRequests(jumpList, requestList);
//		endTime = System.nanoTime();
//		duration = endTime - startTime;
//		
//		long jumpListMemory = MemoryUtil.deepMemoryUsageOf(jumpList, VisibilityFilter.ALL);
//		System.out.println("jumpListMemory sizeof:"  + jumpListMemory/1048576.0);
//		
//		System.out.println("jumpList duration: " + (double) duration
//				/ Math.pow(10, 6) + " ms");

	}

	private static void loadSet(Set set, List<Long> numberList) {
		int i = 0;
		for (long num : numberList) {
			try {
				//if (i%10==0) 
					//System.out.println(i);
				set.insert(num);	
				//i++;
			}
			catch (Exception e){
				System.out.println("hello " + num);
				e.printStackTrace();
			}
			
		}
	}
	
	private static void lookupRequests(Set set, List<Long> requestList, String listName){
		int numMissing = 0;
		for (long num : requestList){
			//System.out.println(num);
			if (!set.contains(num)){
				numMissing++;
				//System.out.println(num);
			}
		}
		if (numMissing > 0) System.out.println(listName + " numMissing: " + numMissing);
	}
	
	private static void runTest(Set set, String listName){
		long startTime = 0;
		long endTime = 0;
		long insertDuration = 0;
		long lookupDuration = 0;

		startTime = System.nanoTime();
		loadSet(set, numberList);
		endTime = System.nanoTime();
		insertDuration = endTime - startTime;
		
		startTime = System.nanoTime();
		lookupRequests(set, requestList, listName);
		endTime = System.nanoTime();
		lookupDuration = endTime - startTime;
		
		long memory = MemoryUtil.deepMemoryUsageOf(set, VisibilityFilter.ALL);
		
		System.out.println(listName + " insert duration: " + (double) insertDuration
				/ Math.pow(10, 6) + " ms");
		System.out.println(listName + " lookup duration: " + (double) lookupDuration
				/ Math.pow(10, 6) + " ms");
		System.out.println(listName + " sizeof (mb):"  + memory/1048576.0);


	}
}
