package dev.yashkalia.groceries.ServiceTests;

import dev.yashkalia.groceries.Models.CartItem.CartItem;
import dev.yashkalia.groceries.Models.DiscountTypes.Discount;
import dev.yashkalia.groceries.Models.Items.Item;
import dev.yashkalia.groceries.Models.Reciept.Reciept;
import dev.yashkalia.groceries.Repositories.DiscountTypes.DiscountRepository;
import dev.yashkalia.groceries.Services.DiscountService;
import dev.yashkalia.groceries.Services.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscountServiceTests {

    @Mock
    private ItemService itemService;

    @Mock
    private DiscountRepository discountRepository;

    @InjectMocks
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApplyApplicableDiscountForBeer_WithoutPack() {
        // Arrange
        Item beerItem = new Item("Beer", 3.99f, "Beer", "456def", 30, "DISC20", false, 500, "beer.jpg");
        CartItem cartItem = new CartItem(6, beerItem);
        List<CartItem> cartItems = List.of(cartItem);

        List<Item> beerItems = List.of(new Item("Beer 6-Pack", 20.0f, "Beer", "DISC20", 30, "DISC20", true, 3000, "beer_6pack.jpg"));
        when(itemService.GetAllBeerItems()).thenReturn(beerItems);

        // Act
        Reciept reciept = discountService.applyApplicableDiscount(cartItems);

        // Assert
        assertNotNull(reciept);
        assertEquals(1, reciept.getReceiptItems().size());
        assertEquals(20.0f, reciept.getReceiptItems().get(0).getTotalPrice());
        assertEquals(20.0f, reciept.getTotalOrderPrice());
    }

    @Test
    void testApplyApplicableDiscountForBeer_WithPack() {
        // Arrange
        Item beerItem = new Item("Beer", 3.99f, "Beer", "456def", 30, "DISC20", true, 500, "beer.jpg");
        CartItem cartItem = new CartItem(1, beerItem);
        List<CartItem> cartItems = List.of(cartItem);

        List<Item> beerItems = List.of(new Item("Beer 6-Pack", 20.0f, "Beer", "DISC20", 30, "DISC20", true, 3000, "beer_6pack.jpg"));
        when(itemService.GetAllBeerItems()).thenReturn(beerItems);

        // Act
        Reciept reciept = discountService.applyApplicableDiscount(cartItems);

        // Assert
        assertNotNull(reciept);
        assertEquals(1, reciept.getReceiptItems().size());
        assertEquals(3.99f, reciept.getReceiptItems().get(0).getTotalPrice());
        assertEquals(3.99f, reciept.getTotalOrderPrice());
    }

    @Test
    void testApplyApplicableDiscountForBeer_MultipleItems() {
        // Arrange
        Item beerItem = new Item("Beer", 3.99f, "Beer", "456def", 30, "DISC20", true, 500, "beer.jpg");
        Item beerItemPack = new Item("Beer", 3.99f, "Beer", "456def", 30, "DISC20", false, 500, "beer.jpg");

        CartItem cartItem = new CartItem(1, beerItem);
        CartItem cartItemPack = new CartItem(1, beerItemPack);

        List<CartItem> cartItems = List.of(cartItem,cartItemPack);

        List<Item> beerItems = List.of(new Item("Beer 6-Pack", 20.0f, "Beer", "DISC20", 30, "DISC20", true, 3000, "beer_6pack.jpg"));
        when(itemService.GetAllBeerItems()).thenReturn(beerItems);

        // Act
        Reciept reciept = discountService.applyApplicableDiscount(cartItems);

        // Assert
        assertNotNull(reciept);
        assertEquals(2, reciept.getReceiptItems().size());
        assertEquals(3.99f, reciept.getReceiptItems().get(0).getTotalPrice());
        assertEquals(3.99f, reciept.getReceiptItems().get(1).getTotalPrice());
        assertEquals(7.98f, reciept.getTotalOrderPrice());
    }

    @Test
    void testApplyApplicableDiscountForBread_WithDiscount() {
        // Arrange
        Item breadItem = new Item("Bread", 2.0f, "Bread", "123abc", 1, "DISC10", false, 500, "bread.jpg");
        CartItem cartItem = new CartItem(3, breadItem);
        List<CartItem> cartItems = List.of(cartItem);

        Discount breadDiscount = new Discount(
                "breadDiscount123",    // id
                "BreadDiscount",               // type
                1,                     // Age (e.g., 5 days old bread)
                2,                     // Buy 2
                1,                     // Take 1 free
                0,                     // weightLowerLimit (not applicable)
                0,                     // weightUpperLimit (not applicable)
                0.0f,                  // discountPercentage (not applicable)
                null                   // sixPackDiscountPrice (not applicable)
        );

        when(discountRepository.findAll()).thenReturn(List.of(breadDiscount));

        // Act
        Reciept reciept = discountService.applyApplicableDiscount(cartItems);

        // Assert
        assertNotNull(reciept);
        assertEquals(1, reciept.getReceiptItems().size());
        assertEquals(6.0f, reciept.getTotalOrderPrice());
        assertEquals(3 + 1, reciept.getReceiptItems().get(0).getCartItem().getItemCount()); // 3 bought + 1 free
    }

    @Test
    void testApplyApplicableDiscountForBread_WithoutDiscount() {
        // Arrange
        Item breadItem = new Item("Bread", 2.0f, "Bread", "123abc", 1, "DISC10", false, 500, "bread.jpg");
        CartItem cartItem = new CartItem(3, breadItem);
        List<CartItem> cartItems = List.of(cartItem);

        Discount breadDiscount = new Discount(
                "breadDiscount123",    // id
                "BreadDiscount",               // type
                5,                     // Age (e.g., 5 days old bread)
                2,                     // Buy 2
                1,                     // Take 1 free
                0,                     // weightLowerLimit (not applicable)
                0,                     // weightUpperLimit (not applicable)
                0.0f,                  // discountPercentage (not applicable)
                null                   // sixPackDiscountPrice (not applicable)
        );

        when(discountRepository.findAll()).thenReturn(List.of(breadDiscount));

        // Act
        Reciept reciept = discountService.applyApplicableDiscount(cartItems);

        // Assert
        assertNotNull(reciept);
        assertEquals(1, reciept.getReceiptItems().size());
        assertEquals(6.0f, reciept.getTotalOrderPrice());
        assertEquals(3, reciept.getReceiptItems().get(0).getCartItem().getItemCount()); // 3 bought
    }

    @Test
    void testApplyApplicableDiscountForVegetables_WithDiscount() {
        // Arrange
        Item vegetableItem = new Item("Carrot", 1.5f, "Vegetable", "789ghi", 1, "DISC30", false, 200, "carrot.jpg");
        CartItem cartItem = new CartItem(5, vegetableItem);
        List<CartItem> cartItems = List.of(cartItem);

        Discount vegetableDiscount = new Discount(
                "vegetableDiscount123",    // id
                "Vegetable",                  // type
                0,                            // Age (not applicable)
                0,                            // Buy (not applicable)
                0,                            // Take (not applicable)
                500,                          // weightLowerLimit
                2000,                         // weightUpperLimit
                10.0f,                        // discountPercentage (not applicable)
                null                          // sixPackDiscountPrice (not applicable)
        );
        when(discountRepository.findAll()).thenReturn(List.of(vegetableDiscount));

        // Act
        Reciept reciept = discountService.applyApplicableDiscount(cartItems);

        // Assert
        assertNotNull(reciept);
        assertEquals(1, reciept.getReceiptItems().size());
        assertEquals(6.75f, reciept.getTotalOrderPrice()); // 5 * 1.5 * 0.9 = 6.75
    }

    @Test
    void testApplyApplicableDiscountForVegetables_WithoutDiscount() {
        // Arrange
        Item vegetableItem = new Item("Carrot", 1.5f, "Vegetable", "789ghi", 1, "DISC30", false, 200, "carrot.jpg");
        CartItem cartItem = new CartItem(5, vegetableItem);
        List<CartItem> cartItems = List.of(cartItem);

        Discount vegetableDiscount = new Discount(
                "vegetableDiscount123",    // id
                "Vegetable",                  // type
                0,                            // Age (not applicable)
                0,                            // Buy (not applicable)
                0,                            // Take (not applicable)
                300,                          // weightLowerLimit
                400,                         // weightUpperLimit
                10.0f,                        // discountPercentage (not applicable)
                null                          // sixPackDiscountPrice (not applicable)
        );
        when(discountRepository.findAll()).thenReturn(List.of(vegetableDiscount));

        // Act
        Reciept reciept = discountService.applyApplicableDiscount(cartItems);

        // Assert
        assertNotNull(reciept);
        assertEquals(1, reciept.getReceiptItems().size());
        assertEquals(7.5f, reciept.getTotalOrderPrice());
    }


}
