import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashSet;
/**
 * Write a description of class IngredientsOrganizer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IngredientsOrganizer
{
    // instance variables - replace the example below with your own
    FileReader fr;

    /**
     * Constructor for objects of class IngredientsOrganizer
     */
    public IngredientsOrganizer()
    {
        fr = new FileReader();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ArrayList<ArrayList<String>> organize (ArrayList w,double amount) throws FileNotFoundException
    {
        //1) Merge ingredients in one AList
        
        ArrayList<String> all = new ArrayList<String>();
        for (String a : (ArrayList<String>)w) {
             ArrayList<String> I1= fr.readFile(a,amount );
             all.addAll(I1);}
              
        //2) Create a ALlist of ALists, to organize each individual ingredient
        // the name of the individual ingredient is stored as the element(0) of each AL
        
        ArrayList hey = new ArrayList<String>();
        
        for (String x : all) {
          //Pattern ingredient= Pattern.compile("((\\d+\\.\\d+)|(\\d+)) ((\\-)|(fluid ounces|fluid ounce)|(\\w+)) (.*)");
          //Matcher matcherB = ingredient.matcher(x);
          Matcher matcherB= fr.matcherMagic("((\\d+\\.\\d+)|(\\d+)) ((\\-)|(fluid ounces|fluid ounce)|(\\w+)) (.*)", x);
          while (matcherB.find()) {
          hey.add(matcherB.group(8));
          }
        }
        
        HashSet y = new HashSet(hey);
        ArrayList z = new ArrayList<String>(y);
        
        ArrayList<ArrayList<String>> list= new ArrayList<ArrayList<String>>();
        for (int i=0; i<z.size(); i++) {
            ArrayList foo = new ArrayList<String>();
            foo.add(z.get(i));
            list.add(foo);
            }
        
        //3) Populate each "individual-ingredient-AL" with the various amounts
        
        for (String x : all) {
            Matcher matcherC= fr.matcherMagic("((\\d+\\.\\d+)|(\\d+)) ((\\-)|(fluid ounces|fluid ounce)|(\\w+)) (.*)", x);
            while (matcherC.find()) {
            String ingrName= matcherC.group(8);
            String amounts= matcherC.group(1)+" "+matcherC.group(4);
            int index = z.indexOf(ingrName);
            (list.get(index)).add(amounts);
            }
        
        }
        
        return list;
    }
          
}
