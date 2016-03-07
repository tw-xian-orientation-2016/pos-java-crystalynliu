import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by crystalynliu on 3/7/16.
 */
public class IntegrationPosTest {

    @Test
    public void print_correct_when_input_tags() throws IOException {
        String[] tags = {"ITEM000001",
                "ITEM000001",
                "ITEM000001",
                "ITEM000001",
                "ITEM000001",
                "ITEM000003-2",
                "ITEM000005",
                "ITEM000005",
                "ITEM000005"};
        BufferedWriter writer = mock(BufferedWriter.class);
        Pos pos = new Pos(writer);
        String expectation="***<没钱赚商店>收据***\n" +
                "名称：雪碧，数量：5瓶，单价：3.00(元)，小计：12.00(元)\n" +
                "名称：荔枝，数量：2斤，单价：15.00(元)，小计：30.00(元)\n" +
                "名称：方便面，数量：3袋，单价：4.50(元)，小计：9.00(元)\n" +
                "----------------------\n" +
                "总计：51.00(元)\n"+
                "节省：7.50(元)\n" +
                "**********************";
        pos.posPrint(tags);
        verify(writer).write(expectation);
    }
}
