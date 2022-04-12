import java.util.*;

/*
You are given a zero-indexed array A consisting of n > 0 integers; you must return
the number of unique values in array A.
 */
public class NumberOfArrayUniqueElements {
    public int solution_HashMap (int[] A) {
        HashMap<Integer, Integer> dict = new HashMap<>();
        Arrays.stream(A).forEach((el) -> {
            if (dict.get(el) == null) dict.put(el, 1);
            else dict.put(el, dict.get(el) + 1);
        });

        int counter = 0;
        for (Map.Entry<Integer, Integer> ent: dict.entrySet()) {
            if (ent.getValue() == 1) counter++;
        }
        return counter;
    }

    public int solution_sorting (int[] A) {
        int counter = 1;
        Arrays.sort(A);
        for (int i = 1; i < A.length; i++) {
            if (A[i] != A[i-1]) counter++;
        }
        return counter;
    }

    public int solution_emptyA(int[] A) {
        if (A.length == 0)
            return 0;
        if (A.length == 2 && A[0] == A[1])
            return 1;
        if (A.length == 2 && A[0] != A[1])
            return 2;

        int counter = 1;
        Arrays.sort(A);
        for (int i = 1; i < A.length; i++) {
            if (A[i] != A[i-1]) counter++;
        }
        return counter;
    }

    public static void main (String[] arg) {
        NumberOfArrayUniqueElements uniqueElements = new NumberOfArrayUniqueElements();
        int count = uniqueElements.solution_sorting(new int[]{1,2,2,2,3,0,4,5,0,5});
        System.out.println(count);
    }
}
