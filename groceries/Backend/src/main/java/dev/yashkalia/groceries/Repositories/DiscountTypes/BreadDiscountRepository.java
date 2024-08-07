package dev.yashkalia.groceries.Repositories.DiscountTypes;

import dev.yashkalia.groceries.Models.DiscountTypes.BreadDiscount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreadDiscountRepository extends MongoRepository<BreadDiscount, ObjectId> {
}