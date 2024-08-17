package dev.yashkalia.groceries.ServiceTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import dev.yashkalia.groceries.Repositories.Items.ItemRepository;
import dev.yashkalia.groceries.Services.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.query.Update;
import dev.yashkalia.groceries.Models.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTests {

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateItemPrice() {
        String id = "someId";
        Float updatedPrice = 10.5f;

        // Mock the result of the update operation
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.getModifiedCount()).thenReturn(1L);
        when(mongoTemplate.updateFirst(any(Query.class), any(Update.class), eq(Item.class)))
                .thenReturn(updateResult);

        // Call the method
        Long modifiedCount = itemService.updateItemPrice(id, updatedPrice);

        // Verify the interactions and result
        assertEquals(1L, modifiedCount);
        verify(mongoTemplate, times(1)).updateFirst(any(Query.class), any(Update.class), eq(Item.class));
    }

    @Test
    void testGetAllItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Apple", 1.99f, "Fruit", "123abc", 7, "DISC10", false, 150, "apple.jpg"));
        items.add(new Item("Beer", 3.99f, "Beer", "456def", 30, "DISC20", false, 500, "beer.jpg"));

        when(itemRepository.findAll()).thenReturn(items);

        List<Item> result = itemService.getAllItems();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void testGetAllBeerItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Beer", 3.99f, "Beer", "456def", 30, "DISC20", false, 500, "beer.jpg"));
        items.add(new Item("6 Pack Beer", 2.49f, "Beer", "789ghi", 7, "DISC15", true, 1000, "beer.jpg"));

        when(itemRepository.findAll()).thenReturn(items);

        List<Item> beerItems = itemService.GetAllBeerItems();

        assertNotNull(beerItems);
        assertEquals(2, beerItems.size());
        assertEquals("Beer", beerItems.get(0).getItemName());
        assertEquals("6 Pack Beer", beerItems.get(1).getItemName());
        verify(itemRepository, times(1)).findAll();
    }

}
