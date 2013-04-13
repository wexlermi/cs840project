//Got inspriation from http://users.cis.fiu.edu/~weiss/dsaajava/code/DataStructures/Treap.java

import java.util.Random;
public class Treap implements OrderedSet {
	TreapNode root;
	static Random random = new Random();
	private static class TreapNode {
		Comparable value;
		int priority;
		TreapNode leftChild;
		TreapNode rightChild;
		TreapNode parent;
		public TreapNode(Comparable value, int priority){
			this.value = value;
			this.priority = priority;
		}
	}

	
	public static void main(){
		Treap treap = new Treap();
		treap.insert(5);
		treap.insert(4);
		treap.insert(8);
		treap.delete(4);
		
	}
	
	public Treap(){
		
	}
	
	public void insert(Comparable data){
		if (root == null)
			root = new TreapNode(data, random.nextInt());
		else{
			insert(data, root);
		}
	}
	
	private void insert(Comparable data, TreapNode node){
		if (data.compareTo(node.value) < 0)
			insert(data, node.leftChild);
	}

	@Override
	public boolean contains(Comparable data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(Comparable data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void successor(Comparable data) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void getMin() {
		// TODO Auto-generated method stub
		
	}
}
