package pos.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PosMachineTest {

    @Test
    public void should_return_receipt() {
        PosMachine posMachine = new PosMachine();

        String expected = "***<store earning no money>Receipt***\n" +
                "Name: Coca-Cola, Quantity: 4, Unit price: 3.50 (yuan), Subtotal: 14.00 (yuan)\n" +
                "Name: Sprite, Quantity: 2, Unit price: 3.20 (yuan), Subtotal: 6.40 (yuan)\n" +
                "Name: Battery, Quantity: 1, Unit price: 2.00 (yuan), Subtotal: 2.00 (yuan)\n" +
                "----------------------\n" +
                "Total: 22.40 (yuan)\n" +
                "**********************";

        assertEquals(expected, posMachine.print(ItemDataLoader.loadCartBarcodes()));
    }
}
