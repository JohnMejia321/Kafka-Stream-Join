package models;

public class ItemPriceWithDepence {

    private long plu;
    private long dependenceId;
    private float price;
    private long strategy;

    public ItemPriceWithDepence() {
    }

    public ItemPriceWithDepence(long plu, long dependenceId, float price, long strategy) {
        this.plu = plu;
        this.dependenceId= dependenceId;
        this.price = price;
        this.strategy = strategy;
    }

    public long getPlu() {
        return plu;
    }

    public void setPlu(long plu) {
        this.plu = plu;
    }

    public long getDependenceId() {
        return dependenceId;
    }

    public void setDependenceId(long dependence1Id) {
        this.dependenceId= dependenceId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getStrategy() {
        return strategy;
    }

    public void setStrategy(long strategy) {
        this.strategy = strategy;
    }
}
