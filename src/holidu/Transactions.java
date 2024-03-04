package holidu;

import java.util.*;

public class Transactions {

    public static List<String> findRejectedTransactions(List<String> transactions, int creditLimit) {

        if (transactions.isEmpty() || creditLimit == 0) return Collections.emptyList();

        List<String> rejectList = new ArrayList<>();
        Map<String, Integer> creditMap = new HashMap<>();

        for (String transaction: transactions) {
            String[] details = transaction.split(",");

            String key = details[0]+details[1]+details[2];
            int credit = creditMap.getOrDefault(key, 0);
            int amount = Integer.parseInt(details[3]);
            String id = details[4];


            if (credit + amount > creditLimit) rejectList.add(id);
            else creditMap.put(key, credit + amount);

        }
        return rejectList;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(findRejectedTransactions(
                List.of(
                "John,Doe,john@doe.com,200,TR0001",
                "John,Doe,john@doe.com,1,TR0002",
                "John,Doej,ohn@doe.com,1,TR0003"
                ),
                200
        )); // [TR0002]
    }
}
