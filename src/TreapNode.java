import java.util.Random;

public class TreapNode {
	int value;
	int priority;
	TreapNode leftChild;
	TreapNode rightChild;
	TreapNode parent;
	static Random random = new Random();
	
	public TreapNode(int value){
		this.value = value;
		this.priority = random.nextInt();
	}
	
}
