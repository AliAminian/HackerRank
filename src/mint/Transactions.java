package mint;

import java.util.*;

/*
You are provided with a list of transactions representing consumers' purchases.
Each transaction can be either approved or rejected based on total sum of approved purchases the consumer made so far.
Specifically the transaction is rejected if total sum of purchases would increase above a certain number - creditLimit.
Otherwise the transaction is approved.

Your task is to process the list of transactions and return list of the rejected ones.

Input
You should write a function called findRejectedTransactions, which takes as input a list
of transactions to process and a creditLimit integer.

List<String> findRejectedTransactions(List<String> transactions, int creditLimit);
Transactions are provided in a CSV format. An example transaction may look this:

"John,Doe,john@doe.com,30,TR000"
Each transaction contains consumer details as well as an amount and an id:

first name
last name
email
amount
transaction id
You should expect all the transactions being valid and containing all of the information listed above.

Additionally there is a creditLimit >= 0 defined that is equal to all the consumers.

Output
The function should return a list of Strings listing the transactions that supposed to be rejected
if the transactions were to be processed in a sequential manner following the initial order of elements.

A consumer is uniquely identified by all three of first name, last name and email,
i.e. two persons with equal names but different emails have separate credit line.

Examples
findRejectedTransactions([], 0) returns empty list []
findRejectedTransactions(["John,Doe,john@doe.com,200,TR0001"], 200) returns empty list [] - the only John Doe's transaction is approved
findRejectedTransactions(["Jane,Doe,jane@doe.com,201,TR0001"], 200) returns [TR0001] - the only Jane Doe's transaction is rejected since its amount 201 is greater than creditLimit = 200
findRejectedTransactions(["Jane,Doe,jane@doe.com,199,TR0001", "Jane,Doe,jane@doe.com,2,TR0002"], 200) returns [TR0002] as approving the second transaction would make the credit limit exceeded
 */
public class Transactions {

    public static List<String> findRejectedTransactions(List<String> transactions, int creditLimit) {

        if (transactions.size() == 0 || creditLimit == 0) return Collections.emptyList();

        List<String> rejectList = new ArrayList<>();
        Map<String, Integer> creditMap = new HashMap<>();

        for (String transaction: transactions) {
            String[] details = transaction.split(",");

            String key = details[0]+"-"+details[1]+"-"+details[2];
            int credit = creditMap.getOrDefault(key, 0);
            int amount = Integer.parseInt(details[3]);
            String id = details[4];


            if (credit + amount > creditLimit) rejectList.add(id);
            else creditMap.put(key, credit + amount);

        }
        return rejectList;
    }
}
