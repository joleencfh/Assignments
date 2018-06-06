import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashSet;

public class ShoppingList
{
    FileReader fr;
    IngredientsOrganizer iOrg;
    UnitConv uc;

    
    public ShoppingList()
    {
       uc = new UnitConv();
       iOrg = new IngredientsOrganizer();
       fr= new FileReader();
    }

    
    public void getThatList(ArrayList<String> x, double amount) throws FileNotFoundException
    {
                
        ArrayList<ArrayList<String>> HALAL = iOrg.organize(x,amount);
        ArrayList<String> finalList= new ArrayList<String>();
       
        for (ArrayList al : HALAL) {
            //1) check what kind of units I'm dealing with
            String s = (String) al.get(1);
            Matcher matcherA = fr.matcherMagic("((\\d+\\.\\d+)|(\\d+)) ((\\-)|(fluid ounces|fluid ounce)|(\\w+))", s);
            String st=null;
            while (matcherA.find()) {
                st = matcherA.group(4);         
            }
            
            //2)do unit conversion + build final list
            double sum=0;
            if (st.matches("-")) {
                finalList.add(uc.wholePieces(al)+" "+al.get(0));
            }
            if (st.matches("clove|cloves")) {
                double amountz = uc.wholePieces(al);
                if (amount<=1) 
                finalList.add(amountz+" clove "+al.get(0));
                else if (amount>1) 
                finalList.add(amountz+" cloves "+al.get(0));
                
            }
            else if (st.matches("pound|ounce")) {
                finalList.add(uc.poundsOrOz(al)+" "+al.get(0));
            }
            else if (st.matches("pinch|dash|teaspoon|tablespoon|fluid ounce|cup|pint|quart|gallon")) { 
                finalList.add(uc.tooManyUnits(al)+" "+al.get(0));            
            }
        }   
        
        //Finally print the list
        System.out.println("**** SHOPPING LIST ****");
        System.out.println(" ");
        for (String s : finalList) {
           System.out.println(s);
        }
        
        
    }
}

