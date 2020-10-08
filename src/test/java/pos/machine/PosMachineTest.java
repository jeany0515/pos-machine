package pos.machine;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PosMachineTest {
    @Test
    void should_return_receipt_when_settle_given_item_list_and_product_detail() {
        //given
        ProductInfo productInfo1 = new ProductInfo("ITEM000000", "Coca-Cola", 3.5);
        ProductInfo productInfo2 = new ProductInfo("ITEM000001", "Sprite", 3.2);
        ProductInfo productInfo3 = new ProductInfo("ITEM000004", "Battery", 2);
        List<ProductInfo> productInfos = new ArrayList<>();
        productInfos.add(productInfo1);
        productInfos.add(productInfo2);
        productInfos.add(productInfo3);

        PosMachine posmachine = new PosMachine(productInfos);
        List<String> items = Arrays.asList("ITEM000000", "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000001", "ITEM000001", "ITEM000004");

        String expected = "***<store earning no money>Receipt***" + "\n" +
                "Name: Coca-Cola, Quantity: 4, Unit price: 3.5 (yuan), Subtotal: 14.0 (yuan)\n" +
                "Name: Sprite, Quantity: 2, Unit price: 3.2 (yuan), Subtotal: 6.4 (yuan)\n" +
                "Name: Battery, Quantity: 1, Unit price: 2.0 (yuan), Subtotal: 2.0 (yuan)\n" +
                "----------------------\n" +
                "Total: 22.4 (yuan)\n" +
                "**********************";

        //when
        String actual = posmachine.settle(items);

        //then
        assertEquals(expected, actual);
    }
}
