
public class Problem{
	
	Coordinate initialState;
	Coordinate targetState;
	String algo;
	int jauntPoint;
	
	/**
	 * function builder
	 * @param initialState
	 * @param targetState
	 * @param algo
	 */
	public Problem(Coordinate initialState,Coordinate targetState, String algo) {
		
		this.initialState = initialState;
		this.targetState = targetState;
		//this.jauntPoint = jauntPoint;
		this.algo = algo;
	}
	
	
	/**
	 * calculate the stepCost for each node
	 * @param parent
	 * @param action
	 * @return
	 */
	public int stepCost(Node parent, int action)
	{
		int stepCost = 0;
		if(this.algo.equals("BFS"))
		{
			return 1;
		}
		if(this.algo.equals("UCS")||this.algo.equals("A*"))
		{
			if((action % 2) != 0)
			{
				stepCost = 10;
			}
			else if((action % 2) ==0 )
			{
				stepCost = 14;
			}
		}
		return stepCost;
			
	}
	/**
	 * calculate the heuristic for A*
	 * the h(n) = 14 * Math.max(d_x,d_y) - 4 * (Math.max(d_x, d_y) - Math.min(d_x, d_y)) +years jaunted
	 * for same world, the years jaunted is 0
	 * @param node
	 * @param action
	 * @return
	 */
	static public int heuristic(Node node)
	{
		int h = 0;
		int d_x = homework.target_state.x - node.coord.x;
		int d_y = homework.target_state.y - node.coord.y;
		int d_year = homework.target_state.year - node.coord.year;
		
		//if the action is jaunt
		//calculate heuristic 
		if(d_x == d_y)
			h = 14 * d_x + d_year;
		else
			h = 14 * Math.max(d_x,d_y) - 4 * (Math.max(d_x, d_y) - Math.min(d_x, d_y)) + d_year;
					
		return h;	
	}
	/**
	 * goal test function used to test if the goal is accomplished
	 * @param state
	 * @return if the state is exactly same with the target_state return true, otherwise return false
	 */
	public boolean goalTest(Coordinate state) {
		if(state==null)
		{
			System.out.println("null");
		}
			
		if(state.equals(this.targetState))
			return true;
		return false;
	}
	
	
	
}