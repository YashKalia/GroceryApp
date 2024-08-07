package dev.yashkalia.groceries.Services;

import ch.qos.logback.core.net.SyslogOutputStream;
import dev.yashkalia.groceries.Enums.ItemType;
import dev.yashkalia.groceries.Models.CartItem.CartItem;
import dev.yashkalia.groceries.Models.DiscountTypes.BeerDiscount;
import dev.yashkalia.groceries.Models.DiscountTypes.BreadDiscount;
import dev.yashkalia.groceries.Models.DiscountTypes.VegetableDiscount;
import dev.yashkalia.groceries.Models.Items.BeerItem;
import dev.yashkalia.groceries.Models.Reciept.Reciept;
import dev.yashkalia.groceries.Models.Reciept.RecieptItem;
import dev.yashkalia.groceries.Repositories.DiscountTypes.BeerDiscountRepository;
import dev.yashkalia.groceries.Repositories.DiscountTypes.BreadDiscountRepository;
import dev.yashkalia.groceries.Repositories.DiscountTypes.VegetableDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService {

//    @Autowired
//    BreadService breadService;

    @Autowired
    BeerService beerService;

//    @Autowired
//    VegetableService vegetableService;

    @Autowired
    private BreadDiscountRepository breadDiscountRepository;
//
//    @Autowired
//    private BeerDiscountRepository beerDiscountRepository;
//
    @Autowired
    private VegetableDiscountRepository vegetableDiscountRepository;

//    List<BeerDiscount> beerDiscounts;
    List<BreadDiscount> breadDiscounts;
    List<VegetableDiscount> vegetableDiscounts;
//    List<BeerItem> beerItems;

    public DiscountService() {
//        this.beerDiscounts = beerDiscountRepository.findAll();
//        this.breadDiscounts = breadDiscountRepository.findAll();
//        this.vegetableDiscounts = vegetableDiscountRepository.findAll();
//        this.beerItems = beerService.getAllBeerItems();
    }

    public Reciept applyApplicableDiscount(List<CartItem> cartItems){
        List<RecieptItem> reciept = new ArrayList<RecieptItem>();
        float totalOrderPrice=0;
        for(CartItem cartItem : cartItems){
            if(ItemType.valueOf(cartItem.getProps().getItemType()) == ItemType.Beer){
                 float totalPriceForBeer=calculateTotalPriceForBeersAfterDiscount(cartItem);
                 totalOrderPrice+=totalPriceForBeer;
                System.out.println("totalPriceForBeer "+totalPriceForBeer);
                System.out.println("totalCartPrice "+totalOrderPrice);
                reciept.add(new RecieptItem(cartItem, totalPriceForBeer));
            }

            else  if(ItemType.valueOf(cartItem.getProps().getItemType()) == ItemType.Bread){
                int totalBreadsToTake = getTotalBreadsToTakeAfterDiscount(cartItem);
                float totalPriceForBread =  cartItem.getItemCount() * cartItem.getProps().getPrice();
                totalOrderPrice+=totalPriceForBread;
                System.out.println("totalBreadsToTake "+totalBreadsToTake);
                System.out.println("totalPriceForBread "+totalPriceForBread);
                reciept.add(new RecieptItem(cartItem, totalPriceForBread));
            }

            else  if(ItemType.valueOf(cartItem.getProps().getItemType()) == ItemType.Vegetable){
                float discountPercentage = getVegetableDiscountPercentage(cartItem);
                float totalPriceBeforeDiscountForVegetables =  cartItem.getItemCount() * cartItem.getProps().getPrice();
                float totalPriceAfterDiscount = totalPriceBeforeDiscountForVegetables - (discountPercentage/100) * totalPriceBeforeDiscountForVegetables;
                totalOrderPrice+=totalPriceAfterDiscount;
                System.out.println("discountPercentage "+discountPercentage);
                System.out.println("totalPriceBeforeDiscountForVegetables "+totalPriceBeforeDiscountForVegetables);
                System.out.println("totalPriceAfterDiscount "+totalPriceAfterDiscount);
                reciept.add(new RecieptItem(cartItem, totalPriceAfterDiscount));
            }

        }
        System.out.println("Total discounted price for totalCartPrice " + totalOrderPrice);
        return new Reciept(reciept, totalOrderPrice);
    }

    public int calculateTotalPriceForBeersAfterDiscount(CartItem cartItem){

        if(cartItem.getProps().getIsPack()){
            return getPackDiscountForBeers(cartItem);
        }
        return getPriceByItemCountForBeers(cartItem);
    }

    private int getPackDiscountForBeers(CartItem cartItem){
        return cartItem.getItemCount() * cartItem.getProps().getPrice();
    }

    private int getPriceByItemCountForBeers(CartItem cartItem){
        int sixPackCount = cartItem.getItemCount() / 6;
        int remainingBeers = cartItem.getItemCount() % 6;

        List<BeerItem> beerItems = beerService.getAllBeerItems();
        BeerItem sixPackItem = beerItems.stream().filter( beer -> {return beer.getId().equals(cartItem.getProps().getDiscountId());}).findAny().orElse(null);
        if(sixPackItem !=null){
            return sixPackCount * sixPackItem.getPrice() + remainingBeers * cartItem.getProps().getPrice();
        }
        return cartItem.getItemCount() * cartItem.getProps().getPrice();


    }

    //Returns the total number of breads that can be taken after discount
    private int getTotalBreadsToTakeAfterDiscount(CartItem cartItem){
        this.breadDiscounts = breadDiscountRepository.findAll();
        BreadDiscount breadDiscount  = this.breadDiscounts.stream().filter( discount -> {return discount.getAge() == cartItem.getProps().getAge();}).findAny().orElse(null);
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
        this.vegetableDiscounts = vegetableDiscountRepository.findAll();
        VegetableDiscount vegetableDiscount  = this.vegetableDiscounts.stream().filter( discount -> {return discount.weightLowerLimit < cartItem.getItemCount() * cartItem.getProps().getWeight() && (discount.weightUpperLimit == -1 || discount.weightUpperLimit >= cartItem.getItemCount() * cartItem.getProps().getWeight());}).findAny().orElse(null);
        if(vegetableDiscount!=null){
            return vegetableDiscount.getDiscountPercentage();
        }
        return 0;
    }

}
