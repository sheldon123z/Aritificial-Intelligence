import java.util.Comparator;
/**
 * comparator for priority queue for UCS
 * @author xiaodongzheng
 *
 */
public class NodeComparator implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		if(o1.pathCost < o2.pathCost)
			return -1;
		else if(o1.pathCost > o2.pathCost)
			return 1;
		return 0;
	}

}
