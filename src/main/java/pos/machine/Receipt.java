package pos.machine;

import java.util.List;

public class Receipt {
    private final List<ItemDetail> itemDetails;
    private final double totalPrice;

    public Receipt(List<ItemDetail> itemDetails, double totalPrice) {
        this.itemDetails = itemDetails;
        this.totalPrice = totalPrice;
    }

    private String generateItemDetailReceipt() {
        String itemDetailReceipt = "";
        for (ItemDetail itemDetail : this.itemDetails) {
            itemDetailReceipt += String.format("Name: %s, Quantity: %d, Unit price: %s (yuan), Subtotal: %s (yuan)%n",
                    itemDetail.getName(), itemDetail.getQuantity(), itemDetail.getUnitPrice(), itemDetail.getSubTotal());
        }
        return itemDetailReceipt;
    }

    public String generate() {
        String header = "***<store earning no money>Receipt***";
        String itemList = generateItemDetailReceipt();
        String splice = "----------------------";
        String total = String.format("Total: %s (yuan)", totalPrice);
        String end = "**********************";

        return String.format("%s%n%s%s%n%s%n%s", header, itemList, splice, total, end);
    }
}
