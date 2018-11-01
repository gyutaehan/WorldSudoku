package kr.sesoc.sudoku.util;

import java.util.ArrayList;

public class SudokuSolver
{
	public SudokuSolver()
	{
		sg = new SudokuGenerator();
		init();
	}
	public boolean solve()
	{
		int countdown = 20;
		while(!solved() && --countdown > 0)
		{
			if(given())
				continue;
			if(findSingletons())
				continue;
			if(doubles())
				continue;
			if(doubletons())
				continue;
			justGuess();
		}
		if(!solved())
			System.out.println(zerosLeft());
		return solved();
	}
	
	public boolean given()
	{
		boolean repeat = false;
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(!found[i][j])
				{
					if(board[i][j] != 0)
					{
						repeat = true;
						foundNum(i, j, board[i][j]);
					}
					else if(possible[i][j].size() == 1)
					{
						repeat = true;
						foundNum(i, j, possible[i][j].get(0));
					}
				}
			}
		}
		return repeat;
	}
	
	public boolean findSingletons()
	{
		boolean repeat = false;
		int[] values;
		ArrayList<Integer> singletons = new ArrayList<Integer>();
		for(int i=0;i<9;i++)
		{
			values = new int[10];
			singletons.clear();
			for(int j=0;j<9;j++)
				for(int k=0;k<possible[i][j].size();k++)
					values[possible[i][j].get(k)]++;
					
			for(int j=1;j<10;j++)
				if(values[j] == 1)
					singletons.add(j);
					
			for(int j=0;j<9;j++)
				for(int k=0;k<singletons.size();k++)
					if(possible[i][j].contains(singletons.get(k)) && !found[i][j])
					{
						foundNum(i, j, singletons.get(k));
						repeat = true;
					}
		}
		
		for(int i=0;i<9;i++)
		{
			values = new int[10];
			singletons.clear();
			for(int j=0;j<9;j++)
				for(int k=0;k<possible[j][i].size();k++)
					values[possible[j][i].get(k)]++;
			for(int j=1;j<10;j++)
				if(values[j] == 1)
					singletons.add(j);
			for(int j=0;j<9;j++)
				for(int k=0;k<singletons.size();k++)
					if(possible[j][i].contains(singletons.get(k)) && !found[j][i])
					{
						foundNum(j, i, singletons.get(k));
						repeat = true;
					}
		}
		
		int[] corners = {0,3,6};
		for(int a=0;a<3;a++)
			for(int l=0;l<3;l++)
				for(int i=corners[a];i<corners[a]+3;i++)
				{
					values = new int[10];
					singletons.clear();
					for(int j=corners[l];j<corners[l]+3;j++)
						for(int k=0;k<possible[i][j].size();k++)
							values[possible[i][j].get(k)]++;
					for(int j=1;j<10;j++)
						if(values[j] == 1)
							singletons.add(j);
					for(int j=0;j<9;j++)
						for(int k=0;k<singletons.size();k++)
							if(possible[i][j].contains(singletons.get(k)) && !found[i][j])
							{
								foundNum(i, j, singletons.get(k));
								repeat = true;
							}
				}
		return repeat;
	}
	
	public boolean doubles()
	{
		boolean repeat = false;
		ArrayList<Integer> possDoubs = new ArrayList<Integer>();
		for(int i=0;i<9;i++)
		{
			possDoubs.clear();
			for(int j=0;j<9;j++)
			{
				if(possible[i][j].size() == 2 && !foundDouble[i][j])
					possDoubs.add(j);
			}			
			for(int j=0;j<possDoubs.size()-1;j++)
				for(int k=j+1;k<possDoubs.size();k++)
					if(possible[i][possDoubs.get(j)].equals(possible[i][possDoubs.get(k)]))
					{
						foundDouble[i][possDoubs.get(j)] = true;
						foundDouble[i][possDoubs.get(k)] = true;
						int zero = possible[i][possDoubs.get(j)].get(0);
						int one = possible[i][possDoubs.get(j)].get(1);
						for(int q=0;q<9;q++)
						{
							if(q == possDoubs.get(j) || q == possDoubs.get(k))
								continue;
							else
							{
								if(possible[i][q].indexOf(zero) != -1)
									possible[i][q].remove(possible[i][q].indexOf(zero));
								if(possible[i][q].indexOf(one) != -1)
									possible[i][q].remove(possible[i][q].indexOf(one));
							}	
						}
						repeat = true;
					}
		}
		
		for(int i=0;i<9;i++)
		{
			possDoubs.clear();
			for(int j=0;j<9;j++)
			{
				if(possible[j][i].size() == 2 && !foundDouble[j][i])
					possDoubs.add(j);
			}
			
			for(int j=0;j<possDoubs.size()-1;j++)
				for(int k=j+1;k<possDoubs.size();k++)
					if(possible[possDoubs.get(j)][i].equals(possible[possDoubs.get(k)][i]))
					{
						foundDouble[possDoubs.get(j)][i] = true;
						foundDouble[possDoubs.get(k)][i] = true;
						if(possible[possDoubs.get(j)][i].size() == 1)
						{
							print();
							System.out.println(j + " " + k);
						}
						int zero = possible[possDoubs.get(j)][i].get(0);
						int one = possible[possDoubs.get(j)][i].get(1);
						for(int q=0;q<9;q++)
						{
							if(q == possDoubs.get(j) || q == possDoubs.get(k))
								continue;
							else
							{
								if(possible[q][i].indexOf(zero) != -1)
									possible[q][i].remove(possible[q][i].indexOf(zero));
								if(possible[q][i].indexOf(one) != -1)
									possible[q][i].remove(possible[q][i].indexOf(one));
							}
						}							
						repeat = true;
					}
		}

		int[] corners = {0,3,6};
		for(int a=0;a<3;a++)
			for(int l=0;l<3;l++)
				for(int i=corners[a];i<corners[a]+3;i++)
				{
					possDoubs.clear();
					for(int j=corners[l];j<corners[l]+3;j++)
						if(possible[i][j].size() == 2 && !foundDouble[i][j])
						{
							possDoubs.add(i);
							possDoubs.add(j);
						}
					for(int j=0;j<possDoubs.size()-1;j+=2)
						for(int k=j+2;k<possDoubs.size();k+=2)
							if(possible[possDoubs.get(j)][possDoubs.get(j+1)].equals(possible[possDoubs.get(k)][possDoubs.get(k+1)]))
							{
								foundDouble[possDoubs.get(j)][possDoubs.get(j+1)] = true;
								foundDouble[possDoubs.get(k)][possDoubs.get(k+1)] = true;
								int zero = possible[possDoubs.get(j)][possDoubs.get(j+1)].get(0);
								int one = possible[possDoubs.get(j)][possDoubs.get(j+1)].get(1);
								for(int q=corners[a];q<corners[a]+3;q++)
									for(int r=corners[l];r<corners[l]+3;r++)
									{
										if(q == possDoubs.get(j) && r == possDoubs.get(j+1))
											continue;
										if(q == possDoubs.get(k) && r == possDoubs.get(k+1))
											continue;
										else
										{
											if(possible[i][q].indexOf(zero) != -1)
												possible[i][q].remove(possible[i][q].indexOf(zero));
											if(possible[i][q].indexOf(one) != -1)
												possible[i][q].remove(possible[i][q].indexOf(one));
										}
									}
								repeat = true;
							}
				}
		
		return repeat;
	}
	
	public boolean doubletons()
	{
		boolean result = false;
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				for(int k=j+1;k<9;k++)
				{
					ArrayList<Integer> common = inCommon(possible[i][j], possible[i][k]);
					if(common.size() == 2)
					{
						boolean checkOthers = true;
						for(int l=0;l<9;l++)
						{
							if(l == j || l == k)
								continue;
							if(possible[i][l].contains(common.get(0)) || possible[i][l].contains(common.get(1)))
							{
								checkOthers = false;
								break;
							}
						}
						if(checkOthers)
						{
							possible[i][j] = new ArrayList<Integer>(common);
							possible[i][k] = new ArrayList<Integer>(common);
							result = true;
						}
					}
				}
				
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				for(int k=i+1;k<9;k++)
				{
					ArrayList<Integer> common = inCommon(possible[j][i], possible[j][k]);
					if(common.size() == 2)
					{
						boolean checkOthers = true;
						for(int l=0;l<9;l++)
						{
							if(l == i || l == k)
								continue;
							if(possible[j][l].contains(common.get(0)) || possible[j][l].contains(common.get(1)))
							{
								checkOthers = false;
								break;
							}
						}
						if(checkOthers)
						{
							possible[j][i] = new ArrayList<Integer>(common);
							possible[j][k] = new ArrayList<Integer>(common);
							result = true;
						}
					}
				}
		int[] corners = {0, 3, 6};
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int i = corners[a]; i < corners[a] + 3; i++) {
                    for (int j = corners[b]; j < corners[b] + 3; j++) {
                        int k = i;
                        int l = j;
                        if (j % 3 == 2) {
                            if (i % 3 == 2) {
                                continue;
                            }
                            k++;
                            l = corners[b];
                        }

                        for (; k < corners[a] + 3; k++) {
                            for (; l < corners[b] + 3; l++) {
                                ArrayList<Integer> common = inCommon(possible[i][j], possible[k][l]);
                                if (common.size() == 2) {
                                    boolean checkOthers = true;

                                    for (int m = corners[a]; m < corners[a] + 3; m++) {
                                        for (int n = corners[b]; n < corners[b] + 3; n++) {
                                            if (m == i && n == j) {
                                                continue;
                                            }
                                            if (m == k && n == l) {
                                                continue;
                                            }
                                            if (possible[m][n].contains(common.get(0)) || possible[m][n].contains(common.get(1))) {
                                                checkOthers = false;
                                                break;
                                            }
                                        }
                                    }
                                    if (checkOthers) {
                                        possible[i][j] = new ArrayList<Integer>(common);
                                        possible[k][l] = new ArrayList<Integer>(common);
                                        result = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
	}
	
	private ArrayList<Integer> inCommon(ArrayList<Integer> one, ArrayList<Integer> two)
	{
		ArrayList<Integer> common = new ArrayList<Integer>();
		for(int i=0;i<one.size();i++)
			for(int j=0;j<two.size();j++)
			{
				if(one.get(i) == two.get(j))
					common.add(one.get(i));
			}
		return common;
	}
	
	public void justGuess()
	{
		ArrayList<SudokuSolver> solved = new ArrayList<SudokuSolver>();
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				if(board[i][j] == 0 && possible[i][j].size() > 0)
				{					
					for(int k=0;k<possible[i][j].size();k++)
					{
						SudokuSolver guess = new SudokuSolver();
						guess.board = this.board;
						guess.possible = this.possible;
						guess.foundNum(i, j, possible[i][j].get(k));
						if(guess.solve() && solved.size() > 0)
						{
							boolean notIncluded = true;
							for(int l=0;l<solved.size();l++)
								if(solved.get(k).equals(guess))
									notIncluded = false;
							if(notIncluded)
								solved.add(guess);
						}
						else if(guess.solve())
						{
							solved.add(guess);
						}
					}
				}
		if(solved.size() > 1)
		{
			System.err.println("여러 답을 확인");
		}
		if(solved.size() > 0)
			this.board = solved.get(0).board;
	}
	
	public void foundNum(int x, int y, int numFound)
	{
		
		if(board[x][y] != 0 && board[x][y] != numFound)
		{
			throw new RuntimeException("error!!");
		}
		
		board[x][y] = numFound;
		possible[x][y].clear();
		possible[x][y].add(numFound);
		found[x][y] = true;
		
		for(int i=0;i<9;i++) {
			if(i != x)
				if(possible[i][y].indexOf(numFound) != -1)
					possible[i][y].remove(possible[i][y].indexOf(numFound));
		}
		for(int i=0;i<9;i++) {
			if(i != y)
				if(possible[x][i].indexOf(numFound) != -1)
					possible[x][i].remove(possible[x][i].indexOf(numFound));
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
				if(i != x && j != y)
					if(possible[i][j].indexOf(numFound) != -1)
						possible[i][j].remove(possible[i][j].indexOf(numFound));
	}
	
	public boolean solved()
	{
		SudokuChecker sc = new SudokuChecker(board);
		return sc.completed() && sc.checkPuzzle();
	}
	
	public void reset(int[][] board)
	{
		this.board = board;
		init();
	}
	
	public void reset(int diff)
	{
		this.board = sg.nextBoard(diff);
		init();
	}
	
	public void init()
	{
		possible = new ArrayList[9][9];
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
			{
				possible[i][j] = new ArrayList<Integer>();
				for(int k=1;k<10;k++)
					possible[i][j].add(k);
			}
		found = new boolean[9][9];
		foundDouble = new boolean[9][9];
	}
	
	public void print()
	{
		for(int i=0;i<9;i++)
		{
			if(i%3==0 && i != 0)
				System.out.println("-  -  -  | -  -  -  |  -  -  -");
			for(int j=0;j<9;j++)
			{
				if(j%3==0 & j != 0)
					System.out.print("| ");
				System.out.print(board[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private int zerosLeft()
	{
		int empty = 0;
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				if(board[i][j] == 0)
					empty++;
		return empty;
	}
	
	private void data(int difficulty)
	{
		int empty = 0;
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				if(board[i][j] == 0)
					empty++;
		System.out.println(empty);
	}
	@Override
	public boolean equals(Object other)
	{
		if(!(other instanceof SudokuSolver))
			return false;
		SudokuSolver otherSudoku = (SudokuSolver) other;
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				if(this.board[i][j] != otherSudoku.board[i][j])
					return false;
		return true;
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	int[][] board;
	ArrayList<Integer>[][] possible;
	boolean[][] found;
	boolean[][] foundDouble;
	SudokuGenerator sg;
}