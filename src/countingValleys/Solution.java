package countingValleys;

/*
An avid hiker keeps meticulous records of their hikes. During the last hike that took exactly  steps,
for every step it was noted if it was an uphill, , or a downhill,  step. Hikes always start and end at
sea level, and each step up or down represents a  unit change in altitude. We define the following terms:

    A mountain is a sequence of consecutive steps above sea level, starting with a step up from sea level and ending
    with a step down to sea level.
    A valley is a sequence of consecutive steps below sea level, starting with a step down from sea level and ending
    with a step up to sea level.
    Given the sequence of up and down steps during a hike, find and print the number of valleys walked through.

    Example



    The hiker first enters a valley  units deep. Then they climb out and up onto a mountain  units high.
    Finally, the hiker returns to sea level and ends the hike.
    Function Description
    Complete the countingValleys function in the editor below.
    countingValleys has the following parameter(s):
    int steps: the number of steps on the hike
    string path: a string describing the path
    Returns
    int: the number of valleys traversed
    Input Format
    The first line contains an integer , the number of steps in the hike.
    The second line contains a single string , of  characters that describe the path.
        Constraints
*/








import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */

    public static int countingValleys(int steps, String path) {
        char[] arr = path.toCharArray();
        int seaLevel = 0;
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'D') seaLevel--;
            else if (arr[i] == 'U') {
                if (seaLevel+1 == 0) counter++;
                seaLevel++;
            }
        }
        return counter;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = Result.countingValleys(steps, path);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
