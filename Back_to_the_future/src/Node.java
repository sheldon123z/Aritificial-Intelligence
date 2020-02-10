
/**
 * basic Node class, each node records a path state
 * @author xiaodongzheng
 *
 */
class Node{
	public Coordinate coord;
    public Node parent;
    public int action;
    public int pathCost;
    public int stepCost;
    
    public Node (int x, int y,int year)
    {
    	this.coord = new Coordinate(x,y,year); 
    }
    public Node(Coordinate coord) {
    	this.coord = coord;
    }
    public Node(Coordinate coord, Node parent) {
    	this.coord = coord;
    	this.parent = parent;
    }
    public Node(Coordinate coord, Node parent, int action, int pathCost, int stepCost) {
    	this.parent = parent;
    	this.action = action;
    	this.pathCost = pathCost;
    	this.coord = coord;
    	this.stepCost = stepCost;
    }
    /**
     * print step cost to console
     */
    public void printStepCost()
    {
    	System.out.print(this.stepCost+" ");
    }
    /**
     * print path cost to console
     */
    public void printPathCost()
    {
    	System.out.print(this.pathCost);
    }
    /**
     * get path cost
     * @return
     */
    public int getPathCost()
    {
    	return this.pathCost;
    }
    /**
     * get step cost
     * @return
     */
    public int getStepCost()
    {
    	return this.stepCost;
    }
    @Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if (getClass() != o.getClass()) {
            return false;
        }
        final Node other = (Node)o;
        if(!this.coord.equals(other.coord))
    	{
    		return false;
    	}
        return true;		
       }
	@Override
	 public int hashCode() {
        return this.coord.hashCode();
    }
}