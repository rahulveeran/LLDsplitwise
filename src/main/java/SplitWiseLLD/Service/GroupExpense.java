package SplitWiseLLD.Service;

import SplitWiseLLD.Model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupExpense {
    private final ExpenseService expenseService;
    private Map<String, Group> groups;

    public GroupExpense(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public PaymentGraph getGroupPaymentGraph(final String groupId, final String userId) throws IllegalAccessException {
        if(!groups.get(groupId).getUsers().contains(userId)){
            throw new IllegalAccessException();
        }

        final Expense getBalance = getBalances(groupId,userId);

        return expenseService.getPaymentGraph(getBalance);
    }
    
    public Expense sumExpenses (List<Expense> groupExpenses){
        Map<User, Amount> resultBalances = new HashMap<>();
        for(Expense expense : groupExpenses){
            for(var balance : expense.getUserBalances().entrySet()){
                final var user = balance.getKey();
                final var amount = balance.getValue();
                resultBalances.put(user,resultBalances.get(user).Add(amount));

            }
        }

        return new Expense(resultBalances);
    }
    public Expense getBalances(final String groupId, final String userId) throws IllegalAccessException {
        if(!groups.get(groupId).getUsers().contains(userId)){
            throw new IllegalAccessException();
        }
        List<Expense> groupExpense = expenseService.getGroupExpenses(groupId);

        Expense resultExepnse = sumExpenses(groupExpense);

        return resultExepnse;
    }
}
