import java.util.*;

public class MaximalOfTriples {
    public int solution(int[] A) {
        Arrays.sort(A);
        int first_max = A[A.length-1]*A[A.length-2]*A[A.length-3];
        int second_max = A[A.length-1]*A[0]*A[1];
        return Math.max(first_max, second_max);
    }

    public static void main(String[] args) {
        MaximalOfTriples obj = new MaximalOfTriples();
        System.out.println(obj.solution(new int[] {-3, 1, -2, 2, 5, 6}));
    }
}
