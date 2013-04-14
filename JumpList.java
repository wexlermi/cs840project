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
		////Inititalize a jump list here
		JumpList jl = new JumpList();
		for (int i = 20; i >= 1; i--)
			jl.init_insert(i);
		
		//build up the jump links here
		jl.build_perfect_jumplist(jl.headNode, jl.length);
		jl.print_list();

		// test search method here
		int tItems[] = new int[]{2, 7, 13, 21}; 
		for (int i = 0; i < tItems.length; i++){
			int target_item = tItems[i];
			System.out.println("The jl contains " + target_item + ": " + jl.contains(target_item));
		}
	}

	private void init_insert(Comparable data)
	{
		JumpListNode newNode = new JumpListNode(data);
		newNode.nextNode = this.headNode;
		headNode = newNode;
		this.length++;
	}

	public void print_list(){
		JumpListNode currNode = this.headNode;
		for (int i = 0; i < this.length; i++){
			System.out.print(i + 1 + ". Current Node: " + currNode.value + 
				" -------- Next to Node: " + (currNode.nextNode != null ? currNode.nextNode.value : "no next Node.") +
				" -------- Jump to Node: " + (currNode.jumpToNode != null ? currNode.jumpToNode.value : "no jump Node.") + "\n");
			currNode = currNode.nextNode;
			//System.out.println(currNode.jumpToNode.value);	
		}
		
		/*
		while (currNode != null && currNode.value.compareTo(11) < 0) {			
			System.out.println(currNode.value + ":");
			JumpListNode nextNode = currNode.jumpToNode;
			while (nextNode != null){				
				System.out.print("	" + nextNode.value);
				nextNode = nextNode.jumpToNode;
			}
			System.out.println();
			currNode = currNode.nextNode;
		}*/
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
		JumpListNode x = this.headNode;

		if (x != null && x.value.compareTo(data) == 0)
			return true;

		while (x.jumpToNode != x)
		{
			if (x.jumpToNode.value.compareTo(data) < 0){
				x = x.jumpToNode;
			}
			else if (x.nextNode.value.compareTo(data) < 0){
				x = x.nextNode;
			}
			else {
				return x.nextNode.value.compareTo(data) == 0;
			}
		}

		return (x.nextNode != null ? x.nextNode.value.compareTo(data) == 0 : false);
		//return false;
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