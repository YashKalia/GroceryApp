package dev.yashkalia.groceries.Repositories.Items;

import dev.yashkalia.groceries.Models.Items.BeerItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeerRepository extends MongoRepository<BeerItem, String> {
}
