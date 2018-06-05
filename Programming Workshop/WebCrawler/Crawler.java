import java.util.ArrayList;
import java.util.HashSet;

public class Crawler {


    /**
     * seenSites is an HashSet to avoid duplicates.
     * The order of sites displayed doesn't matter (other reason why this data structure was chosen).
     */
    private HashSet<String> seenSites;

    /**
     * sitesToSee is an ArrayList because in this case order matters:
     * the crawler should visit sites in the order they were found.     *
     */
    private ArrayList<String> sitesToSee;

    public Crawler() {


        seenSites = new HashSet<String>();
        sitesToSee = new ArrayList<String>();
    }
   
    /**
   * This method is used to crawl on Wikipedia pages. 
   * @param url The url the crawler should start from.
   * @param maxNumSites  The maximum number of webpages the crawler should visit.
   */

    public void crawl(String url, int maxNumSites) throws InterruptedException {


        while (seenSites.size() < maxNumSites) {

            Tentacles tentacles = new Tentacles();

            /**
             * Here the Crawler finds out which website it should go to.
             *
             * @currentSite the website crawler should go to.
             *
             * If the sitesToSee list is empty, crawler will start from the url given as a parameter.
             * If the sitesToSee list is not empty it will go visit the first website of that list
             * (sitesToSee.remove(0))
             *
             */
            String currentSite=null;

            if(sitesToSee.isEmpty())
            {
                currentSite = url;
                seenSites.add(url);
            }
            else
            {
                currentSite = sitesToSee.remove(0);
                seenSites.add(currentSite);
            }

          /**
           Once the crawler has found out which website to visit, its "tentacles" will do all the work
           (connect to the page, print a pdf of the page, collect all the links present on the page)
           */
          tentacles.act(currentSite);

          /**
             * With a getter method the tentacles can retrieve the list of links collected on the visited page.
             */
          ArrayList<String> newLinks= tentacles.getLinks();

          /**
             * To avoid adding duplicate links to the sitesToSee list, the already visited sites are removed
           * from the list of new links (comparing the newLinks list to the seenSites list).
           * Once the duplicates are gone, the newLinks list is added to the end of the sitesToSee list.
             */

          newLinks.removeAll(seenSites);
         sitesToSee .addAll(newLinks);

            /**
             * Here the program execution takes a break for three seconds
             * to avoid visiting more than 20 websites per minute.
             */
            Thread.sleep(3000);
        }
        System.out.println("Crawler is done. Check your pdfs in the \"target\" folder.");
    }

}

