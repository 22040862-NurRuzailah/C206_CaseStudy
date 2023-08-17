public class ExchangeRate {
    private String currencyFrom;
    private String currencyTo;
    private double rate;

    public ExchangeRate(String currencyFrom, String currencyTo, double rate) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }
//getters and setters
    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "1 " + currencyFrom + " = " + rate + " " + currencyTo;
    }
}