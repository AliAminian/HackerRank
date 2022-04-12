import java.util.*;

public class MaxPerimeterTriangle {
    public int solution(int[] A) {
        int counter = 0;
        if (A.length == 0 || A.length == 1 || A.length == 2)
            return counter;

        Arrays.sort(A);
        for (int i = A.length-1 ; i >= 2; i--) {
            if ((long)(A[i]) >= (long)(A[i-1] + A[i-2])) continue;
            if ((long)(A[i-1]) >= (long)(A[i] + A[i-2])) continue;
            if ((long)(A[i-2]) >= (long)(A[i] + A[i-1])) continue;
            counter = 1;
        }
        return counter;
    }

    public static void main (String[] arg) {
        MaxPerimeterTriangle uniqueElements = new MaxPerimeterTriangle();
        int count = uniqueElements.solution(new int[]{1, 1, 2, 3, 5});
        System.out.println(count);
    }
}
