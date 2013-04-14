import java.util.Random;
import java.util.Arrays;

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
		/*for (int i = 20; i >= 1; i--)
			jl.init_insert(i);
		*/
		int oList[] = new int[10];
		for (int i = oList.length-1; i >= 0; i--)
		{
			int randomNum = (int) (Math.random() * 100);
			while (Arrays.binarySearch(oList, randomNum) >= 0)
				randomNum = (int) (Math.random() * 100);
			oList[i] = randomNum;
		}
		Arrays.sort(oList);
		for (int i = oList.length-1; i >= 0; i--)
			jl.init_insert(oList[i]);
		
		//build up the jump links here
		jl.build_perfect_jumplist(jl.headNode, jl.length);
		jl.print_list();

		// test search method here
		int tItems[] = new int[]{2, 7, 13, 21}; 
		for (int i = 0; i < tItems.length; i++){
			int target_item = tItems[i];
			System.out.println("The jl contains " + target_item + ": " + jl.contains(target_item));
		}

		// test insert method here
		/*int tItems[] = new int[]{21};//, 7, 13, 21}; 
		for (int i = 0; i < tItems.length; i++){
			int target_item = tItems[i];
			jl.insert(target_item);
			System.out.println("Inserting: " + target_item + "\n");
		}
		jl.print_list();
		*/

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
		}
	}

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
		if (this.contains(data))
		{
			System.out.println("Target Item: " + data + " is in the jumplist already.");
			return;
		}

		JumpListNode x = this.headNode;
		JumpListNode y = new JumpListNode(data);

		while (x.jumpToNode != x)
		{
			double alpha = 0.40;	//0 < alpha < 0.5; alpha could be 1 - 1/sqrt(2)
			double temp = (double)(x.ncount + 1)/(2 + x.ncount + x.jcount);
			if ((1 - alpha) < temp && temp < alpha){
				this.build_perfect_jumplist(x, (1 + x.ncount + x.jcount));
			}
			if (x.jumpToNode.value.compareTo(data) <= 0)
			{
				x.jcount++;
				x = x.jumpToNode;
			}
			else
			{
				x.ncount++;
				if (x.nextNode.value.compareTo(data) <= 0)
					x = x.nextNode;
				else
					break;
			}
		}

		y.nextNode = x.nextNode;
		x.nextNode = y;

		if (x.jumpToNode == x){
			x.jumpToNode = y;
			x.jcount = 1;
		}
		else if (x.jumpToNode != y.nextNode){
			while (y.ncount > 1){
				y.jumpToNode = y.nextNode.jumpToNode;
				y.jcount = y.nextNode.jcount;
				y.ncount = y.nextNode.ncount + 1;
				y = y.nextNode;
			}
			if (y.jumpToNode == y.nextNode){
				y.jumpToNode = y;
				y.jcount = 0;
			}
			else
			{
				y.jumpToNode = y.nextNode;
				y.jcount = 1;
				y.ncount = 0;
			}
		}
		this.length++;
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
		if (!this.contains(data))
		{
			System.out.println("Deleted Item: " + data + " is not in the list.");
			return;
		}

		JumpListNode x = this.headNode;
		JumpListNode s = new JumpListNode();
		while (x.value.compareTo(data) != 0)
		{
			s = x;

			double alpha = 0.40;	//0 < alpha < 0.5; alpha could be 1 - 1/sqrt(2)
			double temp = (double)(x.ncount + 1)/(2 + x.ncount + x.jcount);
			if ((1 - alpha) < temp && temp < alpha){
				this.build_perfect_jumplist(x, (1 + x.ncount + x.jcount));
			}
			if (x.jumpToNode.value.compareTo(data) <= 0)
			{
				x.jcount--;
				x = x.jumpToNode;
			}
			else if (x.nextNode.value.compareTo(data) <= 0)
			{
				x.ncount--;
				x = x.nextNode;
			}
		}
		if (s.jumpToNode == x)
		{
			if (x.jumpToNode == x)
			{
				if (s.nextNode == x)
					s.jumpToNode = s;
				else
					s.jumpToNode = s.nextNode;

				s.jcount = s.ncount;
				s.ncount = 0;
			}
			else
				s.jumpToNode = x.nextNode;

			if (s.nextNode != x)
			{
				s = s.nextNode;
				while (s.jumpToNode != s)
					s = s.jumpToNode;
			}

			s.nextNode = x.nextNode;
			if (x.ncount != 0)
			{
				JumpListNode y = x.nextNode;
				JumpListNode t1 = x.jumpToNode;
				int jc1 = x.jcount;

				while (y.ncount != 0)
				{
					JumpListNode t2 = y.jumpToNode;
					int jc2 = y.jcount;

					y.jumpToNode = t1;
					y.ncount = y.ncount + jc2;
					y.jcount = jc1;
					t1 = t2;
					jc1 = jc2;
					y = y.nextNode;
				}
				y.jumpToNode = t1;
				y.ncount = y.jcount;
				y.jcount = jc1;
			}
			x = null;
		}
	}

	public void successor(Comparable data)
	{

	}

	public void getMin(){

	}
}