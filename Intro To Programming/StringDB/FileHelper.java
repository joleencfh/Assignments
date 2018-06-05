import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList; 
 
 
 
public class FileHelper
{
    public Matcher match (String sql, String command) {
     Pattern pattern1 = Pattern.compile(sql);
     Matcher matcher1 = pattern1.matcher(command);
      
     return matcher1;
    }
     
 
    public ArrayList checkSubRegex (String subRegex, String command)
    {
        ArrayList groups = new ArrayList<String>();
        Pattern patternA = Pattern.compile(subRegex);
        Matcher matcherA = patternA.matcher(command);
          
           
          while (matcherA.find())
            { groups.add(matcherA.group(1));}
         
        return groups;
    }
     
}