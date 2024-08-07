package dev.yashkalia.groceries.Models.DiscountTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="beerdiscount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerDiscount {
    @Id
    private ObjectId id;
    private String ItemName;
    public int SixPackDiscountPrice;
}
