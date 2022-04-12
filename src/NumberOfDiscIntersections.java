public class NumberOfDiscIntersections {
    public int solution(int[] A) {
        int[] starts = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            int startPoint = i - A[i];
            if (startPoint < 0)
                startPoint = 0;
            starts[startPoint]++;
        }

        int total = 0;
        for (int i = 0; i < starts.length; i++) {
            total += starts[i];
            starts[i] = total;
        }

        int totalIntersections = 0;
        for (long i = 0; i < A.length; i++) {
            long endPoints = i + A[(int)i];
            if (endPoints > A.length-1)
                endPoints = A.length-1;
            int intersections = (int)(Math.max(starts[(int)endPoints], starts[(int)i]) - (i+1));
            totalIntersections += intersections;
            if (totalIntersections > 10000000) return -1;
        }

        return totalIntersections;
    }

    public static void main (String[] arg) {
        NumberOfDiscIntersections discIntersects = new NumberOfDiscIntersections();
        int count = discIntersects.solution(new int[]{1, 5, 2, 1, 4, 0});
        System.out.println(count);
    }
}
