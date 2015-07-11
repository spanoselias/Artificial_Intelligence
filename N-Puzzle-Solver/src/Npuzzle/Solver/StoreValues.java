package Npuzzle.Solver;

/////////////////////////////////////////////////////////////////
//
//            Author:Elias Spanos
//
/////////////////////////////////////////////////////////////////
public class StoreValues {
    SearchNode curSearchNode=null;
    int openList=0;
    int closeList=0;

    StoreValues(SearchNode curSearchNodeIn,int openListIn,int closeListIn)
    {
        this.curSearchNode=curSearchNodeIn;
        this.openList=openListIn;
        this.closeList=closeListIn;
    }//StoresValues

}
