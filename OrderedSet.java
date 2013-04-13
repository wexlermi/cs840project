
public interface OrderedSet {
	public void insert(Comparable data);
	public boolean contains(Comparable data);
	public void delete(Comparable data);
	public void successor(Comparable data);
	public void getMin();
}
