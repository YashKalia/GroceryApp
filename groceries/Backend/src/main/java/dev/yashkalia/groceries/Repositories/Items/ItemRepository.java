package dev.yashkalia.groceries.Repositories.Items;

import dev.yashkalia.groceries.Models.Items.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
}

