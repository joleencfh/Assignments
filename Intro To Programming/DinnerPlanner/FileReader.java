import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;
/**
 * Write a description of class FileReader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FileReader
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class FileReader
     */
    public FileReader()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ArrayList readFile(String fileName, double amount) throws FileNotFoundException
    {
       Scanner in = new Scanner(new File(fileName));
       String text1 = in.useDelimiter("\\A").next();
       in.close();
          
       //Find the amount of servings the recipe provides:
       Matcher matcherA= matcherMagic("Servings: (\\d+)\\n", text1);
       String quant = null;
       while (matcherA.find()) {
         quant = matcherA.group(1);
        } 
       double q = Double.parseDouble(quant);
       
      
       //Extract the Ingredients section of the recipe:
       Matcher matcherB= matcherMagic("Ingredients:\\n((((\\d+\\.\\d+)|\\d+) (\\-|(fluid ounces|fluid ounce)|(\\w+)) (.*)\\n)*)", text1);
       String x = null;
       while (matcherB.find())
            {x = matcherB.group(1);
            }
       
       //Convert all amounts according to number of mouths to feed:
       //1) make an AL with each line/ingredient
       Matcher matcherC= matcherMagic("(((\\d+\\.\\d+)|(\\d+)) ((\\-)|(fluid ounces|fluid ounce)|(\\w+)) (.*))", x);
       ArrayList a = new ArrayList<String>();
       while (matcherC.find()) {
           a.add(matcherC.group(1));
        }
       
       //2) find new proportion 
       double newProportion= amount/q;
              
       //3) make a new AL with the right amounts
       Matcher matcherD= matcherMagic("(((\\d+\\.\\d+)|(\\d+)) ((\\-)|(fluid ounces|fluid ounce)|(\\w+)) (.*))", x);
       ArrayList IngredientsWRightAmounts = new ArrayList<String>();
        for (String s : (ArrayList<String>) a){  
          while (matcherD.find()) {
           String oldAmount = matcherD.group(2);
           double oldAm= Double.parseDouble(oldAmount);
           double newAmount = oldAm*newProportion;
           IngredientsWRightAmounts.add(newAmount+" "+matcherD.group(5)+" "+matcherD.group(9));
          } 
       }
  
       return IngredientsWRightAmounts;

    }
    
    public Matcher matcherMagic(String regex, String st) {
       Pattern p= Pattern.compile(regex);
       Matcher matcherB = p.matcher(st);
       return matcherB;
    }
}
