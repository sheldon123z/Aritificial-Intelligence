
public class MineFieldTester {

	public static void main(String[] args) {
		
		 boolean[][] smallMineField = 
		      {{false, false, false, false}, 
		       {false, false, false, false}, 
		       {false, true, true, false},
		       {false, true, false, false}};
		   
		MineField mineField = new MineField(smallMineField);
		MineField mineField2 = new MineField(10,10,6);
		//
		System.out.println("minefield mine numbers: "+mineField.numMines() + 
							"\nnumber of rows: "+ mineField.numRows()+"\nnum of cols: "+mineField.numCols());
		
		
		for(int i=0;i<mineField.numRows();i++)
		{
			System.out.println();
			for(int j = 0; j<mineField.numCols();j++)
			{
				System.out.print(mineField.numAdjacentMines(i, j) + " ");
			}
		}

	}

}
