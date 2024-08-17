package dev.yashkalia.groceries.Services;

import com.mongodb.client.result.UpdateResult;
import dev.yashkalia.groceries.Enums.ItemType;
import dev.yashkalia.groceries.Models.Items.Item;
import dev.yashkalia.groceries.Repositories.Items.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Long updateItemPrice(String id, Float updatedPrice) {
        Update update = new Update().set("Price", updatedPrice);
        UpdateResult result = mongoTemplate.updateFirst(
                new Query(Criteria.where("_id").is(id)),
                update,
                Item.class
        );
        return result.getModifiedCount();
    }


    public List<Item> GetAllBeerItems(){
        return this.getAllItems().stream().filter(item -> {
            return ItemType.valueOf(item.getItemType()) == ItemType.Beer;
        }).toList();
    }
}
