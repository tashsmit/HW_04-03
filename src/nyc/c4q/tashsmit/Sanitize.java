package nyc.c4q.tashsmit;
/**
 * readAll, stringToURL, and get methods were created by Alex Samuel
 * sanitize method created by c4q-tashasmith on 4/2/15.
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Sanitize
{
    /**
     * Reads from 'reader' by 'blockSize' until end-of-stream, and returns its complete contents.
     */
    private static String readAll(InputStreamReader reader, int blockSize) throws IOException
    {
        final char buffer[] = new char[blockSize];
        StringBuilder builder = new StringBuilder();
        while(true)
        {
            final int readSize = reader.read(buffer);
            if(readSize >= 0)
            {
                builder.append(buffer, 0, readSize);
            }
            else
            {
                break;
            }
        }
        return builder.toString();
    }

    /**
     * Returns from 'reader' until end-of-stream, and returns its complete contents.
     */
    private static String readAll(InputStreamReader reader) throws IOException
    {
        return readAll(reader, 1024 * 1024);
    }

    /**
     * Interprets a string as a URL.  If the string isn't a valid URL, prints an error message and returns null.
     */
    public static URL stringToURL(String string)
    {
        try
        {
            return new URL(string);
        }
        catch(MalformedURLException exception)
        {
            System.err.println("invalid URL: " + string + ": " + exception);
            return null;
        }
    }

    /**
     * Retrieves the body of a URL.
     * <p/>
     * Opens a connection to the URL, makes a request, and retrieves the response.  Returns the body.  If the
     * URL cannot be opened or the response cannot be read, prints an error message and returns null.
     */
    public static String get(URL url)
    {
        try
        {
            final URLConnection connection = url.openConnection();
            final InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            try
            {
                return readAll(reader);
            }
            finally
            {
                reader.close();
            }
        }
        catch(IOException exception)
        {
            System.err.println("can't open URL: " + url + ": " + exception);
            return null;
        }
    }

    //this method takes a string, converts it to a URL type and extracts all displayable information
    //besides anything in the script tags. Returns text.
    public static String sanitize(String html)
    {
        URL myURL = stringToURL(html);

        String text = get(myURL);

        //find the substring of <script> and </script>
        //the loop will continue to run until there are no more open script tags
        while(true)
        {
            int openScript = text.indexOf("<script"); //find where the next open script tag appears
            if(openScript == - 1)  //if there are no more open script tags, leave loop
            {
                break;
            }
            int closeScript = text.indexOf("</script>"); //find where the next close script tag appears

            //save everything inside the script tag to firstScript variable
            String firstScript = text.substring(openScript, closeScript + 9);
            //remove everything from insdie the script tag by replacing it with an empty string
            text = text.replace(firstScript, "");
        }

        return text;
    }

    //main - to test code
    public static void main(String[] args) {

        System.out.println(sanitize("http://www.google.com"));




    }

}
