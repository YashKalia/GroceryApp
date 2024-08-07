package dev.yashkalia.groceries.Models.Items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="beeritems")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerItem {
    @Id
    private String id;
    private String ItemName;
    @Field("Price")
    private Integer Price;
    private String ItemType;
    private String DiscountId;
    private Boolean IsPack;
}