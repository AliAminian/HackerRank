import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntersectionOfArrays {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] big = nums1;
        int[] small = nums2;

        if (nums2.length > nums1.length) {
            big = nums2;
            small = nums1;
        }

        List<Integer> res = new ArrayList<>();

        Map<Integer, Integer> intersection = new HashMap<>();
        Arrays.stream(big).forEach(it->intersection.put(it, (!intersection.containsKey(it))? 1 : intersection.get(it)+1));
        Arrays.stream(small)
                .filter(intersection::containsKey)
                .forEach(it-> {
                        Integer currVal = intersection.get(it);
                        if (currVal > 0) {
                            intersection.put(it, currVal - 1);
                            res.add(it);
                        }
                    }
                );
        return res.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        IntersectionOfArrays obj = new IntersectionOfArrays();
        int[] res = obj.intersect(new int[]{1,2,2}, new int[]{1,1});
        Arrays.stream(res).forEach(System.out::println);
    }
}
