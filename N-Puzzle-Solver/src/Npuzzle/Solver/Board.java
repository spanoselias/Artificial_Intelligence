package Npuzzle.Solver;

/////////////////////////////////////////////////////////////////
//
//            Author:Elias Spanos
//
/////////////////////////////////////////////////////////////////

import java.lang.*;
import java.util.LinkedList;

public class Board {
     int board[][];
     int size;

    //Constructor
    Board(int tiles[][])
    {
        this.size=tiles[0].length;
        board=new int[size][size];

        for(int i=0; i<tiles[0].length; i++) {
            System.arraycopy(tiles[i], 0, this.board[i], 0, size);
        }

    }

    //Calculate tha manhtattan heuristic distance
    public int manhattan()
    {
     int totalManhattan=0;
     for(int i=0; i<board[0].length; i++)
     {
         for(int j=0; j<board[1].length; j++)
         {
             int curValue=board[i][j];
             if(curValue!=0)
             {
                 int cordX=(curValue-1)% board[0].length;
                 int cordY=(curValue-1)/ board[0].length;

                 totalManhattan += Math.abs(cordY-i)+ Math.abs(cordX-j);
             }//If is not the space
         }//For j
     }//For i
     return totalManhattan;
   }

    //Calculate tha Euclidean heuristic distance
    public int Euclidean()
    {
        int euclidean=0;
        int totalEuclidean=0;
        for(int i=0; i<board[0].length; i++)
        {
            for(int j=0; j<board[1].length; j++)
            {
                int curValue=board[i][j];
                if(curValue!=0)
                {
                    int cordX=(curValue-1)% board[0].length;
                    int cordY=(curValue-1)/ board[0].length;
                    totalEuclidean +=Math.sqrt(Math.pow((cordY - i) , 2) + Math.pow((cordX-j),2));
                }//If is not the space
            }//For j
        }//For i

        return  totalEuclidean;
    }

    //Calculate tha linearConflict heuristic distance
    public int linearConflict() {

        int linearConflict = 0;

        for (int row = 0; row < size; row++){
            int max = -1;
            for (int column = 0;  column < size; column++){
                int cellValue = board[row][column];

                if (cellValue != 0 && (cellValue - 1) / size == row){
                    if (cellValue > max){
                        max = cellValue;
                    }else {

                        linearConflict += 2;
                    }
                }

            }

        }



        for (int column = 0; column < size; column++){
            int max = -1;
            for (int row = 0;  row < size; row++){
                int cellValue = board[row][column];
                //is tile in its goal row ?
                if (cellValue != 0 && cellValue % size == column + 1){
                    if (cellValue > max){
                        max = cellValue;
                    }else {
                        //linear conflict, one tile must move left or right to allow the other to pass by and then back up
                        //add two moves to the manhattan distance
                        linearConflict += 2;
                    }
                }

            }

        }
        return linearConflict+manhattan();
    }

    //Calculate tha linearConflict heuristic distance
    public int hamming() {
        int ham = 0;
        int counter = 1;
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                if (board[i][j] == 0) {
                    counter +=1;
                    continue;
                }
                if (board[i][j] != counter) {
                    ham += 1;
                }
                counter +=1;

            }
        }
        return ham;
    }

    //Check if the puzzle isSolvable
    public boolean isSolvable()
   {
       int calBoard[]=new int[this.size*this.size];
       int inversions=0;
       int index=0;
       for(int i=0; i<size; i++){
           for(int j=0; j<size; j++){
               calBoard[index++]=this.board[i][j];
           }//For j
       }//For i

       for(int i=0; i<size*size; i++)
       {
           for(int j=i+1; j<size*size; j++)
           {
               if(calBoard[i]<calBoard[j] && calBoard[j]!=0 && calBoard[i]!=0)
               {++inversions;}
           }//for j
       }//For i statment

       if(inversions %2==0)
           return true;
                return false;
   }

    //Check if the puzzle isGoal
    public boolean isGoal(){

        return manhattan()==0?true:false;
             }

    //Find the neighbors
    public LinkedList<Board> neighbors()
    {
        LinkedList<Board> neighboardBoards= new LinkedList<Board>();
        int zeroRow=-1;
        int zeroCol=-1;

        for(int i=0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }

        if(zeroCol-1 >= 0)
        {
            Board neighborBoard=new Board(this.board);
            swap(neighborBoard,zeroRow,zeroCol,zeroRow,zeroCol-1);
            neighboardBoards.add(neighborBoard);
        }

        if(zeroCol+1 < size){
            Board neighborBoard=new Board(this.board);
            swap(neighborBoard,zeroRow,zeroCol,zeroRow,zeroCol+1);
            neighboardBoards.add(neighborBoard);
        }

        if(zeroRow-1 >=0){
            Board neighborBoard=new Board(this.board);
            swap(neighborBoard,zeroRow,zeroCol,zeroRow-1,zeroCol);
            neighboardBoards.add(neighborBoard);
        }

        if(zeroRow+1 < size)
        {
            Board neighborBoard=new Board(this.board);
            swap(neighborBoard,zeroRow,zeroCol,zeroRow+1,zeroCol);
            neighboardBoards.add(neighborBoard);
        }
        return neighboardBoards;

    }//Function neighbors

    //Help method for neighbor method
    private void swap(Board boardIn,int zeroRowIn, int zeroColIn,int newRow, int newCol)
    {
        int curNo=boardIn.board[newRow][newCol];

        boardIn.board[newRow][newCol]=0;
        boardIn.board[zeroRowIn][zeroColIn]=curNo;
    }//Function Swap

    //Representation of puzzle
    public  String toString(){
        String repres="";
       for (int i = 0; i < board[0].length; i++) {
           for (int j = 0; j < board[1].length; j++) {
              repres +=(board[i][j]+ " ");
           }
           repres +="\n";
             }
           return repres;
   }//toString Function

}








