package SplitWiseLLD.Model;

import java.util.HashMap;
import java.util.Map;

public class BalanceMap {
    private Map<User,Amount> balance;

    public BalanceMap(){
        balance = new HashMap<>();
    }

    public Map<User,Amount> getBalances(){
        return balance;
    }

}
