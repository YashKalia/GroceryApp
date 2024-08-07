package dev.yashkalia.groceries.Services;

import dev.yashkalia.groceries.Models.DiscountTypes.BeerDiscount;
import dev.yashkalia.groceries.Models.Items.BeerItem;
import dev.yashkalia.groceries.Repositories.DiscountTypes.BeerDiscountRepository;
import dev.yashkalia.groceries.Repositories.Items.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    @Autowired
    private BeerDiscountRepository beerDiscountRepository;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<BeerDiscount> getAllBeerDiscountItems(){
        return beerDiscountRepository.findAll();
    }

    public List<BeerItem> getAllBeerItems(){
        return beerRepository.findAll();
    }

    public Long updateBeerItem(String id, Integer updatedPrice){

        return mongoTemplate.update(BeerItem.class)
                .matching(Criteria.where("_id").is(id))
                .apply(new Update().set("Price",updatedPrice)).first().getModifiedCount ();

    }
}
