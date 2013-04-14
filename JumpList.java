import java.util.Random;

public class JumpList  implements OrderedSet {
	public JumpListNode headNode;
	private JumpListNode tailNode;
	private int length;

	public JumpList()
	{
		this.length = 0;
		this.headNode = null;
		//this.tailNode = null;
	}

	public JumpList(Comparable value)
	{
		this.length = 1;
		this.headNode = new JumpListNode(value);
		//this.tailNode = null;
	}

	public static void main(String args[])
	{
		JumpList jl = new JumpList();
		//System.out.println(jl);
		for (int i = 10; i >= 1; i--)
			jl.init_insert(i);
		
		jl.build_perfect_jumplist(jl.headNode,10);
		jl.print_list();
	}

	private void init_insert(Comparable data)
	{
		JumpListNode newNode = new JumpListNode(data);
		newNode.nextNode = this.headNode;
		headNode = newNode;
	}

	public void print_list(){
		JumpListNode currNode = this.headNode;
		while (currNode != null && currNode.value.compareTo(10) <= 0) {			
			System.out.println(currNode.value + ":");
			JumpListNode nextNode = currNode.jumpToNode;
			while (nextNode != null){				
				System.out.print("	" + nextNode.value);
				nextNode = nextNode.jumpToNode;
			}
			System.out.println();
			currNode = currNode.nextNode;
		}
		//System.out.println();
	}

	/*public void build_jumplist(int headIndex, int tailIndex){
		int numOfItems = tailIndex - headIndex + 1;

		if (numOfItems == 1)
			return;
	}*/

	public JumpListNode build_perfect_jumplist(JumpListNode x, int n){
		while (n > 1)
		{
			int m = (n - 1) / 2;
			n = n - m - 1;
			x.ncount = m;
			x.jcount = n;
			JumpListNode y = x.nextNode;
			if (m == 0)
				x.jumpToNode = y;
			else
				x.jumpToNode = this.build_perfect_jumplist(y, m);
			x = x.jumpToNode;
		}
		x.jumpToNode = x;
		x.ncount = 0;
		x.jcount = 0;
		return x.nextNode;
	}

	public void insert(Comparable data){
		/*if (this.length == 0)
		{
			this.length = 1;
			this.headNode = new JumpListNode(data);
		}
		else if (this.length == 1){
			if (this.headNode.value.compareTo(data) < 0) {
				this.headNode.nextNode = new JumpListNode(data);
			}
			else if (this.headNode.value.compareTo(data) > 0){
				JumpListNode newNode = new JumpListNode(data);
				JumpListNode oldNode = this.headNode;
				this.headNode = newNode;
				this.headNode.nextNode = oldNode;
			}
			else {
				System.out.println("JumpList has only one element and head value = inserted value.");
			}
		}
		else {
			JumpListNode prevNode = null;
			JumpListNode currNode = this.headNode;
			while (currNode != null && currNode.value.compareTo(data) < 0){
				prevNode = currNode;
				currNode = currNode.nextNode;
			}
			currNode = new JumpListNode(data);
			prevNode.nextNode = currNode;
		}*/

		/*JumpListNode currNode = this.headNode;
		if (currNode.value < data) {
			JumpListNode newNode = new JumpListNode(data);
			
		}*/
		
	}

	public boolean contains(Comparable data)
	{
			return false;
	}

	public void delete(Comparable data)
	{

	}

	public void successor(Comparable data)
	{

	}

	public void getMin(){

	}
}