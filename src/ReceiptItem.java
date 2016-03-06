public class ReceiptItem {
    private CartItem cartItem;
    private double subtotal;
    private double subSave;

    public CartItem getCartItem() {
        return cartItem;
    }

    public double getSubSave() {
        return subSave;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public void setSubSave(double subSave) {
        this.subSave = subSave;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
