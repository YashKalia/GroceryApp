package dev.yashkalia.groceries.Services;

import com.mongodb.client.result.UpdateResult;
import dev.yashkalia.groceries.Enums.ItemType;
import dev.yashkalia.groceries.Models.Items.Item;
import dev.yashkalia.groceries.Repositories.Items.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
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

    public Long UpdateItem(String id, Float updatedPrice){
        ExecutableUpdateOperation.TerminatingUpdate<Item> updateresult =  mongoTemplate.update(Item.class);


        return updateresult.getModifiedCount();
    };

    public List<Item> GetAllBeerItems(){

        return this.getAllItems().stream().filter(item -> {
            return ItemType.valueOf(item.getItemType()) == ItemType.Beer;
        }).toList();

    }
}
