package pos.machine;

public class ProductInfo {
    private final String barcode;
    private final String name;
    private final double price;

    public ProductInfo(String barcode, String name, double price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
