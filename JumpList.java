import java.util.Random;
import java.util.LinkedList;

public class JumpList extends LinkedList implements OrderedSet {
	public JumpList()
	{
		super();
		for (int i = 0; i < 10; i++)
			this.add(i);
	}

	public static void main(String args[])
	{
		JumpList jl = new JumpList();
		System.out.println(jl);
	}

	public void insert(Comparable data){

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