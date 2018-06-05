import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Tentacles {


    private ArrayList<String> collectedLinks = new ArrayList<String>();
    private Document htmlDoc;
    private String fakeUserAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0.1) Gecko/20121011 Firefox/16.0.1";
    private PdfCreatorrr pdfCreator;

    /**
     * The following method connects to a url, retrieves a page ("Document") and
     * collects all the links that the page contains
     *
     * @param url the url the crawler uses as a starting point
     */
    public void act(String url) {

        try {
            Connection connection = Jsoup.connect(url).userAgent(fakeUserAgent);
            /*
            the .get() method executes the request as a GET, and parses the result (the result is a "Document").
            */
            Document htmlDocument = connection.get();
            htmlDoc = htmlDocument;


            /**
            the .response() method gets the response, once the request has been executed
            200 is the HTTP "OK" status code
             */

            if (connection.response().statusCode() == 200) {
                System.out.println("Crawler has crawled on this web page " + url + ".");
            }

            /**
            the .contentType() method gets the response content type (for example a String)
            (That's why the .contains(...) method works here)
            If no ContentType is specified, the default is text/HTML
            which means that the retrieved content type is something other than HTML
            (not what we want!)
             */

            else if (!connection.response().contentType().contains("text/html")) {

                System.out.println("*EPIC FAIL* Crawler retrieved something other than HTML.");

            }

            /**the .select() method on the Document "htmlDocument" returns a list of Elements.
              Jsoup elements support a CSS (or jquery) like selector syntax
              to find matching elements.
              Links in CSS are written starting with "a[href]".
              Ex.: a[href="http://perishablepress.com"]
            
             The jsoup class "Elements" extends ArrayList<Element>
             */
            Elements foundLinks = htmlDocument.select("a[href]");

            for (Element link : foundLinks) {

                /** This is the reason why we need to extract the "absolute url" (.absUrl(..))
                 from the links we have found:
                 "Less-sophisticated bots are (...) likely to get confused by relative links,
                  even under typical circumstances. The only way to ensure that you've
                  eliminated these uncertainties is by utilizing absolute URLs, and doing so
                  consistently across the site."

                 Long story short: the .absUrl(..) method gets
                 an absolute URL from a URL attribute that may be relative.
                 */

                collectedLinks.add(link.absUrl("href"));
            }

                /**
                Creating a pdf of the page we're currently visiting

                @url  The page we're currently visiting
                 */
                pdfCreator = new PdfCreatorrr();
                pdfCreator.printPdf(url);



        } catch (IOException e) {

            System.out.println("Error: the HTTP request didn't go as expected." + e);
        }


    }


    /**
   * This method is used to get the list of collected links. 
   * @return ArrayList<String> It returns an ArrayList of url Strings.
   */
    public ArrayList<String> getLinks() {

        return collectedLinks;
    }


}
