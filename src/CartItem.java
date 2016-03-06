public class CartItem {
    private Item item;
    private double count;

    public CartItem(Item item,double count){
        this.item = item;
        this.count = count;
    }
    public double getCount() {
        return count;
    }

    public Item getItem() {
        return item;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
