import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Table
{
    
    private List<String> header;
    private ArrayList<List<String>> rows;
    private DBHelper dbHelper;
      
  
    /**
     * Constructor for objects of class Table
     */
    public Table()
    {
        header = new ArrayList<String>();
        rows = new ArrayList<List<String>>();
                
    }
  
  
       public List getHeader(){
       return header;
    } 
      
    public void addToHeader(List x) 
    {
        header.addAll(x);
          
    }
      
    public void addRow(List x) {
      
        rows.add(x);
      
    }
     
      
    public List getRow(int index) {
        return (List) rows.get(index);
    }  
      
    public List getColumn(String x) {
        List column = new ArrayList<String>();
        int i= header.indexOf(x);
        for (List row : rows) { 
            String a = (String) row.get(i);
            column.add(a);
          
        }
        return column;
    }
      
    public void addColumn(List<String> y) {
        if (rows.isEmpty()) {
            int i = 0;
            int size = y.size();
            for (i=0 ; i<size; i++) {
            List x = new ArrayList<String>();
            x.add(y.get(i));
            rows.add(x);
            } 
         
        } 
        else {
          int i;
          int size= y.size();
          for (i=0; i<size; i++) {
            List row=rows.get(i);
            row.add(y.get(i));
            }
                  
        }
    }
          
      
     
      
    public boolean checkHeaderDoubles() {
        ArrayList doubles= new ArrayList<String>();
        boolean valid;
        String error;
        for (String x : header) {
           int i= Collections.frequency(header, x);
           if (i>1) { 
               doubles.add("ERROR:  column \""+x+"\" specified more than once");
                  
           }
        }
                
      if (!doubles.isEmpty()) { 
          System.out.println(doubles.get(0));
          valid = true;          
            
        }  else {valid = false;}
      return valid;
    }     
      
    public int howManyRows(){
      return rows.size();
    }
      
    public void joinedNoMatch(String x, String y){
        int a = header.indexOf(x);
        int b = header.indexOf(y);
        if(!rows.isEmpty()){ 
        ArrayList<List<String>> w = new ArrayList<List<String>>();
        for (List row : rows) {
        String el1 = (String) row.get(a);
        String el2 = (String) row.get(b);
          if (!el1.equals(el2)) {
             w.add(row);
          } 
        } 
        rows.removeAll(w);
        } 
    }
      
    public void printTheTable() {
                      
       dbHelper.printTable(header, rows);
    } 
     
}