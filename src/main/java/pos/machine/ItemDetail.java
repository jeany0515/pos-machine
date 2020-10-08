package pos.machine;

public class ItemDetail {
    private final String name;
    private final int quantity;
    private final double unitPrice;
    private final double subTotal;

    public ItemDetail(String name, int quantity, double unitPrice, double subTotal) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubTotal() {
        return subTotal;
    }
}
