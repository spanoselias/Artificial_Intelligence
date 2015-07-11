package Npuzzle.Solver;

/////////////////////////////////////////////////////////////////
//
//            Author:Elias Spanos
//
/////////////////////////////////////////////////////////////////

import java.lang.*;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Driver {

    public static void runAStarlgorithm(Board boardIn)
    {
        StoreValues sol=null;
        long startTime=0,endTime=0,duration=0;

        PuzzleAlgorithms puzzleAlgor = new PuzzleAlgorithms(boardIn);
         startTime=System.currentTimeMillis();

         sol=puzzleAlgor.AStarAlgorithm();

         endTime=System.currentTimeMillis();
         duration = ((endTime - startTime));
         System.out.println("************************************");
         System.out.println("AStar-milliseconds: " + duration);
         System.out.println("************************************");
         System.out.println("AStar-OpenList: " + sol.openList);
         System.out.println("************************************");
         System.out.println("AStar-Closed: " + sol.closeList);
         System.out.println("************************************");
         System.out.println("Total Moves: " + sol.curSearchNode.g);
        System.out.println("************************************");
         Stack<String> moves= puzzleAlgor.reconstructMoves(sol.curSearchNode);

         System.out.println("*************Moves*************************");
         while(moves.size()>0)
         {
             System.out.println(moves.pop());
         }

    }//runAlgorithms method

    public static void runIdAStarAlgorithm(Board boardIn)
    {
        StoreValues sol=null;

        PuzzleAlgorithms puzzleAlg = new PuzzleAlgorithms(boardIn);
        long startTime=0,endTime=0,duration=0;
        startTime=System.currentTimeMillis();
        sol=puzzleAlg.ideStar();

        endTime=System.currentTimeMillis();
        duration = ((endTime - startTime));
        System.out.println("ideA-milliseconds: " + duration);
        System.out.println("Total Moves: " + sol.curSearchNode.g);
        System.out.println(sol.curSearchNode.toString());
        Stack<String> moves= puzzleAlg.reconstructMoves(sol.curSearchNode);

        System.out.println("*************Moves*************************");
        while(moves.size()>0)
        {
            System.out.println(moves.pop());
        }
    }//runIdAStarAlgorithm Algorithm

    public static void benchmark()
    {
        Solver solve=new Solver();
      //  solve.generateRand8Puzzle("randomPuzzles.txt",16,2);
        String locationPath="randomPuzzles.txt", line="";
        int size=4;int countLine=0;
        int randPuzzle[][]=new int [size][size];

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(locationPath);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            long startTime=0,endTime=0,duration=0;
            long totalOpenlist=0;
            long totalCloseList=0;

            while((line = bufferedReader.readLine()) != null) {
                String[] character = line.trim().split(" ");

                int g=0;
                for(int i=0; i<size; i++)
                {
                    for(int j=0; j<size; j++)
                    {
                        randPuzzle[i][j]=Integer.parseInt(character[g++].toString());

                    }//For j statment
                }//For i statment

                Board myBoard=new Board(randPuzzle);

                //Check if is Solvable
                if(myBoard.isSolvable()==true) {
                    countLine++;
                    PuzzleAlgorithms puzzleAlg = new PuzzleAlgorithms(myBoard);

                    startTime = System.currentTimeMillis();

                    StoreValues myCurenValue = puzzleAlg.ideStar();

                    endTime = System.currentTimeMillis();
                    totalOpenlist += myCurenValue.openList;
                    totalCloseList += myCurenValue.closeList;
                    duration += ((endTime - startTime));

                }//If isSolvable statment
            }//While there are puzzles


           if(countLine!=0) {
               duration /= countLine;
               totalOpenlist /= countLine;
               totalCloseList /= countLine;

               System.out.println("duration : " + duration);
               //  System.out.println("OpenList: " + totalOpenlist);
               //System.out.println("CloseList: " + totalCloseList);
               // Always close files.
           }//Check if exist valid puzzle
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            locationPath + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + locationPath + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }




    }

    public static void main(String [] args)
    {
        Scanner keyboard=new Scanner(System.in);

        int interfaceAnswer=-1;
        System.out.println("**************Welcome to N-puzzle Solver");
        System.out.println("What puzzle do you want to solve?(Type 1 for 8-puzzle or 2 for N-puzzle(IDA-Star)):");
        interfaceAnswer=keyboard.nextInt();
        if(interfaceAnswer==1)
        {
            int eightPuzzle[][]=new int[3][3];

            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++)
                {
                    System.out.println("Give the number of the position: " + "(" + i + "," + j + ") :");
                    eightPuzzle[i][j]=keyboard.nextInt();
                }//For j
            }//For i

            Board eightPuz=new Board(eightPuzzle);
        //    if(eightPuz.isSolvable()==true){
                runAStarlgorithm(eightPuz);
          //  }
             /*   else
                {
                    System.out.println("There is no solution for thiz puzzle");
                }*/
        }

        else if(interfaceAnswer==2)
        {
            int size;
            System.out.println("Give the dimession of puzzle(ex. 3x3 just give a 3): ");
            size=keyboard.nextInt();

            int eightPuzzle[][]=new int[size][size];
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++)
                {

                    System.out.println("Give the number of the position: " + "(" + i + "," + j + ")");
                    eightPuzzle[i][j]=keyboard.nextInt();
                }//For j
            }//For i

            Board fifteenPuz=new Board(eightPuzzle);
          //  if(fifteenPuz.isSolvable()==true){
                System.out.println("**************\nLoading.....\n");
                runIdAStarAlgorithm(fifteenPuz);
           // }
               /* else
                {
                    System.out.println("There is no solution for thiz puzzle");
                }*/

        }//If the answer is 2

       else
        {
            System.out.println("Error!Unable to solve your request");
        }



    }

}
