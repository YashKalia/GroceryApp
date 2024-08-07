package dev.yashkalia.groceries.Services;

import dev.yashkalia.groceries.Models.DiscountTypes.VegetableDiscount;
import dev.yashkalia.groceries.Models.Items.BreadItem;
import dev.yashkalia.groceries.Models.Items.VegetableItem;
import dev.yashkalia.groceries.Repositories.DiscountTypes.VegetableDiscountRepository;
import dev.yashkalia.groceries.Repositories.Items.VegetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VegetableService {
    @Autowired
    private VegetableDiscountRepository vegetableDiscountRepository;

    @Autowired
    private VegetableRepository vegetableRepository;

    public List<VegetableDiscount> getAllVegetableDiscountItems(){
        return vegetableDiscountRepository.findAll();
    }

    public List<VegetableItem> getAllVegetableItems(){
        return vegetableRepository.findAll();
    }

    @Autowired
    private MongoTemplate mongoTemplate;


    public Long updateVegetableItem(String id, Integer updatedPrice){

        return mongoTemplate.update(VegetableItem.class)
                .matching(Criteria.where("_id").is(id))
                .apply(new Update().set("Price", updatedPrice)).first().getModifiedCount ();

    }
}
