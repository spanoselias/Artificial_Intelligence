package Npuzzle.Solver;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by Elias on 2/14/2015.
 */


/////////////////////////////////////////////////////////////////
//
//                 PRIORITY QUEUE
//
/////////////////////////////////////////////////////////////////
public class PQ extends PriorityQueue<SearchNode>   {

    public PQ(){
        super();
    }

    //Insert data if not exist in priority Queue
     public boolean replaceExistingBoard (SearchNode o){

         Iterator<SearchNode> iterator=iterator();
         while(iterator.hasNext())
         {
             SearchNode curPos=iterator.next();
             if(curPos.equalBoard(o) && curPos.f > o.f)
             {
                 remove(curPos);
                 add(o);
                 return  true;
             }

         else if(curPos.equalBoard(o))
             {
                 return false;
             }
         }
          add(o);
         return true;
     }

/*
    public boolean replaceExistingBoard (SearchNode o) {

        Iterator<SearchNode> iterator = iterator();

        while (iterator.hasNext()) {
        SearchNode curPos = iterator.next();

            if (curPos.equalBoard(o)) {
                return false;
            }
        }
        add(o);
        return true;
    }*/
}
