package dev.yashkalia.groceries.Models.Items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="vegetableitems")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VegetableItem {
    @Id
    private String id;
    private String ItemName;
    private int Price;
    private String ItemType;
    private int Weight;
}
