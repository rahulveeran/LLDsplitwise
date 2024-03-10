package SplitWiseLLD.Model;

import java.util.HashMap;
import java.util.Map;

public class Expense {
    public Expense(Map<User, Amount> userBalances) {
        this.userBalances = userBalances;
    }

    private final Map<User,Amount> userBalances;
    private  String title;



    public Map<User, Amount> getUserBalances() {
        return userBalances;
    }
}
