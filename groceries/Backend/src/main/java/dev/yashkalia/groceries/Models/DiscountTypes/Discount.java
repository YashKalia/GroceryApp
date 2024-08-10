package dev.yashkalia.groceries.Models.DiscountTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    @Id
    private ObjectId id;
    private String type;
    //Bread discount
    private Integer Age;
    private Integer Buy;
    private Integer Take;
    //Vegetable discount
    public int weightLowerLimit;
    public int weightUpperLimit;
    public float discountPercentage;
}
