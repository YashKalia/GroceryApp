package dev.yashkalia.groceries.Services;

import dev.yashkalia.groceries.Models.DiscountTypes.BeerDiscount;
import dev.yashkalia.groceries.Models.Items.BeerItem;
import dev.yashkalia.groceries.Models.Items.BreadItem;
import dev.yashkalia.groceries.Models.Items.Item;
import dev.yashkalia.groceries.Repositories.DiscountTypes.BeerDiscountRepository;
import dev.yashkalia.groceries.Repositories.Items.BeerRepository;
import dev.yashkalia.groceries.Repositories.Items.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

//    @Autowired
//    private BeerDiscountRepository beerDiscountRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

//    public List<BeerDiscount> getAllBeerDiscountItems(){
//        return beerDiscountRepository.findAll();
//    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Long updateItem(String id, Integer updatedPrice){

        return mongoTemplate.update(Item.class)
                .matching(Criteria.where("_id").is(id))
                .apply(new Update().set("Price", updatedPrice)).first().getModifiedCount();

    }
}
