//Original SkipList implementation: http://www.crimsonglow.ca/~kjiwa/src/java-skip-list/SkipList.java

import java.util.Random;

public class SkipLift implements Set {
	public static void main(String[] args) {
		SkipLift skipLift = new SkipLift();
		for (int i = 1; i < 100; i++) {
			skipLift.insert(i);
		}
		System.out.println("3: " + skipLift.contains(3));
		System.out.println("322: " + skipLift.contains(322));
		System.out.println("101: " + skipLift.contains(101));
		System.out.println("99: " + skipLift.contains(99));
		System.out.println(skipLift);
	}

	private class Node {
		public long value;
		public long level;
		public Node next;
		public Node prev;
		public Node down;

		public Node(long value, long level) {
			this.value = value;
			this.level = level;
		}

		public String toString() {
			return value + "";
		}
	}

	private Node head;
	private Random random;
	private long size;
	private double p;
	private static final long MIN_VALUE = Long.MIN_VALUE;

	private long randomLevel() {
		long level = 0;
		while (random.nextDouble() < p && level < head.level) {
			level++;
		}

		return level;
	}

	public SkipLift() {
		head = new Node(MIN_VALUE, 0);
		random = new Random();
		size = 0;
		p = 0.5;
	}

	public void insert(Long value) {
		long level = randomLevel();
		Node newNode = new Node(value, level);
		if (level >= 1)
			newNode.down = new Node(value, level - 1);
		if (level == head.level) {
			Node oldHead = head;
			head = new Node(MIN_VALUE, level + 1);
			head.down = oldHead;
		}
		Node cur = head;

		while (cur.level >= level) {
			if (cur.level > 0) {
				while (cur.down == null) {
					cur = cur.prev;
				}
				if (cur.value == MIN_VALUE && cur.down.level < level
						&& level < cur.level) {
					Node newLevelHead = new Node(MIN_VALUE, level);
					newLevelHead.down = cur.down;
					cur.down = newLevelHead;
				}
				cur = cur.down;
			}
			while (cur.next != null && cur.next.value <= value) {
				cur = cur.next;
			}

			if (cur.level == level) {
				newNode.next = cur.next;
				newNode.prev = cur;
				if (cur.next != null)
					cur.next.prev = newNode;
				cur.next = newNode;
				newNode = newNode.down;
				if (newNode != null) {
					level--;
				}
				else{
					break;
				}
			}

		}
		size++;
	}

	public boolean contains(long value) {
		Node cur = head;
		long pred = MIN_VALUE;
		while (cur.value != value && cur.level > 0){
			while (cur.down == null){
				cur = cur.prev;
			}
			cur = cur.down;
			while (cur.next != null && cur.next.value <= value){
				cur = cur.next;
			}
			if (pred < cur.value){
				pred = cur.value;
			}
		}
		
		return pred == value;
	}

	public String toString() {
		String str = "";
		Node levelHead = head;
		Node cur;
		while (levelHead != null) {
			cur = levelHead;
			str += "\nLEVEL " + levelHead.level + ": ";
			while (cur.next != null) {
				str += cur.next.toString() + " ";
				cur = cur.next;
			}
			levelHead = levelHead.down;
		}

		return str;
	}

	@Override
	public void insert(Comparable data) {
		insert((Long)data);
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Comparable data) {
		return contains((Long)data);
	}

	@Override
	public void delete(Comparable data) {
		delete((Long)data);
		// TODO Auto-generated method stub
		
	}
}
