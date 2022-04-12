import java.util.*;

public class BestTimeForStock {
    public int maxProfit(int[] prices) {
        int buy=Integer.MAX_VALUE,sell=0;
        for(int i=0;i<prices.length;i++){
            buy=Math.min(buy,prices[i]);
            sell=Math.max(sell,prices[i]-buy);
        }
        return sell;
    }


    public static void main(String[] args) {
        BestTimeForStock obj = new BestTimeForStock();
        int res = obj.maxProfit(new int[]{7,1,5,3,6,4});
        System.out.println(res);
    }
}
