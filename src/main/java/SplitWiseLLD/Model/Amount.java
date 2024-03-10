package SplitWiseLLD.Model;

public class Amount {
    private String currency;
    private int amount;
    public int getAmount() {
        return amount;
    }

    public Amount(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Amount Add(Amount amount){

        return new Amount("USD",this.amount+amount.amount);
    }


}
