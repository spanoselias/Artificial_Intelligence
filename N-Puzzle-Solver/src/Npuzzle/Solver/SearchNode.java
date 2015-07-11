package Npuzzle.Solver;

/**
 * Created by Elias on 2/8/2015.
 */

/////////////////////////////////////////////////////////////////
//
//                       SEARCH NODE
//
/////////////////////////////////////////////////////////////////
public class SearchNode implements Comparable<SearchNode> {

        Board puzzleBoard;
        int h;
        int g;
        int f;
        SearchNode parent=null;

    //Node that store the state of the puzzle.
        SearchNode(Board boardIn)
        {
            int size=boardIn.board[0].length;
            this.puzzleBoard=boardIn;
            this.h=boardIn.linearConflict();
            this.g=0;
            this.f=this.g+this.h;
        }

    //Node that store the state of the puzzle.
        SearchNode(){
           puzzleBoard=null;
           parent=null;
        }

        SearchNode(Board curboard,int g, SearchNode curparent)
        {
            this.puzzleBoard=curboard;
            this.g=g;
            this.h=curboard.linearConflict();
            this.f=this.g+this.h;
            this.parent=curparent;
        }

    //Check two boards if it equal
        public  boolean equalBoard(SearchNode checkBoard)
        {
            for (int i = 0; i < checkBoard.puzzleBoard.board[0].length; i++) {
                for (int j = 0; j < checkBoard.puzzleBoard.board[1].length; j++) {
                    if (checkBoard.puzzleBoard.board[i][j] != this.puzzleBoard.board[i][j]) {
                        return false;
                    }
                }
            }
           // return Arrays.equals(checkBoard.puzzleBoard.board,puzzleBoard.board);
         return true;
        }

        @Override
        public int compareTo(SearchNode node1) {
            //  return node1.f<node2.f?-1: node1.f ==node2.f ? 0:1;
            //  SearchNode node1=(SearchNode)o1;
            //SearchNode node2=(SearchNode)o2;

           if(node1.f==this.f && node1.g>this.g){return 1;}
            else if(node1.f==this.f && node1.g <this.g){return -1;}

           else
               return node1.f > this.f?-1:node1.f==this.f? 0 : 1;
        }

        @Override
        public boolean equals(Object o) {

            SearchNode n = (SearchNode) o;
            if(equalBoard(n))
                return true;
                    return false;
                }


        public  String toString(){
            String repres="";
            for (int i = 0; i < puzzleBoard.board[0].length; i++) {
                for (int j = 0; j < puzzleBoard.board[1].length; j++) {
                    repres +=(puzzleBoard.board[i][j]+ " ");
                }
                repres +="\n";
            }
            return repres;
        }//toString Function

}
