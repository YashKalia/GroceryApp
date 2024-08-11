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
            if(ItemType.valueOf(cartItem.getProps().getItemType()) == ItemType.Beer){
                 float totalPriceForBeer=calculateTotalPriceForBeersAfterDiscount(cartItem);
                 totalOrderPrice+=totalPriceForBeer;
//                System.out.println("totalPriceForBeer "+totalPriceForBeer);
//                System.out.println("totalCartPrice "+totalOrderPrice);
                reciept.add(new RecieptItem(cartItem, totalPriceForBeer));
            }

            else  if(ItemType.valueOf(cartItem.getProps().getItemType()) == ItemType.Bread){
                int totalBreadsToTake = getTotalBreadsToTakeAfterDiscount(cartItem);
                float totalPriceForBread =  cartItem.getItemCount() * cartItem.getProps().getPrice();
                totalOrderPrice+=totalPriceForBread;
//                System.out.println("totalBreadsToTake "+totalBreadsToTake);
//                System.out.println("totalPriceForBread "+totalPriceForBread);
                reciept.add(new RecieptItem(new CartItem(cartItem.getItemCount() +totalBreadsToTake,cartItem.getProps()), totalPriceForBread));
            }

            else  if(ItemType.valueOf(cartItem.getProps().getItemType()) == ItemType.Vegetable){
                float discountPercentage = getVegetableDiscountPercentage(cartItem);
                float totalPriceBeforeDiscountForVegetables =  cartItem.getItemCount() * cartItem.getProps().getPrice();
                float totalPriceAfterDiscount = totalPriceBeforeDiscountForVegetables - (discountPercentage/100) * totalPriceBeforeDiscountForVegetables;
                totalOrderPrice+=totalPriceAfterDiscount;
//                System.out.println("discountPercentage "+discountPercentage);
//                System.out.println("totalPriceBeforeDiscountForVegetables "+totalPriceBeforeDiscountForVegetables);
//                System.out.println("totalPriceAfterDiscount "+totalPriceAfterDiscount);
                reciept.add(new RecieptItem(cartItem, totalPriceAfterDiscount));
            }

        }
//        System.out.println("Total discounted price for totalCartPrice " + totalOrderPrice);
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
        List<Item> beerItems = itemService.getAllBeerItems();
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
            return discountsApplicable * breadDiscount.getTake();
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
