package dev.yashkalia.groceries.Config;

import dev.yashkalia.groceries.Models.DiscountTypes.Discount;
import dev.yashkalia.groceries.Models.Items.Item;
import dev.yashkalia.groceries.Repositories.DiscountTypes.DiscountRepository;
import dev.yashkalia.groceries.Repositories.Items.ItemRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoDataSeeder implements ApplicationRunner {

    private final ItemRepository itemRepository;
    private final DiscountRepository discountRepository;

    public DemoDataSeeder(ItemRepository itemRepository, DiscountRepository discountRepository) {
        this.itemRepository = itemRepository;
        this.discountRepository = discountRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (itemRepository.count() > 0) {
            return;
        }

        Discount beerDiscount = new Discount();
        beerDiscount.setId("beer-discount");
        beerDiscount.setType("BeerDiscount");
        beerDiscount.setSixPackDiscountPrice(10);
        discountRepository.save(beerDiscount);

        Discount breadDiscount = new Discount();
        breadDiscount.setId("bread-discount");
        breadDiscount.setType("BreadDiscount");
        breadDiscount.setAge(3);
        breadDiscount.setBuy(2);
        breadDiscount.setTake(3);
        discountRepository.save(breadDiscount);

        Discount vegDiscount = new Discount();
        vegDiscount.setId("veg-discount");
        vegDiscount.setType("VegetableDiscount");
        vegDiscount.setWeightLowerLimit(100);
        vegDiscount.setWeightUpperLimit(500);
        vegDiscount.setDiscountPercentage(10f);
        discountRepository.save(vegDiscount);

        itemRepository.save(item("1", "Heineken", 2.5f, "Beer", 0, "2", false, 0,
                "https://images.unsplash.com/photo-1608270586620-248524c67de9?w=400"));
        itemRepository.save(item("2", "Heineken 6-pack", 12f, "Beer", 0, "beer-discount", true, 0,
                "https://images.unsplash.com/photo-1618885472179-5e474019f2a9?w=400"));
        itemRepository.save(item("3", "Sourdough", 3.2f, "Bread", 1, "bread-discount", false, 0,
                "https://images.unsplash.com/photo-1549931319-a545dcf3bc73?w=400"));
        itemRepository.save(item("4", "Whole Wheat", 2.8f, "Bread", 4, "bread-discount", false, 0,
                "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400"));
        itemRepository.save(item("5", "Tomatoes", 1.5f, "Vegetable", 0, "veg-discount", false, 250,
                "https://images.unsplash.com/photo-1546470427-e212b7d31075?w=400"));
        itemRepository.save(item("6", "Carrots", 1.2f, "Vegetable", 0, "veg-discount", false, 400,
                "https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?w=400"));
    }

    private static Item item(
            String id,
            String name,
            float price,
            String type,
            int age,
            String discountId,
            boolean isPack,
            int weight,
            String image
    ) {
        Item item = new Item();
        item.setId(id);
        item.setItemName(name);
        item.setPrice(price);
        item.setItemType(type);
        item.setAge(age);
        item.setDiscountId(discountId);
        item.setIsPack(isPack);
        item.setWeight(weight);
        item.setProductImage(image);
        return item;
    }
}
