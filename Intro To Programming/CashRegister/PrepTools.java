import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

 
public class PrepTools
{
    // instance variables - replace the example below with your own
    private int x;
 
    /**
     * Constructor for objects of class PrepTools
     */
    public PrepTools()
    {
        // initialise instance variables
        x = 0;
    }
 
 
    
    public ArrayList readFile(String regex, String fileName, ArrayList x) throws FileNotFoundException
    {
       Scanner in = new Scanner(new File(fileName));
       String text1 = in.useDelimiter("\\A").next();
       in.close();
                 
       Pattern priceItem = Pattern.compile(regex);
       Matcher matcherA = priceItem.matcher(text1);
                    
          while (matcherA.find())
            {x.add(matcherA.group(1));
            }
       return x;
    }
     
    public String stringPrice(float f) {
        String num = String.format ("%.2f", f);
        String s = num.replace(".", ",");
        return s;
    }
     
}