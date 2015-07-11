package Npuzzle.Solver;

/////////////////////////////////////////////////////////////////
//
//            Author:Elias Spanos
//
/////////////////////////////////////////////////////////////////
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {

    public  Board readPuzzle(String path)
    {
        int puzzle[][]=null;
        String line=null;
        int size=-1;
        int i=0;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(path);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String[] character = line.trim().split("\\s+");
                if(character.length==1){
                    size=Integer.parseInt(character[0].toString());
                    puzzle=new int[size][size];
                    continue;
                }

                for(int j=0; j<size; j++)
                {
                    puzzle[i][j]=Integer.parseInt(character[j].toString());
                }//For statment
                i++;
            }
            // Always close files.
            bufferedReader.close();

        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            path + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + path + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        Board myBoard=new Board(puzzle);
        return myBoard;
    }//Read puzzle method

    /**
     *
     * Generate random puzzle for benchmark.
     */
    public void generateRand8Puzzle(String path,int puzzleSize,int noPuzzles)
    {
        ArrayList<ArrayList<Integer>> listNumbers = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> randomArrays=new ArrayList<Integer>();
        for(int j=0; j<noPuzzles; j++)
        {
            for (int i=0; i<puzzleSize; i++) {
                randomArrays.add(i);
            }//For statment
            Collections.shuffle(randomArrays);
            listNumbers.add(new ArrayList<Integer>(randomArrays));
            randomArrays.clear();
        }//For statment

        try {
            FileWriter filewriter=
                    new FileWriter(path);

            BufferedWriter bufferwriter=
                    new BufferedWriter(filewriter);

            for(int i=0; i<listNumbers.size(); i++)
            {
                for( int j=0; j<puzzleSize; j++)
                {
                    bufferwriter.write(listNumbers.get(i).get(j)+" ");
                }
                bufferwriter.write(String.format("%n"));
            }//For statment
            bufferwriter.close();
        }//Try

        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + path + "'");
        }

    }

 }

