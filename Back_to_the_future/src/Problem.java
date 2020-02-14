
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
	 * action move a node to a position in the same world
	 * action 1:south, 2:north, 3:west, 4:east, 5:south west, 6:north west, 7:south east, 8 north east
	 * @param node
	 * @param action
	 * @return
	 */
	public Coordinate inphaseAction(Node node, int action)
	{
		Coordinate coord;
		if(homework.checkIfValidCoord(node, action))
		{
			switch(action) {
			//jaunt if jaunt, the child node's coordinate is same as its child
			//or go west
			//if x=some channel's x y= some channel's y then year should be the other jaunt year
			case 1:
				coord = new Coordinate(node.coord.x-1 ,node.coord.y, node.coord.year);
			break;
			//east
			case 2: 
				coord = new Coordinate(node.coord.x+1 ,node.coord.y, node.coord.year);
			break;
			//north
			case 3: coord = new Coordinate(node.coord.x ,node.coord.y-1, node.coord.year);
			break;
			//south
			case 4: coord = new Coordinate(node.coord.x ,node.coord.y+1, node.coord.year);
			break;
			//moveNorthEast
			case 5: coord = new Coordinate(node.coord.x+1 ,node.coord.y-1, node.coord.year);
			break;
			//moveNorthWest
			case 6:coord = new Coordinate(node.coord.x-1 ,node.coord.y-1, node.coord.year);
			break;
			//moveSouthEast
			case 7:coord = new Coordinate(node.coord.x+1 ,node.coord.y+1, node.coord.year);
			break;
			//moveSouthWest
			case 8:coord = new Coordinate(node.coord.x-1 ,node.coord.y+1, node.coord.year);
			break;
			default:
				coord = new Coordinate(node.coord.x ,node.coord.y, node.coord.year);
			}
		
		}
		else
			coord = new Coordinate(node.coord.x ,node.coord.y, node.coord.year);
		
		return coord;
		
	}
	/**
	 * calculate the stepCost for each node
	 * @param parent
	 * @param action
	 * @return
	 */
	public int stepCost(Node parent, Coordinate coord, int action)
	{
		int stepCost = 0;
		if(this.algo.equals("BFS"))
		{
			return 1;
		}
		if(this.algo.equals("UCS")||this.algo.equals("A*"))
		{
			if(action <= 8 && action > 4)
			{
				stepCost = 14;
			}
			else if(action<=4 && action > 1)
			{
				stepCost = 10;
			}
			//action ==0 mean jaunt or go south
			else if(action==0 && coord.year != parent.coord.year)
			{
				stepCost = Math.abs(coord.year - parent.coord.year);
			}
			else
				stepCost = 10;
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
		if(state.equals(this.targetState))
			return true;
		return false;
	}
	
	
	
}