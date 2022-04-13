package models;

public class ItemPrice {

    private long plu;
    private long dependenceId;
    private float price;

    public ItemPrice() {
    }

    public ItemPrice(long plu, long dependenceId, float price) {
        this.plu = plu;
        this.dependenceId = dependenceId;
        this.price = price;

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

    public void setDependenceId(long dependenceId) {
        this.dependenceId = dependenceId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
