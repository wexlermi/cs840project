import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutput;


public class JumpList  implements Set {
	static Random random = new Random();
	public JumpListNode headNode;
	private JumpListNode tailNode;
	private int length;
	
	private static class JumpListNode {
		Comparable value;
		JumpListNode nextNode;
		JumpListNode jumpToNode;
		/*boolean isHead;
		boolean isTail;*/
		int ncount;
		int jcount;

		public JumpListNode(Comparable value){
			this.value = value;
			this.nextNode = null;
			this.jumpToNode = null;
		}

		public JumpListNode(){
			this.nextNode = null;
			this.jumpToNode = null;
		}
	}

	public JumpList()
	{
		this.length = 0;
		this.headNode = new JumpListNode();
	}

	private JumpList(Comparable value)
	{
		this.length = 1;
		this.headNode = new JumpListNode(value);
	}

	public static void main(String args[]) throws IOException 
	{
		Scanner in = new Scanner(new FileReader("testdata.txt"));
		List<Long> numberList = new ArrayList<Long>();
		while (in.hasNextLong()) {
			long num = in.nextLong();
			numberList.add(num);
		}
		JumpList jl = new JumpList();
		try {
			for (int i = 0; i < numberList.size(); i++)
			{
				System.out.println("Inserting item " + (i + 1) + ":   " + numberList.get(i));
				jl.insert(numberList.get(i));
			}
		}
		catch (Exception e)
		{
			System.err.println("Exception: " + e.getMessage());
		}
		try {
			jl.print_list();
			System.out.println("numberList size is " + numberList.size() + " 	numberList[-1] = " + numberList.get(numberList.size()-1));
			System.out.println("jumplist size is " + jl.getLength() + " 	jumplist[0] = " + jl.getHeader().value);
			System.out.println("jumplist[1] = " + jl.getHeader().nextNode.value);
			int numOfFound = 0;
			for (int i = 0; i < numberList.size(); i++)
			{
				if (jl.contains(numberList.get(i)))
				{
					System.out.println("Found Item: " + numberList.get(i));
					numOfFound++;
				}
				else
					System.out.println("Not Found item " + (i + 1) + ":   " + numberList.get(i));//jl.contains(numberList.get(i)));
			}
			System.out.println("Total number of found items: " + numOfFound);
		}
		catch (Exception e)
		{
			System.err.println("Exception: " + e.getMessage());
		}
		System.out.println("Orignal list size: " + numberList.size());	
	}

	private void init_insert(Comparable data)
	{
		this.headNode.value = data;
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

	private JumpListNode build_perfect_jumplist(JumpListNode x, int n){
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

	private void dj_insert(Comparable data){
		JumpListNode x = this.headNode;
		JumpListNode y = new JumpListNode(data);

		while (x.jumpToNode != x)
		{			
			double alpha = 0.4;	//0 < alpha < 0.5; alpha could be 1 - 1/sqrt(2)
			double temp = (double)(x.ncount + 1)/(2 + x.ncount + x.jcount);
			if ((1 - alpha) < temp && temp < alpha){
				System.out.println("Calling build_perfect_jumplist()...........");
				this.build_perfect_jumplist(x, (1 + x.ncount + x.jcount));
			}
			if (x.jumpToNode != null && x.jumpToNode.value.compareTo(data) <= 0)
			{
				x.jcount++;
				x = x.jumpToNode;
			}
			else
			{
				x.ncount++;
				if (x.nextNode != null && x.nextNode.value.compareTo(data) <= 0)
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

		if (this.length == 1)
			return (x.value.compareTo(data) == 0);

		while (x.jumpToNode != x)
		{
			if (x.jumpToNode != null && x.jumpToNode.value.compareTo(data) < 0){
				x = x.jumpToNode;
			}
			else if (x.nextNode != null && x.nextNode.value.compareTo(data) < 0){
				x = x.nextNode;
			}
			else {
				return (x.nextNode != null ? x.nextNode.value.compareTo(data) == 0 : false);
			}
		}

		return (x.nextNode != null ? x.nextNode.value.compareTo(data) == 0 : false);
	}

	public void delete(Comparable data)
	{
		if (!this.contains(data))
		{
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
	@Override
	public void insert(Comparable data) {
		if (this.headNode.value == null){
			init_insert(data);
		}
		this.dj_insert(data);
	}

	private int getLength() 
	{
		return this.length;
	}

	private JumpListNode getHeader()
	{
		return this.headNode;
	}
}
