import java.util.ArrayList;

public class ABPuzzle {

    public static void main(String[] args) {
        
        InputGetter ig = new InputGetter();       
        
        //int willBeFromConsole = Integer.valueOf(args[0]);
       	int willBeFromConsole = ig.getFirstNumber();
        System.out.println("firstNumber: " + willBeFromConsole);
        Board board = ig.getBoard(willBeFromConsole);
       	System.out.println("board: " + board.toString());
        State state = ig.getState(willBeFromConsole);
        
        state.setNeighbours();
        state.setFriends();
        SearchNode tmp = new SearchNode(state);
        SearchNode root = new SearchNode(state);
	Goal goal = new Goal();
        
       if (goal.checkState(tmp)) {
            System.out.println("The puzzle is solved.");
            System.exit(0);
        }
        
        if (board.allOnes() && !goal.checkState(tmp)) {
            System.out.println("There is no solution.");
            System.exit(0);
        }
        
        Search search = new Search(state);
        Object object = new Object();
        int count = 0;
        int limitflag = -1;
        int limit = 6;
        while (!goal.checkState(tmp)) {
            //System.out.println(tmp.getCurrent().toString());
            while ((limitflag!=0) && (!goal.checkState(tmp))) {
            search.Expand(tmp, board);
            
            //System.out.println("Fringe: "+ search.testGetList("fringe").getEverything().size()+"\n" + search.testGetList("fringe").toString());
            //System.out.println("Closed: " + search.testGetList("closed").getEverything().size()+"\n" + search.testGetList("closed").toString());
            //System.out.println("------------------------");
            tmp = search.getBest(limit);
            if (tmp == null) { //starting limit
                System.out.println("Over Limit");
                limitflag = 0;
            } 

            //state = tmp.getCurrent();
            }
            tmp = new SearchNode(state);
            search = new Search(state);
            limit+=1;
            limitflag = -1;
        }
        if (tmp != null) {    
            System.out.println("Solution is:");
            search.printTracePath(tmp);
        }
    }
}