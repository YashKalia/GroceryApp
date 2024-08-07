package dev.yashkalia.groceries.Repositories.DiscountTypes;

import dev.yashkalia.groceries.Models.DiscountTypes.BeerDiscount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerDiscountRepository extends MongoRepository<BeerDiscount, ObjectId> {
}