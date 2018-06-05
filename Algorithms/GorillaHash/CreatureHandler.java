import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.HashMap;


public class CreatureHandler {

    //private List<Creature> creatureList;
    private TextHandler textHandler;
    
    public CreatureHandler() {
        textHandler= new TextHandler();
    }
        
     
    public void fillName(Creature creature, String fullInfo) {
        Matcher matcher = textHandler.match(">(\\w+(\\-\\w+)?)", fullInfo);
        while (matcher.find()) {
            creature.setName(matcher.group(1));
            
        }
    }
    
    public void fillProteinSeq(Creature creature, String fullInfo) {
        Matcher matcher = textHandler.match(">.+\\n(.+\\n.+\\n.+)", fullInfo);
        while (matcher.find()) {
            creature.proteinSeq= matcher.group(1);
        }
    }
    
    // k= 20 (per lunghezza k-grams)
    public void buildKGramsList (Creature creature) {
        String str = creature.proteinSeq;
        ArrayList<String> kGrams = new ArrayList<String>();
        for (int i = 0; i < str.length() - 5 + 1; i++){
        kGrams.add(str.substring(i, i + 5));
        }
        creature.kGramsList=kGrams;
    }
     
    // d= 10,000 (per hashing)
    public void buildProfile(Creature creature) {
        // kGramsList -----> hashList
        ArrayList<Integer> hashList= new ArrayList<Integer>();
        for (String gram : creature.kGramsList){
            int hashGram= (gram.hashCode() % 100);
            hashList.add(hashGram);
        }
        // hashList ------> frequencyMap
        HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
            for (Integer dHash : hashList) {
              int prev = 0;
              if (freqMap.get(dHash)!= null){
                  prev = freqMap.get(dHash);
              }
              freqMap.put(dHash, prev+1);
             
           }
        creature.profile=freqMap;
    
      
        
    }
    
    




}