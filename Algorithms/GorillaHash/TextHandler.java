import edu.princeton.cs.algs4.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

public class TextHandler {
    
  public TextHandler() {
      }
  
  public static ArrayList<String> getCreatureInfo(String fileName) throws FileNotFoundException {
        
    
    
    ArrayList<String> creatureInfoList= new ArrayList<String>();
    if (fileName=="HbB_FASTAs-in.txt") {
        Scanner in = new Scanner(new File(fileName));
        String fullText = in.useDelimiter("\\A").next();
        in.close();
        Matcher matcher= match(">.+\\n.+.\\n.+\\n.+", fullText);
        while (matcher.find()) {
            creatureInfoList.add(matcher.group());
        }
    }
    else if (fileName=="bigdata.txt") {
        String fullText = shrinkIt("bigdata.txt");
        Matcher matcher= match(">.+\\n.+.\\n.+\\n.+", fullText);
        while (matcher.find()) {
            creatureInfoList.add(matcher.group());
        }
    }
          
   return creatureInfoList;
  }
  
  public static String shrinkIt(String fileName) throws FileNotFoundException {
        
      In in = new In("bigdata.txt");
      String infoString=" ";
      while(in.hasNextLine()) {
          String line = in.readLine();
          if (line.contains(">")) {
             infoString = infoString+line;
          }
          else if (!line.contains(">")) {
             int hash = line.hashCode()%10;
             infoString = infoString+hash;
          }
        }
   return infoString;
}
  
  public static Matcher match(String regex, String st) {
      Pattern p= Pattern.compile(regex);
       Matcher matcherB = p.matcher(st);
       return matcherB;
    }

}