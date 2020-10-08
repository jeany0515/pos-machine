package pos.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {
    private final List<ProductInfo> productInfos;

    public PosMachine(List<ProductInfo> productDetails) {
        this.productInfos = productDetails;
    }

    public String settle(List<String> items) {
        List<ItemDetail> itemDetails = generateItemDetails(items);

        double totalPrice = itemDetails.stream().mapToDouble(ItemDetail::getSubTotal).sum();

        return new Receipt(itemDetails, totalPrice).generate();
    }

    private List<ItemDetail> generateItemDetails(List<String> items) {
        List<ItemDetail> itemDetails = new ArrayList<>();
        List<String> distinctItems = items.stream().distinct().collect(Collectors.toList());
        distinctItems.forEach(distinctItem -> {
            int quantity = (int) items.stream().filter(item -> item.equals(distinctItem)).count();
            ProductInfo product = productInfos.stream().filter(productInfo -> productInfo.getBarcode().equals(distinctItem)).findFirst().get();
            String name = product.getName();
            double unitPrice = product.getPrice();
            double subTotal = unitPrice*quantity;
            ItemDetail itemDetail = new ItemDetail(name, quantity, unitPrice, subTotal);
            itemDetails.add(itemDetail);
        });

        return itemDetails;
    }
}
