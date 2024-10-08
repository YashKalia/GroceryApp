package dev.yashkalia.groceries.Models.Items;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

//    @Id
    private String ItemName;
    private Float Price;
    private String ItemType;
    private String id;
    private Integer Age;
    private String DiscountId;
    private Boolean IsPack;
    private Integer Weight;
    private String ProductImage;

    @Override
    public String toString() {
        return "Item{" +
                "ItemName='" + ItemName + '\'' +
                ", Price=" + Price +
                ", ItemType='" + ItemType + '\'' +
                ", id='" + id + '\'' +
                ", Age=" + Age +
                ", DiscountId='" + DiscountId + '\'' +
                ", IsPack=" + IsPack +
                ", Weight=" + Weight +
                ", ProductImage='" + ProductImage + '\'' +
                '}';
    }




}

