package dev.yashkalia.groceries.Models.Reciept;

import dev.yashkalia.groceries.Models.CartItem.CartItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecieptItem {
    CartItem cartItem;
    Float totalPrice;

    public RecieptItem(CartItem cartItem, Float totalPrice) {
        this.cartItem = cartItem;
        this.totalPrice = totalPrice;
    }
}
