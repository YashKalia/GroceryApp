package dev.yashkalia.groceries.Services;
import dev.yashkalia.groceries.Models.DiscountTypes.BreadDiscount;
import dev.yashkalia.groceries.Models.Items.BeerItem;
import dev.yashkalia.groceries.Models.Items.BreadItem;
import dev.yashkalia.groceries.Repositories.DiscountTypes.BreadDiscountRepository;
import dev.yashkalia.groceries.Repositories.Items.BreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreadService {

    @Autowired
    private BreadDiscountRepository breadDiscountRepository;

    @Autowired
    private BreadRepository breadRepository;

    public List<BreadDiscount> getAllBreadDiscountItems(){
        return breadDiscountRepository.findAll();
    }

    public List<BreadItem> getAllBreadItems(){
        return breadRepository.findAll();
    }

    @Autowired
    private MongoTemplate mongoTemplate;


    public Long updateBreadItem(String id, Integer updatedPrice){

        return mongoTemplate.update(BreadItem.class)
                .matching(Criteria.where("_id").is(id))
                .apply(new Update().set("Price", updatedPrice)).first().getModifiedCount ();

    }
}
