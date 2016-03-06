import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PosTest {

    private List<Item> allItems;
    @Before
    public void setUp(){
        allItems = Item.loadAllItems();
    }

    @Test
    public void get_correct_cartItems_when_input_tags(){
        String[] tags = {"ITEM000001",
                "ITEM000001",
                "ITEM000001",
                "ITEM000001",
                "ITEM000001",
                "ITEM000003-2",
                "ITEM000005",
                "ITEM000005",
                "ITEM000005"};

        List<CartItem> expectations = new ArrayList<CartItem>();
        Item item = new Item("ITEM000001", "雪碧", "瓶", 3.00);
        CartItem cartItem = new CartItem(item,5);
        expectations.add(cartItem);
        item = new Item("ITEM000003", "荔枝", "斤", 15.00);
        cartItem = new CartItem(item,2);
        expectations.add(cartItem);
        item = new Item("ITEM000005", "方便面", "袋", 4.50);
        cartItem = new CartItem(item,3);
        expectations.add(cartItem);

        Pos pos = new Pos();
        List<CartItem> result = pos.getCartItems(tags,allItems);
        assertThat(result,is(expectations));
    }

}
