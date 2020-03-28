package regioncounter;

//Weighted + Path compression + quick union -> O(N+M log*N) : log*N is a funny operator means 
//	the number of log of N to reach 1
public class Solution {

	private int[] id;
	private int[] sz; // union by size
	
	public Solution(int[][] array) {
		id = new int[array.length * array[0].length];
		sz = new int[array.length * array[0].length];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
			sz[i] = 1;
		}
		
	}
	private void union(int p, int q) {
		System.out.println("union is called " + p + " " + q);
		int i = root(p);
		int j = root(q);
		if (i == j) return;
		if (sz[i] < sz[j]) { //Smaller size merge to bigger tree
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}
	
	private int root(int i) {
		while(i != id[i]) {
			id[i] = id[id[i]]; //halving path length -> more flat tree
			i = id[i];
		}
		return i;
	}

	private int getIndex(int i, int j, int n) {
		return i*n+j;
	}
	private boolean inside(int i, int j, int m, int n) {
		if (i < 0 || j < 0 || i >= m || j >= n)
			return false;
		return true;
	}
	public int regionCounter(int[][] array) {
		int m = array.length;
		int n = array[0].length;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (inside(i-1, j, m, n) && array[i][j] == array[i-1][j]) union(getIndex(i, j, n), getIndex(i-1, j, n));
				if (inside(i, j-1, m, n) && array[i][j] == array[i][j-1]) union(getIndex(i, j, n), getIndex(i, j-1, n));
				if (inside(i, j+1, m, n) && array[i][j] == array[i][j+1]) union(getIndex(i, j, n), getIndex(i, j+1, n));
				if (inside(i+1, j, m, n) && array[i][j] == array[i+1][j]) union(getIndex(i, j, n), getIndex(i+1, j, n));
			}
		}
		
		int counter = 0;
		int i = id.length-1;
		while (i >= 0) {
			if (i == id[i]) counter++;
			i--;
		}
		return counter;
	}
	
	public static void main(String[] ar) {
		int[][] array = {
			{1,2,2},
			{1,7,2},
			{1,1,2}
		};
		
		Solution regions = new Solution(array);
		System.out.println(regions.regionCounter(array));
	}
}
