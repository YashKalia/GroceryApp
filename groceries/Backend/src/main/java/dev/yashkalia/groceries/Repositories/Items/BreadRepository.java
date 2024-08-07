package dev.yashkalia.groceries.Repositories.Items;

import dev.yashkalia.groceries.Models.Items.BreadItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreadRepository extends MongoRepository<BreadItem, String> {
}
