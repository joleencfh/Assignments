import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.io.FileNotFoundException;


public class CashRegister
{
    Prep prep;
    PrepTools pts;
   
     
     
 
    
    public CashRegister(String x, String y) throws FileNotFoundException
    {
         
        prep= new Prep();
        pts = new PrepTools();
        prep.anNPriceList();
        prep.anDPriceList();
              
    }
 
    
    public void printReceipt(String barcodeFilename) 
    {
        ArrayList x = new ArrayList<String>();
        ArrayList initialList= new ArrayList<String>();
        try {initialList = pts.readFile("(\\d \\d{6} \\d{6})", barcodeFilename,(ArrayList) x);} 
        catch (FileNotFoundException e) {
        System.out.println("The file name is not valid.");
        }
                 
        //
        HashMap totamount = new HashMap<String, Integer>();
         
        //creo la HashMap delle occurrences
        for (String item : (ArrayList<String>) initialList) { 
            int occurrences = Collections.frequency(initialList, item);
            totamount.put(item, occurrences);
             
        }
     
     
    ArrayList<ArrayList<String>> finalList= prep.getCategoryLList();
         
    for (String barcd : (ArrayList<String>) initialList) {
        prep.putInCat(barcd, finalList);
    }
    // e fino a qui ho una AL di AL in cui il primo elemento di ogni AL è il nome della categoria e il resto sono gli articoli come barcodes.
     
    for (ArrayList single : finalList) {
        if (single.size() > 1) {
        prep.printCat(single);
        prep.printSpace();
        } 
         
    }
     
    //PARTE FINALE
     
    prep.printSpace();
    prep.printLine();
    prep.printSpace();
    prep.subTot(initialList);
    prep.printSpace();
    prep.rabat(initialList);
    prep.printSpace();
    prep.tot(initialList);
    prep.printSpace();
    prep.marchi();
    prep.printSpace();
    prep.tasse();
     
    }
}
