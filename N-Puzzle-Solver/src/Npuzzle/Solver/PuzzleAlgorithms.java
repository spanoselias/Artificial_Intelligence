package Npuzzle.Solver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/////////////////////////////////////////////////////////////////
//
//            Author:Elias Spanos
//
/////////////////////////////////////////////////////////////////
public class PuzzleAlgorithms {

    private Board starBoard=null;
    
    private int f=0;
    private int min=0;
    private int t=0;
    private SearchNode solution=null;    
        
    PuzzleAlgorithms(Board boardIn)
    {
        this.starBoard=boardIn;
    }

    private LinkedList<SearchNode> closeList= new LinkedList<SearchNode>();

    /*IdA star algorithm is a recursion algorithm that estimate the
     leaste distance to solution using an an admissable heuristic function
     and have an closeList to know which of the board has pass before to
     prevent from cycles.
    */
    public StoreValues ideStar()
    {
        int thershold=0;
        this.min=0;
        SearchNode initial=new SearchNode(this.starBoard,0,null);
        boolean solve=false;

        int bound=initial.h;

        while(this.t!=-1)
        {
            closeList.add(initial);
            this.t=ideSearch(initial,0,thershold);
            if(t==-1) {return new StoreValues(solution,0,closeList.size());}
            thershold=this.t;
            closeList.clear();
        }
        return null;
    }

    private  int ideSearch(SearchNode node,int g,int threshold)
    {
        if(node.f>threshold){return node.f;}

        if(node.puzzleBoard.isGoal()){
            this.solution=node;
            return -1;}

        this.min=Integer.MAX_VALUE;

        for(Board neighbor :node.puzzleBoard.neighbors())
        {
            if(!closeList.contains(neighbor)) {
                t = ideSearch(new SearchNode(neighbor, node.g + 1, node), g + 1, threshold);
                if (t == -1) {
                    return -1;
                }
                if (t < this.min) {
                    this.min = t;
                }
            }
        }
        return min;

    }

    /*IdeStar Algorithm. Is estimate the distance to solution using an
      an adimisable heuristic function and have an closeList to know
      which of the board has pass before to prevent from cycles.
    */
    public  StoreValues  AStarAlgorithm()
    {
        LinkedList<SearchNode> closeList= new LinkedList<SearchNode>();
        PQ openList=new PQ();
        SearchNode initial=new SearchNode(this.starBoard,0,null);
        //if(!initial.puzzleBoard.isSolvable()){return null;}
        openList.add(initial);

        while (openList.size()>0)
        {
            SearchNode curBoard=openList.remove();
            LinkedList<Board> curStateBoard=curBoard.puzzleBoard.neighbors();

            if(curBoard.puzzleBoard.isGoal()) {
               StoreValues myStores=new StoreValues(curBoard,openList.size(),closeList.size());
             //  System.out.println("List:" + openList.size()+closeList.size());
                return myStores;
            }

            closeList.add(curBoard);

            //  System.out.println(closeList.size());
            for(Board neighborBoard : curStateBoard)
            {
                if(closeList.contains(neighborBoard)){continue;}
                // if(!openList.contains(neighborBoard)){openList.add(neighborBoard);}
                SearchNode neighborNode=new SearchNode(neighborBoard,curBoard.g+1,curBoard);
                openList.replaceExistingBoard(neighborNode);
                // openList.add(neighborBoard);
            }

        }
           // return null;
        return new StoreValues(null,openList.size(),closeList.size());
    }

    public Stack<String> reconstructMoves(SearchNode finalState) {
        int size = finalState.puzzleBoard.size;

        SearchNode parentBoard =finalState;
        SearchNode childBoard = null;

        Stack<String> stackMoves=new Stack<String>();

        int parentX=-1;
        int parentY=-1;
        int childX=-1;
        int childY=-1;

        while (parentBoard.parent != null) {
            childBoard=parentBoard;
            parentBoard=childBoard.parent;


            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                        if(childBoard.puzzleBoard.board[i][j]==0) {
                            childX=i;
                            childY=j;
                        }
                }//For j
            }//For i

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {

                    if(parentBoard.puzzleBoard.board[i][j]==0) {
                        parentX=i;
                        parentY=j;
                    }
                }//For j
            }//For i


            if(childX==parentX && (childY-1)==parentY) {

            String move="Move left the : " + childX + "," + childY + "(" + parentBoard.puzzleBoard.board[childX][childY] +  ")";
            stackMoves.push(move);
            }

            if(childX==parentX && (childY+1)==parentY) {

                String move="Move right the : " + childX + "," + childY + "(" + parentBoard.puzzleBoard.board[childX][childY] +  ")";
                stackMoves.push(move);
            }

            if((childX+1)==parentX && (childY)==parentY) {

                String move="Move down the : " + childX + "," + childY + "(" + parentBoard.puzzleBoard.board[childX][childY] +  ")";
                stackMoves.push(move);
            }

            if((childX-1)==parentX && (childY)==parentY) {

                String move="Move up the : " + childX + "," + childY + "(" + parentBoard.puzzleBoard.board[childX][childY] +  ")";
                stackMoves.push(move);
            }
        }//while parent not null;


        return  stackMoves;

    }//recontruct moves


/*
    private void writePuzzle(SearchNode curBoard,BufferedWriter bufferwriter) throws IOException
    {
        if(curBoard.parent==null){
            bufferwriter.write(curBoard.puzzleBoard.move);
            bufferwriter.write(String.format("%n"));
            return;
        }

        writePuzzle(curBoard.parent, bufferwriter);
        bufferwriter.write(curBoard.puzzleBoard.move);
        bufferwriter.write(String.format("%n"));
    }
*/

}//PuzzleAlgorithms Method
