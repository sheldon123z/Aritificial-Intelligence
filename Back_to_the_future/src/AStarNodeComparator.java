import java.util.Comparator;
/**
 * used to initialize the priority queue
 * @author xiaodongzheng
 *
 */
public class AStarNodeComparator implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		if(o1.pathCost + Problem.heuristic(o1) < o2.pathCost +Problem.heuristic(o2))
			return -1;
		else if((o1.pathCost + Problem.heuristic(o1)) > (o2.pathCost + Problem.heuristic(o2)))
			return 1;
		return 0;
	}

}
