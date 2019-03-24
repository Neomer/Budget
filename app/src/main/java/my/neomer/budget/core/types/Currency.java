package my.neomer.budget.core.types;

public class Currency {

    private String fullName;
    private String shortName;
    private String symbol;
    private int id;

    public Currency(int id, String shortName, String fullName, String symbol) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.symbol = symbol;
        this.id = id;
    }

    public Currency() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
