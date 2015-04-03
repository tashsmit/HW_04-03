package nyc.c4q.tashsmit;
/**
 * Created by c4q-tashasmith on 4/2/15.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class CharacterDistributions
{

    // this method accepts as input a Text file. It reads the contents of the file
    // and returns an ArrayList which contains the distribution/percentage of
    // characters (a-z) in the text file
    public static ArrayList<String> calculate (File textFile) {

        ArrayList<String> percentages = new ArrayList();

        String text;
        String newText = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        try {
            Scanner input = new Scanner(textFile);

            // convert file into a string by using scanner
            while (input.hasNext()) {

                text = input.next();
                text = text.toLowerCase(); //convert to lower case

                for (int i=0; i < text.length(); i++) { //for each letter that matches with a-z, append to newText variable

                    String letter = text.substring(i, i+1);
                    if (letter.matches("[a-z]"))
                        newText += letter;
                    else
                        continue;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // use alphabet string and distribution method to add each letters distribution
        // percentage to the array
        for (int i = 0; i < alphabet.length(); i++) {
            percentages.add(distribution(alphabet.charAt(i), newText));
        }
        return percentages; //return the array
    }

    //This method checks the character count and checks for the distribution percentage
    //and returns a string in the format: " a = 14.44 % "
    public static String distribution (char letter, String text) {

        int letterCount = 0;
        int totalCount= 0;
        double distribution;
        DecimalFormat df = new DecimalFormat("#.00");

        //loop through text and look for the character
        //if it is found, update letterCount
        //each iteration will update the totalCount variable
        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == letter)
                letterCount++;

            totalCount++;
        }

        //distribution calculation
        distribution = (double)letterCount / totalCount * 100;

        String newFormat = df.format(distribution);

        String distributionFormat = letter + " = " + newFormat + "%";

        return distributionFormat;
    }

    ///main - to test code
    public static void main (String [] args) {

        ArrayList<String> myArray = new ArrayList();

        File myfile = new File("/Users/c4q-tashasmith/Desktop/accesscode/HW_04-03/textfile.txt");

        myArray = calculate(myfile);

        for (String words : myArray)
            System.out.println(words);

    }
}
