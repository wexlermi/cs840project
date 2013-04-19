import java.io.*;
import java.util.*;

import com.javamex.classmexer.MemoryUtil;
import com.javamex.classmexer.MemoryUtil.VisibilityFilter;

public class TestDriver {
	static List<Long> numberList;
	static List<Long> requestList;

	public static void main(String[] args) throws IOException {
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

		runTest(jumpList, "jumpList");
		runTest(avlTree, "avltree");
		runTest(skipList, "skipList");
		runTest(skipLift, "skipLift");
		runTest(treap, "treap");

	}

	private static void loadSet(Set set, List<Long> numberList) {
		for (long num : numberList) {
			try {
				set.insert(num);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private static void lookupRequests(Set set, List<Long> requestList,
			String listName) {
		for (long num : requestList) {
			set.contains(num);
		}
	}

	private static void runTest(Set set, String listName) {
		long startTime = 0;
		long endTime = 0;
		long insertDuration = 0;
		long lookupDuration = 0;

		startTime = System.nanoTime();
		loadSet(set, numberList);
		endTime = System.nanoTime();
		insertDuration = endTime - startTime;
		System.out.println(listName + " insert duration: "
				+ (double) insertDuration / Math.pow(10, 6) + " ms");
		startTime = System.nanoTime();
		lookupRequests(set, requestList, listName);
		endTime = System.nanoTime();
		lookupDuration = endTime - startTime;

		System.out.println(listName + " lookup duration: "
				+ (double) lookupDuration / Math.pow(10, 6) + " ms");
		long memory = MemoryUtil.deepMemoryUsageOf(set, VisibilityFilter.ALL);

		System.out.println(listName + " sizeof (mb):" + memory / 1048576.0);

	}
}
