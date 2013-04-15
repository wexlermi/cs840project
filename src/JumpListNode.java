public class JumpListNode {
	Comparable value;
	JumpListNode nextNode;
	JumpListNode jumpToNode;
	/*boolean isHead;
	boolean isTail;*/
	int ncount;
	int jcount;

	public JumpListNode(Comparable value){
		this.value = value;
		/*this.isHead = false;
		this.isTail = false;*/
		this.nextNode = null;
		this.jumpToNode = null;
	}

	public JumpListNode(){
		this.nextNode = null;
		this.jumpToNode = null;
	}
}