import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class PosTest {

    private List<Item> allItems;
    private List<Promotion> promotions;
    private Pos pos;
    private String[] tags = {"ITEM000001",
            "ITEM000001",
            "ITEM000001",
            "ITEM000001",
            "ITEM000001",
            "ITEM000003-2",
            "ITEM000005",
            "ITEM000005",
            "ITEM000005"};

    @Before
    public void setUp() {
        allItems = Item.loadAllItems();
        promotions =Promotion.loadPromotions();
        pos = new Pos();
    }

    @Test
    public void get_correct_cartItems_when_input_tags() {

        List<CartItem> expectations = new ArrayList<CartItem>();
        CartItem cartItem = new CartItem(allItems.get(1), 5);
        expectations.add(cartItem);
        cartItem = new CartItem(allItems.get(3), 2);
        expectations.add(cartItem);
        cartItem = new CartItem(allItems.get(5), 3);
        expectations.add(cartItem);


        List<CartItem> result = pos.getCartItems(tags, allItems);
        assertThat(result.size(),is(expectations.size()));
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i).getItem(),is(expectations.get(i).getItem()));
            assertThat(result.get(i).getCount(),is(expectations.get(i).getCount()));
        }
    }

    @Test
    public void get_correct_subprice_when_input_promotions(){
        List<CartItem> cartItems = new ArrayList<CartItem>();
        CartItem cartItem = new CartItem(allItems.get(1),5.00);
        cartItems.add(cartItem);
        cartItem = new CartItem(allItems.get(3),2.00);
        cartItems.add(cartItem);
        cartItem = new CartItem(allItems.get(5),3.00);
        cartItems.add(cartItem);

        double[] expectations = {12.00,30.00,9.00};
        List<ReceiptItem> ReceiptItems = pos.getReceiptItem(cartItems,promotions);
        for(int i =0;i<3;i++){
            assertThat(ReceiptItems.get(i).getSubtotal(),is(expectations[i]));
        }
    }

    @Test
    public void print_correct_text_when_input_receiptItems(){
        List<ReceiptItem> receiptItems = new ArrayList<ReceiptItem>();
        double[][] data = {{},{5.00,12.00,3.00},{},{2.00,30.00,0.00},{},{3.00,9.00,4.50}};
        for(int i =0;i<data.length;i++){
            i++;
            CartItem cartItem = new CartItem(allItems.get(i),data[i][0]);
            ReceiptItem receiptItem = new ReceiptItem();
            receiptItem.setCartItem(cartItem);
            receiptItem.setSubtotal(data[i][1]);
            receiptItem.setSubSave(data[i][2]);
            receiptItems.add(receiptItem);
        }

        String expectation="***<没钱赚商店>收据***\n" +
                "名称：雪碧，数量：5瓶，单价：3.00(元)，小计：12.00(元)\n" +
                "名称：荔枝，数量：2斤，单价：15.00(元)，小计：30.00(元)\n" +
                "名称：方便面，数量：3袋，单价：4.50(元)，小计：9.00(元)\n" +
                "----------------------\n" +
                "总计：51.00(元)\n"+
                "节省：7.50(元)\n" +
                "**********************";

        String result = pos.printReceipt(receiptItems);
        assertThat(result,is(expectation));
    }
}
