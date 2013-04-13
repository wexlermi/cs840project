import java.util.Random;
public class Treap implements OrderedSet {
	TreapNode root;
	static Random random = new Random();
	private static class TreapNode {
		int value;
		int priority;
		TreapNode leftChild;
		TreapNode rightChild;
		TreapNode parent;
		public TreapNode(int value, int priority){
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
	
	public void insert(int data) {
		if (root == null)
			root = new TreapNode(data, random.nextInt());
		else{
			TreapNode treapNodeToInsert = new TreapNode(data, random.nextInt());
			TreapNode ptr = root;
			while (true){
				if (data < ptr.value){
					if (ptr.leftChild == null){
						ptr.leftChild = treapNodeToInsert;
						break;
					}
					else
						ptr = ptr.leftChild;
				}
				else if (data > ptr.value) {
					if (ptr.rightChild == null){
						ptr.rightChild = treapNodeToInsert;
						break;
					}
					else
						ptr = ptr.rightChild;
				}
			}
		}
		
	}

	@Override
	public boolean contains(int data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(int data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void successor(int data) {
		// TODO Auto-generated method stub
		
	}
}
