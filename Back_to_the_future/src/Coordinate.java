class Coordinate{
	
	public int x;
	public int y;
	public int year;

	
	public Coordinate(int x, int y, int year)
	{
		this.x = x;
		this.y = y;
		this.year = year;

	}
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if (getClass() != o.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate)o;
        if((this.x != other.x) || (this.y != other.y) || (this.year != other.year))
    	{
    		return false;
    	}
        return true;		
       }
	@Override
	 public int hashCode() {
        return this.x + 19 * this.y + 23 * this.year + 11;
    }
//	public void setX(int value)
//	{
//		this.x = value;
//	}
//	public void setY(int value)
//	{
//		this.y = value;
//	}
//	public void setYear(int value)
//	{
//		this.year = value;
//	}
	
	 public void printCoordinate()
	 {
		 System.out.print(this.year+" " + this.x+ " " + this.y+ " ");
	 }
	 public String printCoordToString()
	 {
		 return new String(String.valueOf(this.year)+ " " + String.valueOf(this.x)+" "+ 
				 			String.valueOf(this.y)+" ");
	 }
}
