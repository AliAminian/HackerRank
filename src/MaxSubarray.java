public class MaxSubarray {
    public int maxSubArray(int[] nums) {
        int currSum = 0, maxSum = nums[0];
        for(int num: nums){
            if(currSum < 0){
                currSum = 0;
            }
            currSum += num;
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSubarray max = new MaxSubarray();
        max.maxSubArray(new int[]{-2, -1, -4});
    }
}
