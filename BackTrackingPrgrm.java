package com.sudoku;

import java.util.Scanner;

class Tuple{
	Integer row;
	Integer col;
}
public class BackTrackingPrgrm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Integer grid[][]=new Integer[9][9];
		Integer grid[][]={
				{-1,-1,-1,9,-1,6,-1,-1,-1},
				{4,-1,9,-1,-1,-1,2,-1,1},
				{-1,5,1,-1,-1,-1,6,7,-1},
				{-1,8,-1,-1,3,-1,-1,9,-1},
				{-1,1,7,6,-1,2,3,8,-1},
				{-1,3,-1,-1,9,-1,-1,5,-1},
				{-1,2,5,-1,-1,-1,8,6,-1},
				{7,-1,8,-1,-1,-1,9,-1,5},
				{-1,-1,-1,5,-1,7,-1,-1,-1}
		};
		
		BackTrackingPrgrm obj = new BackTrackingPrgrm();
		obj.printSudoku(grid);
		obj.solveSudoku(grid);
		System.out.println("\nAfter solving sudoku\n");
		obj.printSudoku(grid);
	}
	public void printSudoku(Integer grid[][]){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(grid[i][j]<0)
					System.out.print(grid[i][j]+"  ");
				else
					System.out.print("+"+grid[i][j]+"  ");
				if((j+1)%3==0)
					System.out.print("|");
			}
			if((i+1)%3==0){
				System.out.println("");
				for(int j=0;j<9;j++)
					System.out.print("----");
				}
			System.out.println("");
		}
	}
	public boolean solveSudoku(Integer grid[][]){

		Tuple tuple;
		
		System.out.println();
		if((tuple=FindUnassignedLocation(grid))==null)
			return true;
		System.out.println("tple =("+tuple.row+","+tuple.col+")");
		for (int num = 1; num < 10; num++) {
			if(noConflicts(grid,tuple,num)){
				System.out.println("In if block");
				grid[tuple.row][tuple.col]=num;
				printSudoku(grid);
				if(solveSudoku(grid))
					return true;
				grid[tuple.row][tuple.col]=-1;
			}
			
		}
		return false;
	}

	private boolean noConflicts(Integer[][] grid, Tuple tuple, int num) {
		// TODO Auto-generated method stub
		for(int i=0;i<9;i++){
			if(grid[i][tuple.col]==num)
				return false;
			if(grid[tuple.row][i]==num)
				return false;
		}
		int baseRow=0,baseCol=0;
		baseRow=(tuple.row/3)*3;
		baseCol=(tuple.col/3)*3;
		for(int i=baseRow;i<baseRow+3;i++){
			for(int j=baseCol;j<baseCol+3;j++){
				if(grid[i][j]==num)
					return false;
			}
		}
		return true;
	}

	public Tuple FindUnassignedLocation(Integer[][] grid){
		// TODO Auto-generated method stub
		Tuple tuple = new Tuple();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(grid[i][j]==-1){
					tuple.row=i;
					tuple.col=j;
					return tuple;
				}
			}
			
		}
		return null;
	}

}

