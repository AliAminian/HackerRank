package repeatedString;
/*
There is a string, , of lowercase English letters that is repeated infinitely many times.
Given an integer, , find and print the number of letter a's in the first  letters of the infinite string.
Example

    The substring we consider is , the first  characters of the infinite string. There are  occurrences of a in the substring.
    Function Description
    Complete the repeatedString function in the editor below.
    repeatedString has the following parameter(s):
    s: a string to repeat
    n: the number of characters to consider
    Returns
    int: the frequency of a in the substring
    Input Format
    The first line contains a single string, .
    The second line contains an integer, .
    Constraints
        For  of the test cases, .
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
     * Complete the 'repeatedString' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. LONG_INTEGER n
     */

    public static long repeatedString(String s, long n) {
        char[] arr = s.toCharArray();
        long counter_complete = 0L;
        long counter_mod = 0L;

        long mod = n % arr.length;
        long div = n/arr.length;

        long i = 0L;
        for (; i < (long)arr.length && i < n; i++) {
            if (arr[(int)i] == 'a') counter_complete++;
            if (arr[(int)i] == 'a' && i < mod) counter_mod++;
        }
        if (arr.length <= i && i <= n && div > 0) {
            counter_complete = div * counter_complete + ((mod==0)? 0 : counter_mod);
        }
        return counter_complete;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        long n = Long.parseLong(bufferedReader.readLine().trim());

        long result = Result.repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
