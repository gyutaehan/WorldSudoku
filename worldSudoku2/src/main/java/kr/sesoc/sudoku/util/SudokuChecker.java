package kr.sesoc.sudoku.util;

/**
 *스도쿠 퍼즐이 올바른지 확인하는 클래스
 */
public class SudokuChecker
{
	/**
	 * 퍼즐 초기화
	 *@param input 9 * 9 스도쿠 퍼즐
	 */
	public SudokuChecker( int[][] input )
	{
	  puzzle = input;
	}
	
	/**
	 * 각 위치에 1-9가 모두 있는지 확
	 *@return true :: 모두 있음 false :: 부족함
	 */
	public boolean completed()
	{
		for(int i = 0; i < 9;i++)
		{
			for(int j = 0;j < 9;j++)
			{
				if(puzzle[i][j] > 9 || puzzle[i][j] < 1)
				{
					return false;
				}
			}
		}
		return true;
	}
	   
	public boolean checkPuzzle()
	{
		for(int i = 0; i < 9;i++)
		{
			if(!checkRow(i))
			{
				return false;
			}
		}
		 
		for(int i = 0; i < 9;i++)
		{
			if(!checkColumn(i))
			{
				return false;
			}
		}
		for(int i = 0; i < 9;i += 3)
		{
		 	for(int j = 0; j < 9;j += 3)
			{
				if(!checkSquare(i, j))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 행 확인
	 *@param r 확인할 행
	 *@return  true :: 올바름 false :: 잘못됨
	 */
	public boolean checkRow( int r )
	{
	   resetCheck();
	   for( int c=0; c<9; c++ )
	   {
	    	if( !digitCheck( puzzle[r][c] ) )
	    	{
	    		return false;
	   	}
	   }
		return true;
	}
	
	/**
	 * 열 확인
	 *@param c 확인할 열
	 *@return  true :: 올바름 false :: 잘못됨
	 */
	public boolean checkColumn( int c )
	{
		resetCheck();
		for( int r=0; r<9; r++ )
		{
			if( !digitCheck( puzzle[r][c] ) )
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * 3*3 사각형 확인
	 *@param row    사각형의 행 
	 *@param column 사각형의 열
	 *@return       true :: 올바름 false :: 잘못됨
	 */
	public boolean checkSquare( int row, int column )
	{
		resetCheck();
		for(int r = 0; r < 3;r++)
		{
			for(int c = 0; c < 3;c++)
			{
				if( !digitCheck( puzzle[r + row][c + column] ) )
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 체크중에 사용된 번호를 추적
	 *@param n 현재 확인하는 번호
	 *@return  true :: 아직 숫자가 사용되지 않음 false :: 사용됨
	 */
	public boolean digitCheck( int n )
	{
		if( n != 0 && digits[n] )
		{
			return false;
		}
		else
		{
			digits[n] = true;
			return true;
		} 
	}

	public void resetCheck()
	{
		digits = new boolean[10];
	}
	  
	int[][] puzzle;
	boolean[] digits;
	  
	
}