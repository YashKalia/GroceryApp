package dev.yashkalia.groceries.Models.CartItem;

import dev.yashkalia.groceries.Models.Items.Item;
import lombok.Getter;

@Getter
public class CartItem {

    private Integer itemCount;
    private Item props;

    @Override
    public String toString() {
        return "CartItem{" +
                "itemCount=" + itemCount +
                ", props=" + props +
                '}';
    }
}
