package SplitWiseLLD.Service;

import SplitWiseLLD.Model.*;

import java.util.*;

public class ExpenseService {
     private Map<String, List<Expense>> groupExpenses;
    public List<Expense> getGroupExpenses(String  groupId){
        //return new ArrayList<>();
        return groupExpenses.get(groupId);
    }

    public PaymentGraph paymentGraph (BalanceMap groupBalances){
        final var graph = new HashMap<User,BalanceMap>();
        PriorityQueue<Map.Entry<User, Amount>> positiveAmounts = new PriorityQueue<>(Comparator.comparingDouble(balance -> -balance.getValue().getAmount()));
        PriorityQueue<Map.Entry<User,Amount>> negativeAmounts = new PriorityQueue<>(Comparator.comparingDouble(balance -> -balance.getValue().getAmount()));

        for(var balance : groupBalances.getBalances().entrySet()){
            if(balance.getValue().getAmount() > 0){
                positiveAmounts.add(balance);
            }
            else{
                negativeAmounts.add(balance);
            }
        }

        while(!positiveAmounts.isEmpty() && !negativeAmounts.isEmpty()){
            final var largestPositve = positiveAmounts.poll();
            final var largestNegative = negativeAmounts.poll();

            int largestPositiveAmount = largestPositve.getValue().getAmount();
            int largestNegativeAmount = largestNegative.getValue().getAmount();

            graph.putIfAbsent(largestPositve.getKey(), new BalanceMap());
            int val = Math.min(largestPositiveAmount,largestNegativeAmount);
            graph.get(largestPositve.getKey()).getBalances()
                    .put(largestNegative.getKey(),new Amount("USD",val));

            int remaining = largestPositiveAmount - largestNegativeAmount;

            Amount remainingAmount = new Amount("USD",remaining);

            if(remaining > 0){
                positiveAmounts.add(new AbstractMap.SimpleEntry<>(largestPositve.getKey(),remainingAmount));
            }
            else{
                positiveAmounts.add(new AbstractMap.SimpleEntry<>(largestNegative.getKey(),remainingAmount));
            }
        }
        return new PaymentGraph(graph);
    }
}
