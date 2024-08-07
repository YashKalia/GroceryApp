package dev.yashkalia.groceries.Services;

import dev.yashkalia.groceries.Models.CartItem.CartItem;
import dev.yashkalia.groceries.Models.Reciept.Reciept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    DiscountService discountService;

    public Reciept Checkout(List<CartItem> cartItems) {
        return discountService.applyApplicableDiscount(cartItems);
    }

}
