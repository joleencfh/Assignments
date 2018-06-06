import java.util.ArrayList;
import java.util.Scanner;


public class InputReader
{
    private Scanner reader;

    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public InputReader()
    {
        reader = new Scanner(System.in);
    }

   
    public ArrayList<String> getInput() 
    {
        System.out.print("> ");                
        String inputLine = reader.nextLine().trim();

        String[] wordArray = inputLine.split(" ");  

        ArrayList<String> words = new ArrayList<>();
        for(String word : wordArray) {
            words.add(word);
        }
        return words;
    }
    public String getOne() {
    System.out.print("> ");                
        String inputChar = reader.nextLine().trim();
        
        return inputChar;
    }
    
}
