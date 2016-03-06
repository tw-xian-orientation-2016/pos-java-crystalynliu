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

    @Before
    public void setUp() {
        allItems = Item.loadAllItems();
    }

    @Test
    public void get_correct_cartItems_when_input_tags() {
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
        CartItem cartItem = new CartItem(allItems.get(1), 5);
        expectations.add(cartItem);
        cartItem = new CartItem(allItems.get(3), 2);
        expectations.add(cartItem);
        cartItem = new CartItem(allItems.get(5), 3);
        expectations.add(cartItem);

        Pos pos = new Pos();
        List<CartItem> result = pos.getCartItems(tags, allItems);
        assertThat(result.size(),is(expectations.size()));
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i).getItem(),is(expectations.get(i).getItem()));
            assertThat(result.get(i).getCount(),is(expectations.get(i).getCount()));
        }
    }

}
