import java.util.*;
import java.io.*;


public class CreatureComparator {

  private static TextHandler textHandler;
  private static CreatureHandler creatureHandler;
  private static ArrayList<Creature> creaturesList;
   
  public static void main(String [] args) {
   
    long startTime = System.currentTimeMillis();

      
    creatureHandler= new CreatureHandler();
    textHandler= new TextHandler();
    creaturesList= new ArrayList<Creature>();  
      
  // get user's input to choose file to analyse
  System.out.println("Choose between:");
  System.out.println("A. Small file");
  System.out.println("B. Big file");
  System.out.println("Please type A or B");
  System.out.println();
  System.out.println("Type END when done.");
    
  boolean finished = false;
  Scanner reader = new Scanner(System.in);
  ArrayList<String> creatureInfoList = new ArrayList<String>();
  
      System.out.print("> ");                
      String input = reader.nextLine().trim();
     
      boolean validInput=false;
      while(validInput==false) {
      if (input.contains("A")) {
            try {creatureInfoList = textHandler.getCreatureInfo("HbB_FASTAs-in.txt");}
              catch (FileNotFoundException e) {
                  System.out.println("File not found...");}
            validInput=true;
        }
        else if (input.contains("B")) {
            try {creatureInfoList = textHandler.getCreatureInfo("bigdata.txt");}
            catch (FileNotFoundException e) {
                System.out.println("File not found...");}
            validInput=true;
        }
        else {System.out.println("Invalid input. Please choose A or B");}
    }            
  
    // First: make a list of Creatures, with all fields filled in
  
    for (String creatureInfo: creatureInfoList) {
        
        Creature creature = new Creature();
        //fill in name and protein sequence
        creatureHandler.fillName(creature, creatureInfo);
        creatureHandler.fillProteinSeq(creature,creatureInfo);
        //fill in profile (vector)
        creatureHandler.buildKGramsList(creature);
        creatureHandler.buildProfile(creature);
        //add the creature to the list of creatures
        creaturesList.add(creature);
    }
        
    // Second: compare humans to every other creature
    System.out.println("RESULTS: ");
    System.out.println("");
    int x = 0;
    int size= creaturesList.size()-1;
      for (x=0; x<=creaturesList.size()-1; x++ ) {
        comparator(x);
      }
      
      long stopTime = System.currentTimeMillis();
      long elapsedTime = stopTime - startTime;
      System.out.println("elapsed seconds:" + elapsedTime);
   
    
      
  
    } 
  
  
  
  public static void comparator(int nonHumanIndex) {
     // get human and nonHuman creature objects
     Creature human = creaturesList.get(1);
     Creature
         nonHuman = creaturesList.get(nonHumanIndex);
     
     //get their profiles (freqMaps)
     Map<Integer, Integer> humanProfile = human.profile;
     Map<Integer, Integer> nonHumanProfile = nonHuman.profile; 
     
     //get a list of unique values (1...20) from both profiles
     HashSet<Integer> intersection = new HashSet<Integer>(humanProfile.keySet());
     intersection.retainAll(nonHumanProfile.keySet());
     
     double dotProduct = 0, magnitudeHuman = 0, magnitudeNonHuman = 0;
     
     //Calculate Dot Product
        for (Integer i : intersection) {
            dotProduct += humanProfile.get(i) * nonHumanProfile.get(i);
        }  
     
     //Calculate magnitude Human
        for (Integer i : humanProfile.keySet()) {
            magnitudeHuman += Math.pow(humanProfile.get(i), 2);
        }
        
     //Calculate magnitude nonHuman
         for (Integer i : nonHumanProfile.keySet()) {
            magnitudeNonHuman += Math.pow(nonHumanProfile.get(i), 2);
        }
     
     // Finally calculate similarity value:  
      double similarity = dotProduct / Math.sqrt(magnitudeHuman * magnitudeNonHuman);
      
     // ...And print results
      
      System.out.println("Human - "+nonHuman.name+" :  "+similarity);
   }
}