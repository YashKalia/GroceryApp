package dev.yashkalia.groceries.Services;

import dev.yashkalia.groceries.Models.DiscountTypes.BeerDiscount;
import dev.yashkalia.groceries.Models.Items.BeerItem;
import dev.yashkalia.groceries.Models.Items.Item;
import dev.yashkalia.groceries.Repositories.DiscountTypes.BeerDiscountRepository;
import dev.yashkalia.groceries.Repositories.Items.BeerRepository;
import dev.yashkalia.groceries.Repositories.Items.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

//    @Autowired
//    private BeerDiscountRepository beerDiscountRepository;
    @Autowired
    private ItemRepository itemRepository;

//    public List<BeerDiscount> getAllBeerDiscountItems(){
//        return beerDiscountRepository.findAll();
//    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }
}
