package dev.yashkalia.groceries.Services;
import dev.yashkalia.groceries.Enums.ItemType;
import dev.yashkalia.groceries.Models.CartItem.CartItem;
import dev.yashkalia.groceries.Models.DiscountTypes.Discount;
import dev.yashkalia.groceries.Models.Items.Item;
import dev.yashkalia.groceries.Models.Reciept.Reciept;
import dev.yashkalia.groceries.Models.Reciept.RecieptItem;
import dev.yashkalia.groceries.Repositories.DiscountTypes.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    ItemService itemService;

    @Autowired
    private DiscountRepository discountRepository;

      List<Discount> discounts;


    public Reciept applyApplicableDiscount(List<CartItem> cartItems){
        List<RecieptItem> reciept = new ArrayList<RecieptItem>();
        float totalOrderPrice=0;
        for(CartItem cartItem : cartItems){
            switch (ItemType.valueOf(cartItem.getProps().getItemType())){
                case Beer:
                    float totalPriceAfterDiscount=calculateTotalPriceForBeersAfterDiscount(cartItem);
                    totalOrderPrice+=totalPriceAfterDiscount;
                    reciept.add(new RecieptItem(cartItem, totalPriceAfterDiscount));
                    break;
                case Bread:
                    int totalBreadsToTakeAfterDiscount = getTotalBreadsToTakeAfterDiscount(cartItem);
                    float totalPriceForBreadAfterDiscount =  cartItem.getItemCount() * cartItem.getProps().getPrice();
                    totalOrderPrice+=totalPriceForBreadAfterDiscount;
                    reciept.add(new RecieptItem(new CartItem(totalBreadsToTakeAfterDiscount, cartItem.getProps()), totalPriceForBreadAfterDiscount));
                    break;
                case Vegetable:
                    float discountPercentage = getVegetableDiscountPercentage(cartItem);
                    float totalPriceBeforeDiscountForVegetables =  cartItem.getItemCount() * cartItem.getProps().getPrice();
                    float totalPriceAfterDiscountForVegetables = totalPriceBeforeDiscountForVegetables - (discountPercentage/100) * totalPriceBeforeDiscountForVegetables;
                    totalOrderPrice+=totalPriceAfterDiscountForVegetables;
                    reciept.add(new RecieptItem(cartItem, totalPriceAfterDiscountForVegetables));
                    break;
                default: throw new IllegalArgumentException("Item not not supported");
            }
        }
        return new Reciept(reciept, totalOrderPrice);
    }

    public Float calculateTotalPriceForBeersAfterDiscount(CartItem cartItem){

        if(cartItem.getProps().getIsPack()){
            return getPackDiscountForBeers(cartItem);
        }
        return getPriceByItemCountForBeers(cartItem);
    }

    private Float getPackDiscountForBeers(CartItem cartItem){
        return cartItem.getItemCount() * cartItem.getProps().getPrice();
    }

    private Float getPriceByItemCountForBeers(CartItem cartItem){
        int sixPackCount = cartItem.getItemCount() / 6;
        int remainingBeers = cartItem.getItemCount() % 6;
        List<Item> beerItems = itemService.GetAllBeerItems();
        Item sixPackItem = beerItems.stream().filter( beer -> {return beer.getId().equals(cartItem.getProps().getDiscountId());}).findAny().orElse(null);
        if(sixPackItem !=null){
            return sixPackCount * sixPackItem.getPrice() + remainingBeers * cartItem.getProps().getPrice();
        }
        return cartItem.getItemCount() * cartItem.getProps().getPrice();
    }

    //Returns the total number of breads that can be taken after discount
    private int getTotalBreadsToTakeAfterDiscount(CartItem cartItem){
        this.discounts = discountRepository.findAll();
        Discount breadDiscount  = this.discounts.stream().filter( discount -> {return discount.getAge().equals(cartItem.getProps().getAge());}).findAny().orElse(null);
        if(breadDiscount!=null){
            if(cartItem.getItemCount() < breadDiscount.getBuy()){
//                return cartItem.getItemCount() * cartItem.getProps().getPrice();
                return cartItem.getItemCount();
            }
            int discountsApplicable = cartItem.getItemCount() / breadDiscount.getBuy();
            return  cartItem.getItemCount() + discountsApplicable * breadDiscount.getTake();
        }
        return cartItem.getItemCount();
    }

    private float getVegetableDiscountPercentage(CartItem cartItem){
        this.discounts = discountRepository.findAll();
        Discount vegetableDiscount  = this.discounts.stream().filter( discount -> {return discount.weightLowerLimit < cartItem.getItemCount() * cartItem.getProps().getWeight() && (discount.weightUpperLimit == -1 || discount.weightUpperLimit >= cartItem.getItemCount() * cartItem.getProps().getWeight());}).findAny().orElse(null);
        if(vegetableDiscount!=null){
            return vegetableDiscount.getDiscountPercentage();
        }
        return 0;
    }
}
