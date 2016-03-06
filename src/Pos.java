import java.util.ArrayList;
import java.util.List;

/**
 * Created by crystalynliu on 3/6/16.
 */
public class Pos {
    public List<CartItem> getCartItems(String[] tags,List<Item> allItems){
        List<CartItem> cartItems= new ArrayList<CartItem>();
        for(int i=0;i<tags.length;i++){
            String[] barcodeCount = tags[i].split("-");
            String barcode = barcodeCount[0];
            double count=barcodeCount.length>1?Double.valueOf(barcodeCount[1]):1;
            allItems.forEach(item -> {
                if(item.getBarcode().equals(barcode)){
                    int index = findItemIndex(item,cartItems);
                    if(index==-1){
                        CartItem cartItem = new CartItem(item,count);
                        cartItems.add(cartItem);
                    }else {
                        CartItem current = cartItems.get(index);
                        current.setCount(current.getCount()+count);
                    }
                }
            });
        }
        return cartItems;
    }

    public int findItemIndex(Item item,List<CartItem> cartItems){
        int index = -1;
        for(int i =0;i<cartItems.size();i++){
            if(cartItems.get(i).getItem().equals(item)){
                index = i;
            }
        }
        return index;
    }


    public List<ReceiptItem> getReceiptItem(List<CartItem> cartItems, List<Promotion> promotions) {
        List<ReceiptItem> receiptItems = new ArrayList<ReceiptItem>();
        cartItems.forEach(cartItem -> {
            double price = cartItem.getItem().getPrice();
            double count = cartItem.getCount();

            double subTotal = price*count;
            double subSave = 0;
            String type = findCartItemType(cartItem,promotions);
            if(type.equals("BUY_TWO_GET_ONE_FREE")){
                subSave =  (int)(count/3) * price;
                subTotal = subTotal - subSave;
            }
            ReceiptItem receiptItem = new ReceiptItem();
            receiptItem.setCartItem(cartItem);
            receiptItem.setSubSave(subSave);
            receiptItem.setSubtotal(subTotal);
            receiptItems.add(receiptItem);
        });
        return receiptItems;
    }

    private String findCartItemType(CartItem cartItem, List<Promotion> promotions) {
        String type = "";
        for(int i =0 ;i<promotions.size();i++){
            String[] barcodes = promotions.get(i).getBarcode();
            for(int k = 0;k<barcodes.length;k++){
                if(cartItem.getItem().getBarcode().equals(barcodes[k])){
                    type = promotions.get(i).getType();
                    break;
                }
            }
        }
        return type;
    }

    public String printReceipt(List<ReceiptItem> receiptItems) {
        double totalPrice = 0;
        double totalSave = 0;

        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");

        String text = "***<没钱赚商店>收据***\n";

        for(int i = 0;i<receiptItems.size();i++){
            ReceiptItem receiptItem = receiptItems.get(i);
            totalPrice+=receiptItem.getSubtotal();
            totalSave+=receiptItem.getSubSave();

            text+="名称："+receiptItem.getCartItem().getItem().getName()+
                    "，数量："+(int)receiptItem.getCartItem().getCount() +
                    receiptItem.getCartItem().getItem().getUnit()+"，单价："+
                    df.format(receiptItem.getCartItem().getItem().getPrice())+
                    "(元)，小计："+df.format(receiptItem.getSubtotal())+"(元)\n";

        }

        text+="----------------------\n" +
                "总计："+df.format(totalPrice)+"(元)\n"+
                "节省："+df.format(totalSave)+"(元)\n" +
                "**********************";
        return text;
    }
}


