import java.util.ArrayList;
import java.util.Map;


public class Creature {

String allInfo;
String name;
String proteinSeq;
ArrayList<String> kGramsList;
Map<Integer, Integer> profile;

//Contructor
public Creature() {
  this.allInfo=null;
  this.name=null;
  this.proteinSeq=null;
  this.kGramsList=null;
  this.profile=null;
}

public void setName(String name){
   this.name = name;
}

}