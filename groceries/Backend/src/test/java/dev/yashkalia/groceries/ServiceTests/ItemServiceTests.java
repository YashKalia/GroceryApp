package dev.yashkalia.groceries.ServiceTests;

import com.mongodb.client.result.UpdateResult;
import dev.yashkalia.groceries.Repositories.Items.ItemRepository;
import dev.yashkalia.groceries.Services.ItemService;
import dev.yashkalia.groceries.Models.Items.Item;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTests {


    @Mock
    private ItemRepository mockItemRepository;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private MongoTemplate mockMongoTemplate;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void SetUp() {
        // Initialize mocks created above
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void GetAllItemsTest_MockedInput_ReturnsCorrectly(){

        //Arrange
        List<Item> items = Instancio.ofList(Item.class).size(10).create();
        when(mockItemRepository.findAll()).thenReturn(items);

        // Act
        List<Item> result = itemService.getAllItems();

        assertNotNull(result);
        assertEquals(items, result);
        verify(mockItemRepository, times(1)).findAll();

    }

    @Test
    void UpdateItemTest_MockedInput_ReturnsCorrectly(){

        //Arrange
        List<Item> items = Instancio.ofList(Item.class).size(10).create();

        String id = items.get(0).getId();

        Float updatedPrice = 99.99F;

        ExecutableUpdateOperation.TerminatingUpdate<Item> terminatingUpdate = mock(ExecutableUpdateOperation.TerminatingUpdate<Item>.class);
        ExecutableUpdateOperation.TerminatingUpdate<Item> TerminatingUpdate = mock(ExecutableUpdateOperation.TerminatingUpdate<Item>.class);
        UpdateResult updateResult = mock(UpdateResult.class);
        // Mock the behavior of MongoTemplate and UpdateResult
        when(mockMongoTemplate.update(Item.class)
                .matching(Criteria.where("_id").is(id))
                .apply(new Update().set("Price", updatedPrice)).first()).thenReturn(any(UpdateResult.class));

        when(any(UpdateResult.class).getModifiedCount()).thenReturn(1L);

        // Act
        Long modifiedDocumentsCount = itemService.UpdateItem(id, updatedPrice);

        assertEquals(1L, modifiedDocumentsCount);
        verify(mockItemRepository, times(1)).findAll();

    }

}
