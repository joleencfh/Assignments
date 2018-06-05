import java.util.ArrayList;
import java.io.*;
/**
 * Write a description of class Interactive here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Interactive
{
    public static void main(String[] args){}
    // instance variables - replace the example below with your own
    private RandomRecipe rr;
    private InputReader ir;
    private ShoppingList sl;

    /**
     * Constructor for objects of class Interactive
     */
    public Interactive()
    {
        rr = new RandomRecipe();
        ir = new InputReader();
        sl = new ShoppingList();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void start()
    {
        boolean finished = false;

        welcome();

        while(!finished) {
            String input = ir.getOne();

            if(input.contains("END")) {
                finished = true;
            }
            else if (input.contains("A")) {
               System.out.println("Sorry Troels, I didn't have time to finish this feature...");
               System.out.println("Why don't you try with the other option instead?");
                       
            }
            else if (input.contains("B")) {
                System.out.println("Choose between:");
                System.out.println("Random meat or fish: type M");
                System.out.println("Random vegetarian: type V");
                System.out.println("Real Random: type R");
                System.out.println();
                System.out.println("Example:  M M V V R");
                System.out.println("A command like that would get you");
                System.out.println("2 random meat/fish recipes ");
                System.out.println("2 random vegetarian recipes");
                System.out.println("1 real random recipe");
                
                //new input:
                ArrayList<String> input1 = ir.getInput();
                
                //get recipe list according to input:
                ArrayList<String> recipeList = rr.getRandom(input1);
                System.out.println("These are the recipes you're getting:");
                for (String x : recipeList) {
                  System.out.println(x);
                }
                
                //get number of guests:
                System.out.println();
                System.out.println();
                System.out.println("How many guests? (Enter a number)");
                String mouths = ir.getOne();
                Double mouthss = Double.parseDouble(mouths);
                
                //get shopping list:
                System.out.println("Here is your shopping list:");
                System.out.println();
                try {
                    sl.getThatList(recipeList, mouthss);
                } 
                catch (FileNotFoundException e) {
                    System.out.println("Something went wrong...");
                
                }
                                
            }
            else {
                System.out.println("Invalid input. Please choose A or B");
            }
        }
    }
    
    private void welcome()
    {
        System.out.println("What to eat?");
        System.out.println();
        System.out.println("Choose between:");
        System.out.println("A. Specific Recipe");
        System.out.println("B. Random");
        System.out.println("Please type A or B");
        System.out.println();
        System.out.println("Type END when done.");
    }
}
