package SplitWiseLLD.Model;

import java.util.HashMap;
import java.util.Map;

public class PaymentGraph {

    // user -> user, amount;

    private final Map<User, BalanceMap> graph;

    public PaymentGraph(Map<User, BalanceMap> graph) {
        this.graph = graph;
    }

}
