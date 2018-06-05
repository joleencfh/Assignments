import com.lowagie.text.DocumentException;

import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfCreatorrr {



 
        /**
   * This method is used to print a pdf version of a webpage.
   * @param url The url of the webpage to be converted into pdf.
   */
        public void printPdf (String url) {
            try {

                /** The following iTextRenderer instance will create a pdf file
                 * from an html document.
                 */
                ITextRenderer iTextRenderer = new ITextRenderer();

                /**
                 * Passing the url as a parameter in the .setDocument method
                 * allows the iTextRenderer to parse the html found at that url
                 */
                iTextRenderer.setDocument(url);
                iTextRenderer.layout();

                /** To generate a pdf file name that reflects the content of each page
                 * the url string needs to be modified to eliminate illegal characters such as
                 * dots, colons and backslashes. The easiest way is to use regex and substitute
                 * all non-alphanumerical characters with an "underscore".
                 * This way the name of the web page will still be readable.
                 * @pdfFileName is an acceptable version of the url string, without the
                 *              illegal characters
                 */
                String pdfFileName = url.replaceAll("[^A-Za-z0-9]", "_");

                /** The FileOutputStream prepares to generate a pdf file and gives it a filename
                 *  ".pdf" starting from the modified url string.
                 */

                FileOutputStream fileOutputStream =
                        new FileOutputStream(new File(pdfFileName+".pdf"));

                /** The iTextRenderer creates a pdf file with the fileOutputStream
                  */
                iTextRenderer.createPDF(fileOutputStream);
                fileOutputStream.close();

            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

