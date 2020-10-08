package pos.machine;

import java.text.DecimalFormat;
import java.util.*;

public class PosMachine {
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public String print(List<String> barcodes) {

        List<Item> itemsWithDetail = decodeToItems(barcodes);

        Receipt receipt = calculateCost(itemsWithDetail);

        return renderReceipt(receipt);
    }

    private List<Item> decodeToItems(List<String> barcodes) {
        Map<String, Integer> barcodesWithCount = parse(barcodes);

        List<Item> itemsWithDetail = getItemsDetail(barcodesWithCount);

        return itemsWithDetail;
    }

    private Map<String, Integer> parse(List<String> items) {
        LinkedHashMap<String, Integer> itemsWithCount = new LinkedHashMap<>();

        items.forEach(item -> {
            if (!item.contains("-")) {
                itemsWithCount.put(item, itemsWithCount.getOrDefault(item,  0) + 1);
            } else {
                String barcode = getBarcode(item);
                int count = getCount(item);
                itemsWithCount.put(barcode, itemsWithCount.getOrDefault(barcode, 0) + count);
            }
        });

        return itemsWithCount;
    }

    private int getCount(String item) {
        return Integer.parseInt(item.split("-")[1]);
    }

    private String getBarcode(String item) {
        return item.split("-")[0];
    }

    private List<Item> getItemsDetail(Map<String, Integer> barcodesWithCount) {
        List<ItemInfo> allItemInfos = ItemDataLoader.loadAllItemInfos();
        List<Item> itemList = new ArrayList<Item>();
        barcodesWithCount.keySet().forEach(barcode -> {
            ItemInfo item = allItemInfos.stream().filter(itemInfo -> itemInfo.getBarcode().equals(barcode)).findFirst().get();
            itemList.add(new Item(item.getName(), barcodesWithCount.get(barcode), item.getPrice()));
        });

        return itemList;
    }

    private Receipt calculateCost(List<Item> itemsWithDetail) {
        List<Item> itemsWithCost = calculateItemsCost(itemsWithDetail);

        double totalPrice = calculateTotal(itemsWithCost);

        return new Receipt(itemsWithCost, totalPrice);
    }

    private List<Item> calculateItemsCost(List<Item> itemsWithDetail) {
        List<Item> itemsWithSubtotal = new ArrayList<>(itemsWithDetail);

        itemsWithSubtotal.forEach(item -> item.setSubTotal(item.getUnitPrice() * item.getQuantity()));

        return itemsWithSubtotal;
    }

    private Double calculateTotal(List<Item> itemsWithSubtotal) {
        return itemsWithSubtotal.stream().mapToDouble(Item::getSubTotal).sum();
    }

    private String renderReceipt(Receipt receipt) {
        String itemsPart = generateItemsDetail(receipt.getItemDetails());

        return generateReceipt(itemsPart, receipt.getTotalPrice());
    }

    private String generateItemsDetail(List<Item> itemsWithCost) {
        String itemDetail = "";
        for (Item item : itemsWithCost) {
            itemDetail += String.format("Name: %s, Quantity: %s, Unit price: %s (yuan), Subtotal: %s (yuan)%n",
                    item.getName(), item.getQuantity(), decimalFormat.format(item.getUnitPrice()), decimalFormat.format(item.getSubTotal()));
        }
        return itemDetail;
    }

    public String generateReceipt(String itemsPart, double totalPrice) {
        String header = "***<store earning no money>Receipt***";
        String splice = "----------------------";
        String total = String.format("Total: %s (yuan)", decimalFormat.format(totalPrice));
        String end = "**********************";

        return String.format("%s%n%s%s%n%s%n%s", header, itemsPart, splice, total, end);
    }
}
