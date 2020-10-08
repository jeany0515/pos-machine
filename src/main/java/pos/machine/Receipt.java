package pos.machine;

import java.util.List;

public class Receipt {
    private final List<Item> itemDetails;
    private final double totalPrice;

    public Receipt(List<Item> itemDetails, double totalPrice) {
        this.itemDetails = itemDetails;
        this.totalPrice = totalPrice;
    }

    public List<Item> getItemDetails() {
        return itemDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}