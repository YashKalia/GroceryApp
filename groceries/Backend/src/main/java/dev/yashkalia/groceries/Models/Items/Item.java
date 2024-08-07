package dev.yashkalia.groceries.Models.Items;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

//    @Id
    private String itemName;
    private Integer price;
    private String itemType;
    private String id;
    private Integer age;
    private String DiscountId;
    private Boolean IsPack;
    private Integer Weight;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", ItemName='" + itemName + '\'' +
                ", Price=" + price +
                ", ItemType='" + itemType + '\'' +
                ", Age=" + age +
                '}';
    }


}

