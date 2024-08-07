package dev.yashkalia.groceries.Repositories.DiscountTypes;

import dev.yashkalia.groceries.Models.DiscountTypes.VegetableDiscount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VegetableDiscountRepository extends MongoRepository<VegetableDiscount, ObjectId> {
}