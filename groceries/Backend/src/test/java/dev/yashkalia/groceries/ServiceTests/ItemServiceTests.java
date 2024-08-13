package dev.yashkalia.groceries.ServiceTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTests {

    @Mock
    private MongoTemplate mongoTemplate;

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
}
