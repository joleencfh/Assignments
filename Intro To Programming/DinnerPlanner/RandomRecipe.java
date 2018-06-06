import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RandomRecipe
{
    // instance variables - replace the example below with your own
    private ArrayList<String> meatOrFish;
    private ArrayList<String> veggie;
    private ArrayList<String> random;
    private ArrayList<String> chosen;
    private Random rg;

    
    public RandomRecipe()
    {
        meatOrFish= new ArrayList<String>();
        veggie= new ArrayList<String>();
        random= new ArrayList<String>();
        chosen = new ArrayList<String>(); 
        rg= new Random();
        fillMF();
        fillV();
        fillR();
    }
    public ArrayList getRandom(ArrayList<String> words)
    {
        for (String word : words) {
            if (word.equals("M")){
            String recipe = pickOne(meatOrFish);
            chosen.add(recipe);
            }
            else if (word.equals("V")){
            String recipe = pickOne(meatOrFish);
            chosen.add(recipe);
            }
            else if (word.equals("R")){
            String recipe = pickOne(random);
            chosen.add(recipe);
            }
            else {
                System.out.println("You entered an invalid input");
            }
        }
        return chosen;
    }
    
    private void fillMF()
    {
        meatOrFish.add("asian-beef-with-snow-peas.txt");
        meatOrFish.add("baked-pork-chops.txt");
        meatOrFish.add("brown-sugar-meatloaf.txt");
        meatOrFish.add("lemon-rosemary-salmon.txt");
        meatOrFish.add("garlic-chicken.txt");
        meatOrFish.add("pork-marsala.txt");
        meatOrFish.add("salsa-chicken.txt");
        meatOrFish.add("easy-meatloaf.txt");
        
        //didn't have time to add more
    
    }
    private void fillV()
    {
        veggie.add("creamy-pasta-bake-with-cherry-tomatoes-and-basil.txt");
        veggie.add("pasta-pomodoro.txt");
        veggie.add("gourmet-mushroom-risotto.txt");
        //didn't have time to add more
    
    }
    private void fillR()
    {
        random.add("creamy-pasta-bake-with-cherry-tomatoes-and-basil.txt");
        random.add("pasta-pomodoro.txt");
        random.add("gourmet-mushroom-risotto.txt");
        random.add("asian-beef-with-snow-peas.txt");
        random.add("baked-pork-chops.txt");
        random.add("brown-sugar-meatloaf.txt");
        random.add("lemon-rosemary-salmon.txt");
        random.add("garlic-chicken.txt");
        random.add("pork-marsala.txt");
        random.add("salsa-chicken.txt");
        random.add("easy-meatloaf.txt");
        //didn't have time to add more
    
    }
   
   
    private String pickOne(ArrayList type)
    {
        
        int index = rg.nextInt(type.size());
        return (String) type.get(index);
    }
    
    }
