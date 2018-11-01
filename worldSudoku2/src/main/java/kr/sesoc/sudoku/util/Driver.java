package kr.sesoc.sudoku.util;

public class Driver
{
	public static void main(String[] args)
	{
		SudokuGenerator sg = new SudokuGenerator();
		SudokuSolver ss = new SudokuSolver();
		int numSolved = 0;
		int[][] copyArray = new int[9][9];
		for(int i=0;i<1000;i++)
		{
			ss.reset(sg.nextBoard(54));
			for(int j=0; j<9; j++) {
				for(int z=0; z<9; z++) {
					copyArray[j][z] = ss.getBoard()[j][z];
				}
			}
			if(ss.solve())
			{
				numSolved++;
				ss.print();
				break;
			}
		}
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(copyArray[i][j]);
			}
			System.out.println("");
		}
		System.out.println(numSolved);
		//ss.print();
//		SudokuChecker sudoku = new SudokuChecker( ss.board );
//		if( sudoku.checkPuzzle() )
//		{
//		  System.out.println("There are no rule violations");
//		}
//		if( sudoku.completed() )
//		{
//		  System.out.println("Puzzle is done");
//		}
//		else
//		{
//		  System.out.println("Puzzle is NOT done");
//		}

	}
}