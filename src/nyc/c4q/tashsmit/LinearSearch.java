package nyc.c4q.tashsmit;

import java.util.ArrayList;

/**
 * Created by c4q-tashasmith on 3/29/15.
 */
public class LinearSearch {

    //main - to test code
    public static void main(String[] args) {

        ArrayList<Integer> myArray = new ArrayList<Integer>();

        myArray.add(10);
        myArray.add(8);
        myArray.add(3);
        myArray.add(5);
        myArray.add(5);
        myArray.add(5);
        myArray.add(5);

        System.out.println(LinearSearch.search(5, myArray));

    }
    //this method will find the first occurrence of the given number
    //in the given array list
    public static int search(int index, ArrayList<Integer> myList) {

        int iterate = 0;
        int numIndex = 0;
        boolean foundSomething = false;

        for (int nums : myList) { //iterate through the arrayList

            iterate++;

            if (index == nums) { //if there is a match
                numIndex = iterate - 1; //minus one here since the first position starts at 0 not 1
                foundSomething = true;
                break;
            }
        }

        if (foundSomething == false) //if nothing was ever found
            return -1;
        else
            return numIndex; //return numIndex which represents where the number was first found


    }
}
