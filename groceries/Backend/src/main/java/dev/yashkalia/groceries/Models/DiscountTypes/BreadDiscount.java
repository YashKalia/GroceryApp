package dev.yashkalia.groceries.Models.DiscountTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="breaddiscount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BreadDiscount {
    private ObjectId id;
    public int Age;
    public int Buy;
    public int Take;



}
