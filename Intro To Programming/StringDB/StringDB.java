import java.util.*;
import java.util.regex.*;
 
public class StringDB {
 
     private String command1;
     private FileHelper fileHelper;
     private Table table;
     private HashMap tableDB;
  
     
    public StringDB()
    {
        fileHelper=new FileHelper();
        tableDB = new HashMap<String, Table>();
         
    }
  
     
    public void execute (String command)
    {
        command1=command;
         
        //lista SQL query da matchare
        String sql1 = "SELECT \\* (from|FROM) (\\w+) ;";
        String sql2 = "DROP\\s+TABLE\\s+(\\w+)\\s+;";
        String sql3 = "CREATE TABLE (\\w+) .+";
        String sql4 = "INSERT INTO (\\w+) VALUES \\( .+";
        String sql5 = "SELECT (.+) (:?AS|as) (.+)( , (.+)AS (.+))* INTO(.+) FROM (.+) ;"; 
        String sql6 = "SELECT \\* INTO (.+) FROM (.+) INNER JOIN (.+) ON (.+) = (.+) ;";
           
        Matcher matcher1 = fileHelper.match(sql1,command);
        Matcher matcher2 = fileHelper.match(sql2,command);
        Matcher matcher3 = fileHelper.match(sql3,command);
        Matcher matcher4 = fileHelper.match(sql4,command);
        Matcher matcher5 = fileHelper.match(sql5,command);
        Matcher matcher6 = fileHelper.match(sql6,command);
          
        if (matcher1.matches()) { 
        Table table = new Table();
        table = (Table) tableDB.get(matcher1.group(2));
        table.printTheTable();
        
        //System.out.println("It prints everything from the table called "+matcher1.group(1));
            
        }
        else if (matcher2.matches()) { 
            String tableName= matcher2.group(1);
            if (tableDB.get(tableName)==null) { 
            System.out.println("ERROR:  table \""+tableName+"\" does not exist");
            }
            else {
            tableDB.remove(tableName);
            System.out.println("DROP TABLE");
            //System.out.println("It completely removes the table called "+matcher2.group(1)+"... be careful ");
            }
        }
        else if (matcher3.matches()) {
          
          String subregex = "(\\w+) varchar";
          String tableName= matcher3.group(1); 
          table= new Table();
          table.addToHeader(fileHelper.checkSubRegex(subregex,command1));
          if (table.checkHeaderDoubles()) { 
           
          } else { tableDB.put(tableName, table);
          System.out.println("CREATE TABLE");
               
            }
               
          //System.out.println("It creates a new table called "+matcher3.group(1)+" The table has "+fileHelper.checkSubRegex(subregex,command1).size()+" columns, called:");
          //System.out.println(fileHelper.checkSubRegex(subregex,command1).toString());
        }
         
        else if (matcher4.matches()) { 
           
          String subregex = "'(.*?)'";
          String tableName= matcher4.group(1);
          Table table1 = (Table)tableDB.get(tableName);
          ArrayList newRow= (ArrayList) fileHelper.checkSubRegex(subregex,command1);
          table1.addRow(newRow);
           
          tableDB.remove(matcher4.group(1));
          tableDB.put(tableName, table1);
          int rowNum= table1.howManyRows();
           
        
          System.out.println("INSERT 0 1");
               
           
          
        
          //if (fileHelper.checkSubRegex(subregex,command1).size()<=1){
           //System.out.println("It inserts a new row into the table called "+matcher4.group(1)+" The row has the 1 value:");  
          //}          
          //else {
          // System.out.println("It inserts a new row into the table called "+matcher4.group(1)+" The row has the "+fileHelper.checkSubRegex(subregex,command1).size()+" values:");  
           //}
           //System.out.println(fileHelper.checkSubRegex(subregex,command1).toString());
        }
         
        else if (matcher5.matches()) { 
         
        String subregex1 = "(\\w+) (?:AS|as)";
        String subregex2 = " (?:AS|as) (\\w+)";
        String subregex3 = "INTO (\\w+)";
        String subregex4 = "FROM (\\w+)";
         
        //create new table2
        Table table2 = new Table();
        //add a header to the new table2
        table2.addToHeader(fileHelper.checkSubRegex(subregex2,command1));
         
        // get table name from old existing tableX
        String tableName1= (String)fileHelper.checkSubRegex(subregex4,command1).get(0);
        // get old tableX and put it in a new table1
        Table table1 = (Table)tableDB.get(tableName1);
        //header parts to move from tableX to new table
        ArrayList<String> x = fileHelper.checkSubRegex(subregex1,command1);
          
         
        for (String z : x) { 
              List a = (List) table1.getColumn(z);
              table2.addColumn((List) a);
            }
         
        String newTableName = (String)fileHelper.checkSubRegex(subregex3,command1).get(0);
        tableDB.put(newTableName,(Table) table2);
         
        System.out.println("SELECT " +table2.howManyRows());
                 
        //System.out.println("So... It creates a new table called "+fileHelper.checkSubRegex(subregex3,command1).get(0)+" ");
        //System.out.println("The table has "+ fileHelper.checkSubRegex(subregex2,command1).size()+" columns, called:");
        //System.out.println(fileHelper.checkSubRegex(subregex2,command1).toString());
        //System.out.println("The new table has all the rows from the table called "+fileHelper.checkSubRegex(subregex4,command1).get(0));
        //System.out.println("but only the "+fileHelper.checkSubRegex(subregex1,command1).size()+" columns called:");
        //System.out.println(fileHelper.checkSubRegex(subregex1,command1).toString());
        }
         
        else if (matcher6.matches()) { 
             
        Table t1 = (Table) tableDB.get(matcher6.group(2));
        Table t2 = (Table) tableDB.get(matcher6.group(3));
        int size1=t1.howManyRows();
        int size2=t2.howManyRows();
        Table ftable = new Table();
        ftable.addToHeader(t1.getHeader());
        ftable.addToHeader(t2.getHeader());
        int i;
         for(i=0; i<size1;i++){
            int x;
            for(x=0; x<size2; x++) {
            List n = new ArrayList<String>();
            n.addAll(t1.getRow(i));
            n.addAll(t2.getRow(x));
            ftable.addRow(n);            
            } 
        }
         
        ftable.joinedNoMatch(matcher6.group(4), matcher6.group(5));
        tableDB.put(matcher6.group(1), ftable);
        System.out.println("SELECT "+ftable.howManyRows());
                   
        //System.out.println("Wow... It creates a new table called "+matcher6.group(1));
        //System.out.println("The columns are all those from the table "+matcher6.group(2)+" followed by all those from "+matcher6.group(3));
        //System.out.println("The rows will be all the ways to pair a row from "+matcher6.group(2)+" with a row from "+matcher6.group(3));
        //System.out.println("but only keeping those where the value of "+matcher6.group(4)+" is the same as the value of "+matcher6.group(5));
        }
          
          
    }
}