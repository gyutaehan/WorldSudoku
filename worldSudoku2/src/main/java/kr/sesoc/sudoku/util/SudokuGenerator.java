package kr.sesoc.sudoku.util;

import java.util.*;
import java.text.*;
/**
* 9*9 퍼즐 생성기 (Backtracking Algorithm)
*/
public class SudokuGenerator
{
	public static final int BOARD_WIDTH = 9;
   public static final int BOARD_HEIGHT = 9;
	
	/**
	 * board 초기화
	 */
   public SudokuGenerator() {
   	board = new int[BOARD_WIDTH][BOARD_HEIGHT];
   }
	
	/**
	 *@param  난이도 정도
	 *@return 부분적으로 완료된 9*9 퍼즐
	 */
	public int[][] nextBoard(int difficulty)
	{
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];
		nextCell(0,0);
		makeHoles(difficulty);
		return board;

	}
	
	/**
	 *모든 셀에 숫자를 배치하는 메서드
	 *
	 *@param x 현재 셀의 x값
	 *@param y 현재 셀의 y값
	 *@return  true : 보드가 만들어졌을 때, false : 보드 해결방법이 없을 때
	 */
	public boolean nextCell(int x, int y)
	{
		int nextX = x;
		int nextY = y;
		int[] toCheck = {1,2,3,4,5,6,7,8,9};
		Random r = new Random();
		int tmp = 0;
		int current = 0;
		int top = toCheck.length;

   		for(int i=top-1;i>0;i--)
		{
		    current = r.nextInt(i);
		    tmp = toCheck[current];
		    toCheck[current] = toCheck[i];
		    toCheck[i] = tmp;
    	}
		
		for(int i=0;i<toCheck.length;i++)
		{
			if(legalMove(x, y, toCheck[i]))
			{
				board[x][y] = toCheck[i];
				if(x == 8)
				{
					if(y == 8)
						return true;
					else
					{
						nextX = 0;
						nextY = y + 1;
					}
				}
				else
				{
					nextX = x + 1;
				}
				if(nextCell(nextX, nextY))
					return true;
			}
		}
		board[x][y] = 0;
		return false;
	}
	
	/**
	 * 셀의 좌표와 값이 주어졌을때 값이 들어가는지 판단하는 메서드
	 *
	 *@param x 		 현재 셀의 x값
	 *@param y 		 현재 셀의 y값
	 *@param current 주어진 값
	 *@return        true : current 값이 유효, false : 유효하지않음.
	 */
	private boolean legalMove(int x, int y, int current) {
		for(int i=0;i<9;i++) {
			if(current == board[x][i])
				return false;
		}
		for(int i=0;i<9;i++) {
			if(current == board[i][y])
				return false;
		}
		int cornerX = 0;
		int cornerY = 0;
		if(x > 2)
			if(x > 5)
				cornerX = 6;
			else
				cornerX = 3;
		if(y > 2)
			if(y > 5)
				cornerY = 6;
			else
				cornerY = 3;
		for(int i=cornerX;i<10 && i<cornerX+3;i++)
			for(int j=cornerY;j<10 && j<cornerY+3;j++)
				if(current == board[i][j])
					return false;
		return true;
	}
	
	/**
	 * 완성 된 보드에 빈 값을 뚫는 메서드
	 *@param holesToMake 빈 값의 갯수
	 */
	public void makeHoles(int holesToMake)
	{
		/* 난이도:
			쉬움: 49개 이하
			보통: 50 ~ 54개
			어려움: 54개 이상
		*/
		double remainingSquares = 81;
		double remainingHoles = (double)holesToMake;
		
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
			{
				double holeChance = remainingHoles/remainingSquares;
				if(Math.random() <= holeChance)
				{
					board[i][j] = 0;
					remainingHoles--;
				}
				remainingSquares--;
			}
	}
	
	/**
	 * 화면 출력
	 */
	public void print()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
				System.out.print(board[i][j] + "  ");
			System.out.println();
		}
		System.out.println();
	}
	
	
	
	int[][] board;
	private int operations;
}