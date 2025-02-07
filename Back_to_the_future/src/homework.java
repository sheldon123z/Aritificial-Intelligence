
import java.util.*;
import java.io.*;
/**
 * A*, UCS, BFS algorithm to search optimal route 
 * @author xiaodongzheng
 *
 */
public class homework{
	
	static int width;
	static int height;
	static Coordinate initial_state;
	static Coordinate target_state;
	static int channelNum;
//	static HashMap<Coordinate,Coordinate> jauntPoint = new HashMap<>();
	static HashMap<Coordinate,HashSet<Coordinate>> jauntPoint = new HashMap<>();
	static boolean find = false;
	/**
	 * main function
	 * @param args
	 */
    public static void main(String[] args){
        
    	
        try {
        	long start = System.currentTimeMillis();
        	String algorithm = "";
            File input = new File("input.txt");
            
            BufferedReader reader = new BufferedReader(new FileReader(input));
            
            //read the algorithm and store
            String parameter = reader.readLine();
            algorithm = parameter.trim();
            
            //read the width and height of the worlds
            parameter = reader.readLine();
            Scanner scanner = new Scanner(parameter);
            width = scanner.nextInt();
            height = scanner.nextInt();
            scanner.close();
            
            //read the initial location 
            parameter = reader.readLine();
            String[] initial = parameter.trim().split(" ");
            
            
            //store the initial location
            initial_state = new Coordinate(Integer.parseInt(initial[1]),Integer.parseInt(initial[2]),
            		Integer.parseInt(initial[0]));
                       
            //read the target location 
            parameter = reader.readLine();
            initial = parameter.trim().split(" ");
           
            //store the initial location
            target_state = new Coordinate(Integer.parseInt(initial[1]),Integer.parseInt(initial[2]),
            		Integer.parseInt(initial[0]));
            //read the channel number
            parameter = reader.readLine();
            channelNum = Integer.parseInt(parameter.trim());
            parameter =reader.readLine();
           
            
            // read all worlds parameter
            while(parameter != null)
            {	
            	String [] data = parameter.split(" ");
            	int year = Integer.parseInt(data[0].trim());
            	int x = Integer.parseInt(data[1].trim());
            	int y = Integer.parseInt(data[2].trim());
            	int jaunt_year = Integer.parseInt(data[3].trim());
            	Coordinate coord_1 = new Coordinate(x,y,year);
            	Coordinate coord_2 = new Coordinate(x,y,jaunt_year);
            	//store the coordinates as a map between two points in a channel
            	
        		HashSet<Coordinate> set = jauntPoint.getOrDefault(coord_1, new HashSet<Coordinate>());
        		set.add(coord_2);
        		jauntPoint.put(coord_1, set);
            	
        		set = jauntPoint.getOrDefault(coord_2, new HashSet<Coordinate>());
        		set.add(coord_1);
        		jauntPoint.put(coord_2, set);
//            	jauntPoint.put(new Coordinate(x,y,year),new Coordinate(x,y,jaunt_year));
//            	jauntPoint.put(new Coordinate(x,y,jaunt_year),new Coordinate(x,y,year));
            	parameter = reader.readLine();
            }
            reader.close();
            
            // output the result 
            outputResult(algorithm);
            long end = System.currentTimeMillis();
            
            System.out.println("if find a route? " + find + "\n");
            System.out.println("time is: " + (end - start) + " ms");
    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e)
        {
            e.printStackTrace();
        } 
        
    }
    /**
     * Output the result to a file
     * @param algorithm
     * @throws IOException
     */
    public static void outputResult(String algorithm) throws IOException
    {
    	//create a new BFS search problem & result
    	Problem problem = new Problem(initial_state, target_state,algorithm);
    	LinkedList<Node> res = new LinkedList<>();
    	
    	//identify which algorithm is used
    	if(algorithm.equals("BFS"))
    		res = btfBFS(problem);
    	else if(algorithm.contentEquals("UCS"))
    		res= btfUCS(problem);
    	else if(algorithm.equals("A*"))
    		res = btfA_Star(problem);
    	
    	//writer for output
    	BufferedWriter output = null;
    	try {
    		//create a new file for output
			File file = new File("output.txt");
			output = new BufferedWriter(new FileWriter(file));
			if(find)
			{      			
				long totalCost = res.peekLast().pathCost;
				output.write(String.valueOf(totalCost)+"\n"+res.size()+"\n");
				for(Node n: res)
				{
					if(n != null)
					{
            			n.coord.printCoordinate();
            			n.printStepCost();
            			System.out.print(" ");
            			n.printPathCost();
            			System.out.println();
            			String s = n.coord.printCoordToString()+ String.valueOf(n.getStepCost()+"\n");
            			output.write(s);
					}                			
				}
			}
			else
			{
				output.write("FAIL");
        	}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally {
				if(output != null)
					output.close();
			}
			output.close();
    }
    
    /**
     * check if the node in valid position
     * @param node
     * @param action
     * @return return true if is, false otherwise
     */
    public static boolean checkIfValidCoord(Node node, int action)
	{
		if(node.coord.x >=0 && node.coord.x < width && node.coord.y >= 0 && node.coord.y < height && action == 0)
			return true;
		if(node.coord.x >=1 && node.coord.x < width && node.coord.y >= 0 && node.coord.y < height && action == 1)
			return true;
		if(node.coord.x >=0 && node.coord.x < width-1 && node.coord.y >= 0 && node.coord.y < height && action == 2)
			return true;
		if(node.coord.x >=0 && node.coord.x < width && node.coord.y >= 1 && node.coord.y < height && action == 3)
			return true;
		if(node.coord.x >=0 && node.coord.x < width && node.coord.y >= 0 && node.coord.y < height-1 && action == 4)
			return true;
		if(node.coord.x >=0 && node.coord.x < width-1 && node.coord.y >= 1 && node.coord.y < height && action == 5)
			return true;
		if(node.coord.x >=1 && node.coord.x < width && node.coord.y >= 1 && node.coord.y < height && action == 6)
			return true;
		if(node.coord.x >=0 && node.coord.x < width-1 && node.coord.y >= 0 && node.coord.y < height-1 && action == 7)
			return true;
		if(node.coord.x >=1 && node.coord.x < width && node.coord.y >= 0 && node.coord.y < height-1 && action == 8)
			return true;
		return false;
	}
	
	/**
	 * produce a child node with suitable parameters
	 * @param problem
	 * @param parent
	 * @param action
	 * @return a new child node
	 */
	public static Node childNode(Problem problem, Node parent, int action)
	{
		Node child;
		Coordinate coord;
		
		// calculate the child coordinate by its parent's coordinate and the action
		coord = problem.inphaseAction(parent, action);
		child = new Node(coord,parent,action,
							parent.pathCost + problem.stepCost(parent,coord,action),
							problem.stepCost(parent,coord, action));
		return child;	
		
	}
	/**
	 * BFS algorithm
	 * @param problem
	 * @return
	 */
	public static LinkedList<Node> btfBFS(Problem problem)
	{
		
		LinkedList<Node> frontier = new LinkedList<>();
		//a hash set is used to check if the coordinate is existed in the frontier
		HashSet<Coordinate> frontierHash = new HashSet<>();
		//explore set is used to store the node that has been expanded 
		HashSet<Coordinate> explored = new HashSet<>();
		//used to store the result
		LinkedList<Node> res = new LinkedList<>();
		Node parent = new Node(problem.initialState,null,0,0,0);
		
		
		if(parent.coord.equals(target_state))
		{
			res.addFirst(parent);
			find = true;
			return res;
		}
		frontier.add(parent);
		frontierHash.add(parent.coord);
		while(!frontier.isEmpty())
		{
			parent = frontier.remove();
			frontierHash.remove(parent.coord);
			explored.add(parent.coord);
			//9 actions, 8 directional move + jaunt
			Queue<Node> children = new LinkedList<>();
			if(jauntPoint.containsKey(parent.coord) )
			{
				HashSet<Coordinate> set = jauntPoint.get(parent.coord);
				for(Coordinate c : set)
				{
					Node child = new Node(c,parent,0,parent.pathCost + 
							problem.stepCost(parent,c, 0),
							problem.stepCost(parent, c, 0));
					children.add(child);
				}
			}
			
			for(int action = 1; action <= 8;action++)
			{
				
				Node child = childNode(problem,parent,action);
				children.add(child);
			}
			
			while(!children.isEmpty())
			{
				Node child = children.poll();
				if(!explored.contains(child.coord) && !frontierHash.contains(child.coord))
				{
					if(problem.goalTest(child.coord))
					{
						find = true;
						return solution(child, res);
					}
					frontier.add(child);
					frontierHash.add(child.coord);
				}
			}
			
				
	
			
		}
		return res;
	}
	/**
	 * the algorithm of UCS
	 * @param problem
	 * @return
	 */
	public static LinkedList<Node> btfUCS(Problem problem) {
		
		//here is different from A*, here used a different comparator to initialize the priority queue
		PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
		HashMap<Coordinate,Node> frontierHash = new HashMap<>();
		HashSet<Coordinate> explored = new HashSet<>();
		LinkedList<Node> res = new LinkedList<>();
		//initialize a node with initial state 
		Node parent = new Node(problem.initialState,null,0,0,0);
		//if the node is target
		if(parent.coord.equals(target_state))
		{
			res.addFirst(parent);
			find = true;
			return res;
		}
		
		frontier.add(parent);
		frontierHash.put(parent.coord,parent);
		while(!frontier.isEmpty())
		{
			parent = frontier.poll();
			frontierHash.remove(parent.coord);
			if(problem.goalTest(parent.coord))
			{
				find = true;
				return solution(parent, res);
			}
			else
			{	
				explored.add(parent.coord);
				/*
				 * the follow part is also correct but it is quicker. we can remove the node which has larger
				 * path cost directly from queue by using a hashmap to track that node
				 * */
				PriorityQueue<Node> children = new PriorityQueue<>(new NodeComparator());
				if(jauntPoint.containsKey(parent.coord))
				{
					HashSet<Coordinate> set = jauntPoint.get(parent.coord);
					for(Coordinate c : set)
					{
						Node child = new Node(c,parent,0,parent.pathCost + 
								problem.stepCost(parent,c, 0),
								problem.stepCost(parent, c, 0));
								
						children.add(child);
					}
					
				}
				
				for(int action = 1; action <= 8;action++)
				{
					Node child = childNode(problem,parent,action);
					children.add(child);
				}
				while(!children.isEmpty())
				{
					//produce a new child node
					Node child = children.poll();
					//if this node is a new node
					if(!explored.contains(child.coord) && !frontierHash.containsKey(child.coord))
					{
						frontier.add(child);
						frontierHash.put(child.coord,child);
					}
					//replace the node has same coordinate with child and add child to the queue
					else if(frontierHash.containsKey(child.coord))
					{
						Node n = frontierHash.get(child.coord);
						if((n.pathCost > child.pathCost))
						{
							frontier.remove(n);
							frontier.remove(n);
							frontier.add(child);
							frontierHash.put(child.coord, child);
						}	
					}
					
				}
			}
		}
		return res;
}
	/**
	 * A* search algorithm
	 * it is identical to UCS except the priority queue is ordered by g+h rather than g
	 * @param problem
	 * @return
	 */
	public static LinkedList<Node> btfA_Star(Problem problem) {
			//here is different from UCS, here used a different comparator to initialize the priority queue
			PriorityQueue<Node> frontier = new PriorityQueue<>(new AStarNodeComparator());
			HashMap<Coordinate,Node> frontierHash = new HashMap<>();
			HashSet<Coordinate> explored = new HashSet<>();
			HashMap<Coordinate,Node> exploredHash = new HashMap<>();
			LinkedList<Node> res = new LinkedList<>();
			//initialize a node with initial state 
			Node parent = new Node(problem.initialState,null,0,0,0);
			//if the node is target
			if(parent.coord.equals(target_state))
			{
				res.addFirst(parent);
				find = true;
				return res;
			}
			
			frontier.add(parent);
			frontierHash.put(parent.coord,parent);
			while(!frontier.isEmpty())
			{
				parent = frontier.poll();
				frontierHash.remove(parent.coord);
				if(problem.goalTest(parent.coord))
				{
					find = true;
					return solution(parent, res);
				}
				else
				{	
					explored.add(parent.coord);
					exploredHash.put(parent.coord, parent);
					/*
					 * this part is different logic but same implementation
					 */
//					PriorityQueue<Node> children = new PriorityQueue<>(new AStarNodeComparator());
//					for(int action = 1; action <= 8;action++)
//						{
//							children.add(childNode(problem,parent,action));
//						}
//					while(!children.isEmpty())
//					{
//						Node child = children.poll();
//						//h is the heuristic of the node 
//						int h = Problem.heuristic(child);
//						//if the node is new then add it into frontier
//						if(!explored.contains(child.coord) && !frontierHash.containsKey(child.coord))
//						{
//							frontier.add(child);
//							frontierHash.put(child.coord,child);
//						}
//						/*if the node has been visited then check if it has lower path cost*/
//						else if(frontierHash.containsKey(child.coord))
//						{
//							Node n = frontierHash.get(child.coord);
//							// h is calculated by coordinate attribute so child and the n nodes have
//							//same heuristic
//							if(((n.pathCost + h) > (child.pathCost + h)))
//							{
//								frontier.remove(n);
//								frontier.add(child);
//								frontierHash.put(child.coord, child);
//							}
//						}
//						/*if child is explored check if it has lower path cost
//						if it has lower path cost, remove it from explored and add it to frontier
//						/the next outer loop will expand this node and add it into explored again*/
//						else if(explored.contains(child.coord))
//						{
//							Node n =  exploredHash.get(child.coord);
//							if(((n.pathCost + h) > (child.pathCost + h)))
//							{
//								explored.remove(child.coord);
//								exploredHash.remove(child.coord);
//								frontier.add(child);
//								frontierHash.put(child.coord, child);
//							}
//						}
//					}
					
					
					PriorityQueue<Node> children = new PriorityQueue<>(new AStarNodeComparator());
					if(jauntPoint.containsKey(parent.coord))
					{
						HashSet<Coordinate> set = jauntPoint.get(parent.coord);
						for(Coordinate c : set)
						{
							Node child = new Node(c,parent,0,parent.pathCost + 
									problem.stepCost(parent,c, 0),
									problem.stepCost(parent, c, 0));
							children.add(child);
						}
						
					}
					for(int action = 1; action <= 8;action++)
						{
							Node child = childNode(problem,parent,action);
							children.add(child);
						}
					/*
					 * the follow part is also correct but it is quicker. we can remove the node which has larger
					 * path cost directly from queue by using a hashmap to track that node
					 * */
					while(!children.isEmpty()) 
					{
						//produce a new child node
						Node child = children.poll();
						int h = Problem.heuristic(child);
						//if this node is a new node
						if(!explored.contains(child.coord) && !frontierHash.containsKey(child.coord))
						{
							frontier.add(child);
							frontierHash.put(child.coord,child);
						}
						//replace the node has same coordinate with child and add child to the queue
						else if(frontierHash.containsKey(child.coord))
						{
							Node n = frontierHash.get(child.coord);
							
							if(((n.pathCost + h) > (child.pathCost + h)))
							{
								frontier.remove(n);
								frontier.remove(n);
								frontier.add(child);
								frontierHash.put(child.coord, child);
							}	
						}
						
					}
				}
			}
			return res;
	}
	/**
	 * Return solution for BFS algorithm
	 * @param child the node that confirm to the target state
	 * @param res result list
	 * @return
	 */
	public static LinkedList<Node> solution(Node child, LinkedList<Node> res){
		res.add(child);
		while(child.parent != null )
		{	
			child = child.parent;
			res.addFirst(child);
			if(child.coord.equals(initial_state))
			{
				return res;
			}
				
		}
		return res;
	}

}

