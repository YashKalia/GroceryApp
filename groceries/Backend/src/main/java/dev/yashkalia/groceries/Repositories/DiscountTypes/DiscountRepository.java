package dev.yashkalia.groceries.Repositories.DiscountTypes;
import dev.yashkalia.groceries.Models.DiscountTypes.Discount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface  DiscountRepository extends MongoRepository<Discount, ObjectId> {
}
