package binomialcoefficient;

import java.math.BigDecimal;

public class Solution {
	public static long binomialCoefficient(int N, int K) {
		
		//Considering a limit for early exit situation -> return -1;
		BigDecimal upperLimit = new BigDecimal("1000000000");

		BigDecimal[][] ar = new BigDecimal[N + 1][K + 1];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= Math.min(i, K); j++) {
				//ar[i][j] = BigDecimal.ZERO;
				if (j == 0 || j == i) {
					ar[i][j] = BigDecimal.ONE;
				} else {
					ar[i][j] = ar[i - 1][j - 1].add(ar[i - 1][j]);
					if (ar[i][j].compareTo(upperLimit) > 0) {
						return -1;
					}
				}
			}
		}

		return ar[N][K].intValue();
	}

	public static void main(String[] arg) {
		int n = 39, k =20;
		System.out.println("Value of C(" + n + "," + k + ") is " + binomialCoefficient(n, k));
	}
}
