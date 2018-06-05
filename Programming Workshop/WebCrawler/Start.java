import java.util.InputMismatchException;
import java.util.Scanner;

/** The Start class takes the user's input to start the crawler's action.
  * The user needs to input a valid Wikipedia url to start from  
  * and number to specify the maximum amount of webpages to crawl on.
  */


public class Start {


 /** This is the main method which makes use of .crawl method.
   * @param args Unused.
   * @return Nothing.
   */
         public static void main(String[] args) {

            Crawler cr = new Crawler();
            System.out.println("Hi, I'm Crawler");
            System.out.println("Where should I start crawling? Enter a Wikipedia url:");

            Scanner inputReader = new Scanner(System.in);

            String url = inputReader.nextLine();
                  
            // if the user enters anything different than a Wikipedia url
            // the program keeps looping until a valid Wikipedia url is provided.
             while (!url.matches("http[s]?:\\/\\/(.+).wikipedia.org(.*)?")) {

                System.out.println("That's not a Wikipedia url...");
                System.out.println("Please enter a valid Wikipedia url:");

                url = inputReader.nextLine();

            }

            System.out.println();

            int number = -1;
            while( number < 0) {
                try {
                    System.out.print("How many websites should I crawl on? Enter a number:");
                    number = inputReader.nextInt();
                }
                // if the user types in anything but an integer,
                // it will throw the following exception:
                catch (InputMismatchException im) {
                    System.err.println("Incorrect entry. Please enter a positive number:");

                    // clear the keyboard buffer
                    inputReader.nextLine();
                }
            }

            try {
                cr.crawl(url, number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
