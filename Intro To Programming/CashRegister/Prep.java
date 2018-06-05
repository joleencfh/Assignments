import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;
import java.util.HashSet;
import java.util.Collections;


public class Prep
{
    PrepTools pt;
    ArrayList<String> nPricesList;
    ArrayList<String> dPricesList;
    HashMap<String, String> names;
    HashMap<String, String> names1;
    HashMap<String, Float> nPrice;
    HashMap<String, Float> dPrice;
    HashMap<String, Integer> limit;
    HashMap<String, String> category;
    ArrayList<String> discountedItems;
    float totale;
 
    /**
     * Constructor for objects of class Prep
     */
    public Prep () throws FileNotFoundException
    {
      pt= new PrepTools();
      nPricesList = new ArrayList<String>();
      dPricesList= new ArrayList<String>();
      names = new HashMap<String, String>();
      names1 = new HashMap<String, String>();
      nPrice = new HashMap<String, Float>();
      dPrice =new HashMap<String, Float>();
      limit = new HashMap<String, Integer>();
      category = new HashMap<String, String>();
      discountedItems = new ArrayList<String>();
      float totale = 0;
       
         
      pt.readFile("((.+),(.+),(.+),(.+),(.+))","prices.txt",nPricesList);
      pt.readFile("((.+),(.+),(.+),(.+))", "discounts.txt", dPricesList);
       
    } 
     
 
   
    public void anNPriceList()
    {
        for (String item : nPricesList) {
        Pattern pattern = Pattern.compile("(.+),(.+),(.+),(.+),(.+)");
        Matcher matcher = pattern.matcher(item);
        matcher.matches();
        String barcode = matcher.group(1);
        String categoria = matcher.group(2);
        String name = matcher.group(3);
        String price = matcher.group(4) +"."+ matcher.group(5);
        float prezzo = Float.parseFloat(price);
                         
            names.put(barcode, name);
            names1.put(name, barcode);
            category.put(barcode, categoria);
            nPrice.put(barcode, prezzo);
           
         
        }
        // così ho riempito le seguenti maps: names, category, nPrice
    }
     
    public void anDPriceList(){
        for (String item : dPricesList) {
        Pattern pattern = Pattern.compile("(.+),(.+),(.+),(.+)");
        Matcher matcher = pattern.matcher(item);
        matcher.matches();
        String barcode = matcher.group(1);
        String limite1 = matcher.group(2);
        int limite = Integer.parseInt(limite1);
        String dPrezzo = matcher.group(3) +"."+ matcher.group(4);
        float prezzoScontato = Float.parseFloat(dPrezzo);
   
            discountedItems.add(barcode);
            limit.put(barcode, limite);
            dPrice.put(barcode, prezzoScontato);   
                 
        // così ho riempito le seguenti maps: limit, dPrice.
        // e ho anche riempito l'ArrayList discountedItems.    
    } 
         
  } 
    
 public void printLine() {
     String line = new String(new char[37]).replace('\0', '-'); 
     System.out.println(line);
     // così stampo la linea di trattini
    }
     
 public void printSpace() {
     String line = new String(new char[37]).replace('\0', ' '); 
     System.out.println(line);
     // così stampo la linea di trattini
    }
 
 public void printCat(ArrayList<String> x) {
    String cat = "* "+x.get(0)+" *";
    String X = String.format( "%-" + (40+cat.length())/2 + "s", cat); 
    System.out.printf("%40s\n", X); 
    x.remove(0);
     
    HashMap quanti = new HashMap<String, Integer>();
    for (String y : (ArrayList<String>) x) {
        int freq = Collections.frequency(x, y); 
        quanti.put(y, freq);
            
        }
            
    HashSet a = new HashSet(x);
    ArrayList b = new ArrayList (a);
     
    ArrayList n = new ArrayList<String>();
    for (String d : (ArrayList<String>) b) {
        String nome = names.get(d);
        n.add(nome);
                  
    }
    Collections.sort(n);
     
    //Qui ho la lista per nome e in ordine alfabetico. Adesso devo stamparla.
    for (String c : (ArrayList<String>)n) {  
        String bar = names1.get(c);
        int amount = (Integer) quanti.get(bar);
        Float nprice = nPrice.get(bar);
        String s = pt.stringPrice(nprice);
         
        if ((amount==1&&discountedItems.contains(bar)&& limit.get(bar)>1)|(amount==1 &&!discountedItems.contains(bar)) ) {
        System.out.printf("%-20s%20s%n", c, s+" ");     
        }
         
        else if (amount>1 && !discountedItems.contains(bar)) {
        System.out.println(c);
        String finalPrice= pt.stringPrice(nprice*amount);
        System.out.printf("%-20s%20s%n","  "+amount+" x "+s , finalPrice+" ");      
        }
   
        if (amount>1 && discountedItems.contains(bar)) {
        Float dprice = dPrice.get(bar);
        Float sconto = (nprice-dprice)*amount;
        String t = pt.stringPrice(dprice);
        String r = pt.stringPrice(sconto);
        System.out.println(c);
        String finalPrice= pt.stringPrice(nprice*amount);
        System.out.printf("%-20s%20s%n","  "+amount+" x "+s , finalPrice+" "); 
        System.out.printf("%-20s%20s%n","RABAT",r+"-");         
        }
         
        else if (amount==1&& discountedItems.contains(bar)&&limit.get(bar)==1){
        Float dprice = dPrice.get(bar);
        Float sconto = (nprice-dprice)*amount;
        String r = pt.stringPrice(sconto);
        System.out.printf("%-20s%20s%n", c, s+" ");
        System.out.printf("%-20s%20s%n","RABAT",r+"-"); 
        }
    }
     
    }
 public ArrayList<ArrayList<String>> getCategoryLList() {
    ArrayList x = new ArrayList<String>(category.values());
    HashSet y = new HashSet(x);
    ArrayList z = new ArrayList<String>(y);
    Collections.sort(z);
         
    ArrayList<ArrayList<String>> catLList = new ArrayList<ArrayList<String>>();
    for (String i : (ArrayList<String>) z) {
         int j;
         ArrayList a = new ArrayList<String>(); 
         a.add(i);
         catLList.add(a);
          
    }
     
    return catLList;  
    //così ho creato una Lista di liste in cui il primo elemento di ogni lista è il nome della categoria. Hell Yeah.
   
    } 
     
 public void putInCat(String barcode, ArrayList<ArrayList<String>> a) {
    for (ArrayList<String> single : a) {
         String s = single.get(0);
         String cate = category.get(barcode);
         if (s.equals(cate)) {
            single.add(barcode);
            }
    }
    }
     
 public void subTot(ArrayList x) {
    Float totPrice= 0.0f;
     for (String barcode : (ArrayList<String>) x) {
    totPrice+= nPrice.get(barcode);
    }
    String pprice= pt.stringPrice(totPrice);
    System.out.printf("%-20s%20s%n","SUBTOT",pprice+" "); 
    } 
 public void tot(ArrayList x) {
    Float sconto = 0.0f;
    for (String barcode : (ArrayList<String>) x){
       int freq = Collections.frequency(x, barcode);
       if (discountedItems.contains(barcode) && freq>=limit.get(barcode)) {
        sconto+= dPrice.get(barcode);
        }
       else { 
        sconto+= nPrice.get(barcode);
        }
         
    }
    totale=sconto;
    String sco= pt.stringPrice(sconto);
    System.out.printf("%-20s%20s%n","TOTAL",sco+" ");
    } 
     
 public void rabat(ArrayList x) {
    Float sconto = 0.0f;
    for (String barcode : (ArrayList<String>) x){
       int freq = Collections.frequency(x, barcode);
       if (discountedItems.contains(barcode) && freq>=limit.get(barcode)) {
        sconto+= nPrice.get(barcode)-dPrice.get(barcode);
    }
   
   }
   if (sconto > 0) {
   String sco= pt.stringPrice(sconto);
   System.out.printf("%-20s%20s%n","RABAT",sco+" ");}
 }
  
 public void marchi() {
    float x = totale/50;
    String num = String.format ("%.0f", Math.floor(x));
    if (x>=1) {
    System.out.println("KØBET HAR UDLØST "+num+" MÆRKER");}
    else {System.out.println("KØBET HAR UDLØST 0 MÆRKER");}
    }
     
 public void tasse() {
    float x = (totale/100)*20;
    String num = pt.stringPrice(x);
    System.out.printf("%-20s%20s%n","MOMS UDGØR",num+" ");
     
    }
}