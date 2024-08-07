package dev.yashkalia.groceries.Models.DiscountTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="vegetablediscount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VegetableDiscount {
    private ObjectId id;
    public String type;
    public int weightLowerLimit;
    public int weightUpperLimit;
    public float discountPercentage;
}
