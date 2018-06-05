import edu.princeton.cs.algs4.*;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math.*;

 
public class Congress {
 
  private static double priorValue(int population, int seat) {
            
      return  (double) (population / (Math.sqrt(seat * (seat + 1))));
  }
 
 
 
    public static void main(String[] args) throws FileNotFoundException {
 
 
       
      int amountStates = Integer.parseInt(StdIn.readLine());
      int printingSeats = Integer.parseInt(StdIn.readLine());
 
      int amountSeats = printingSeats - amountStates;
         
 
        //from the algs4 library
        MaxPQ<State> pq = new MaxPQ<State>(amountStates, new StateComparator());
 
 
        /**
         * Here we assign one free seat to every state. And insert the state with it's value to our priority queue
         */
        while (StdIn.hasNextLine()) {
 
            String stateName = StdIn.readLine();
            int statePop= Integer.parseInt(StdIn.readLine());
            double priorityValue = priorValue(statePop, 1);
            pq.insert(new State(stateName, 1, statePop, priorityValue));
        }
 
        /**
         * We assign a seat to the state with the highest population. Then reduces the states population with a fixed amount
         * (priorValue). The state will then be shoved back in line, with it's newly updated/reduced population. Thereby
         * giving other states the oppotunity to get a seat.
         */
        while (amountSeats > 0) {
            State currentState = pq.delMax();
            int newSeats = currentState.seats + 1;
            double priorityValue = priorValue(currentState.population, newSeats);
            pq.insert(new State(currentState.name, newSeats, currentState.population, priorityValue));
            amountSeats--;
        }
 
        //Print out all states + seats.
        for (State state : pq) {
            StdOut.println(state.name + " " + state.seats);
        }
 
       // StdOut.println("\n" + printingSeats + " Seats assigned to " + amountStates+ " states.\n");
        //StdOut.println("Glory to Arstotzka!");
    }
 
 
}