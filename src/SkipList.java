//http://www.crimsonglow.ca/~kjiwa/src/java-skip-list/SkipList.java

import java.util.Random;

public class SkipList implements Set, java.io.Serializable
{
	public static void main(String[] args) {
		SkipList skipList = new SkipList();
		for (int i = 1; i < 100; i++) {
			skipList.insert(i);
		}
		System.out.println("3: " + skipList.contains(3));
		System.out.println("322: " + skipList.contains(322));
		System.out.println("101: " + skipList.contains(101));
		System.out.println("99: " + skipList.contains(99));
		System.out.println(skipList);
	}
	private class Node implements java.io.Serializable
	{
		public Comparable key;
		public long level;
		public Node next;
		public Node down;
		
		public Node(Comparable key, long level, Node next, Node down)
		{
			this.key = key;
			this.level = level;
			this.next = next;
			this.down = down;
		}
		
		public String toString(){
			return key + "";
		}
	}
	
	private Node _head;
	private Random _random;
	private long _size;
	private double _p;

	private static final long MIN_VALUE = Long.MIN_VALUE;
	
	private long _level()
	{
		long level = 0;
		while (level <= _size && _random.nextDouble() < _p) {
			level++;
		}
		
		return level;
	}
	
	public SkipList()
	{
		_head = new Node(MIN_VALUE, 0, null, null);
		_random = new Random();
		_size = 0;
		_p = 0.5;
	}
	
	public void insert(Comparable key)
	{
		long level = _level();
		if (level > _head.level) {
			_head = new Node(MIN_VALUE, level, null, _head);
		}
		
		Node cur = _head;
		Node last = null;
		
		while (cur != null) {
			if (cur.next == null || cur.next.key.compareTo(key)>0) {
				if (level >= cur.level) {
					Node n = new Node(key, cur.level, cur.next, null);
					if (last != null) {
						last.down = n;
					}
					
					cur.next = n;
					last = n;
				}
				
				cur = cur.down;
				continue;
			} else if (cur.next.key == key) {
				return;
			}
			
			cur = cur.next;
		}
		
		_size++;
	}
	
	
	public void delete(Comparable key)
	{
		
		Node cur = _head;
		while (cur != null) {
			if (cur.next == null || cur.next.key.compareTo(key) >= 0) {
				if (cur.next != null && cur.next.key == key) {
					cur.next = cur.next.next;
				}
				
				cur = cur.down;
				continue;
			}
			
			cur = cur.next;
		}
		
		_size--;
	}
	
	public boolean contains(Comparable key)
	{
		Node cur = _head;
		while (cur != null) {
			if (cur.next == null || cur.next.key.compareTo(key) > 0) {
				cur = cur.down;
				continue;
			} else if (cur.next.key == key) {
				return true;
			}
			
			cur = cur.next;
		}
		
		return false;
	}
	
	public String toString()
	{
		String str = "";
		Node levelHead = _head;
		Node cur;
		while (levelHead != null) {
			cur = levelHead;
			str += "\nLEVEL " + levelHead.level +": ";
			while (cur.next != null){
				str += cur.next.toString() + " ";
				cur = cur.next;
			}
			levelHead = levelHead.down;
		}
		
		return str;
	}
}
