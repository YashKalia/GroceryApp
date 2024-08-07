package dev.yashkalia.groceries.Models.Reciept;

import dev.yashkalia.groceries.Models.CartItem.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Reciept {
    private List<RecieptItem> receiptItems;
    private Float totalOrderPrice;

    public Reciept(List<RecieptItem> receiptItems, Float totalOrderPrice) {
        this.receiptItems = receiptItems;
        this.totalOrderPrice = totalOrderPrice;
    }
}
