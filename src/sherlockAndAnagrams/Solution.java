package sherlockAndAnagrams;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

	static String getStringKey(String s) {
		char[] sArray = s.toCharArray();
		Arrays.sort(sArray);
		return new String(sArray);
	}

	// Complete the sherlockAndAnagrams function below.
	static int sherlockAndAnagrams(String s) {
		Map<String, Integer> patterns = new HashMap<>();
		for (int i = 0; i <= s.length(); i++) {
			for (int j = i; j <= s.length(); j++) {
				String subString = getStringKey(s.substring(i, j));
				if (subString.length() > 0) {
					Integer value = patterns.get(subString);
					if (value == null) {
						patterns.put(subString, 1);
					} else {
						patterns.put(subString, value + 1);
					}
				}
			}
		}
		int counter = 0;

		for (Map.Entry<String, Integer> ent : patterns.entrySet()) {
			int count = ent.getValue();
			if (count > 1) {
				counter += (count * (count - 1) / 2);
			}
		}
		return counter;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int qItr = 0; qItr < q; qItr++) {
			String s = scanner.nextLine();

			int result = sherlockAndAnagrams(s);

			bufferedWriter.write(String.valueOf(result));
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		scanner.close();
	}
}
