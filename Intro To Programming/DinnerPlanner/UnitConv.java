import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Write a description of class UnitConv here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UnitConv
{
    
    FileReader fr;

    /**
     * Constructor for objects of class UnitConv
     */
    public UnitConv()
    {
        fr = new FileReader();
    }

    
    public double wholePieces(ArrayList a)
    {
       ArrayList a1 = new ArrayList<String>(a);
       a1.remove(0);
       double sum = 0;
       for (String s : (ArrayList<String>) a1) {
           String st = enhancedMMagic(s, 1);
           double foo = Double.parseDouble(st);
           sum+=foo;        
        }
       
       return sum;
    }
    
    public String tooManyUnits(ArrayList b) {
        ArrayList b1 = new ArrayList<String>(b);
        b1.remove(0);
        double sum = 0;
        for (String s : (ArrayList<String>) b1) {
           String st = enhancedMMagic(s,4);
           
           //Convert all amounts in pinches (i.e.: give scores)
           if (st.matches("pinch")) {
               sum = simpleConv(1,sum,s);
            }
           else if (st.matches("dash")) {
               sum = simpleConv(2,sum,s);              
            }
           else if (st.matches("teaspoon")) {
               sum = simpleConv(16,sum,s);              
            }
           else if (st.matches("tablespoon")) {
               sum = simpleConv(48,sum,s);              
            }
           else if (st.matches("fluid ounce")) {
               sum = simpleConv(96,sum,s);              
            }
           else if (st.matches("cup")) {
               sum = simpleConv(768,sum,s);              
            }
           else if (st.matches("pint")) {
               sum = simpleConv(1536,sum,s);              
            }
           else if (st.matches("quart")) {
               sum = simpleConv(3072,sum,s);              
            }
           else if (st.matches("gallon")) {
               sum = simpleConv(12288,sum,s);              
            }
        }
        
        //Understand which unit should be used:
        String s=null;
        if (sum==1) {
            s= "1 pinch";
        }
        if (sum<2&&sum>1) {
            s= sum+" pinches";
        }
        if (sum==2) {
            s= "1 dash";
        }
        if (sum<16&&sum>2) {
            s= sum/2+" dashes";
        }
        if (sum==16) {
            s= "1 teaspoon";
        }
        if (sum<48&&sum>16) {
            s= sum/16+" teaspoons";
        }
        if (sum==48) {
            s= "1 tablespoon";
        }
        if (sum<96&&sum>48) {
            s= sum/48+" tablespoons";
        }
        if (sum==96) {
            s= "1 ounce";
        }
        if (sum<768&&sum>96) {
            s= sum/96+" ounces";
        }
        if (sum==76) {
            s= "1 cup";
        }
        if (sum<1536&&sum>768) {
            s= sum/768+" cups";
        }
        if (sum==1536) {
            s= "1 pint";
        }
        if (sum<3072&&sum>1536) {
            s= sum/1536+" pints";
        }
        if (sum==3072) {
            s= "1 quart";
        }
        if (sum<12288&&sum>3072) {
            s= sum/3072+" quarts";
        }
        if (sum==12288) {
            s= "1 gallon";
        }
        if (sum>12288) {
            s= sum/12288+" gallons";
        }
        
        return s;
    }
    
    public String poundsOrOz(ArrayList c) {
        ArrayList c1 = new ArrayList<String>(c);
        c1.remove(0);
        double sum = 0;
        for (String s : (ArrayList<String>) c1) {
           String st = enhancedMMagic(s,4);
           if (st.matches("pound|pounds")) {
               sum = simpleConv(16,sum,s);
           }
           else if (st.matches("ounces|ounce")){
               sum = simpleConv(1,sum,s);
            }
        }
        String s=null;
        if (sum==1) {
            s= "1 ounce";
        }
        if (sum<16&&sum>1) {
            s= sum+" ounces";
        }
        if (sum==16) {
            s= "1 pound";
        }
        if (sum>16) {
            s=sum/16+" pounds";
        }
        return s;
    }
    
    public String enhancedMMagic(String str, int i) {
       Matcher m = fr.matcherMagic("((\\d+\\.\\d+)|(\\d+)) ((\\-)|(fluid ounces|fluid ounce)|(\\w+))",str);
       String ss=null;
       while (m.find())
            {
             ss = m.group(i);
            }
          
        return ss;
       
    } 
    public double simpleConv(double i, double sum1, String s) {
       String y = enhancedMMagic(s,1);
       double o = Double.parseDouble(y);
       
       return sum1+=(o*i);
    }
    
   
}
