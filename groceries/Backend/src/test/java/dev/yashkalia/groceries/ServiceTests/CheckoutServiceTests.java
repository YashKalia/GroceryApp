package dev.yashkalia.groceries.ServiceTests;

import dev.yashkalia.groceries.Models.CartItem.CartItem;
import dev.yashkalia.groceries.Models.Items.Item;
import dev.yashkalia.groceries.Models.Reciept.Reciept;
import dev.yashkalia.groceries.Models.Reciept.RecieptItem;
import dev.yashkalia.groceries.Services.CheckoutService;
import dev.yashkalia.groceries.Services.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CheckoutServiceTests {

	@Mock
	private DiscountService discountService;

	@InjectMocks
	private CheckoutService checkoutService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCheckout() {
		// Arrange
		CartItem cartItem = new CartItem(2, new Item("Bread", 2.0f, "Bread", "123abc", 1, "DISC10", false, 500, "bread.jpg"));
		List<CartItem> cartItems = List.of(cartItem);

		Reciept expectedReciept = new Reciept(List.of(new RecieptItem(cartItem, 4.0f)), 4.0f);
		when(discountService.applyApplicableDiscount(cartItems)).thenReturn(expectedReciept);

		// Act
		Reciept actualReciept = checkoutService.Checkout(cartItems);

		// Assert
		assertNotNull(actualReciept);
		assertEquals(expectedReciept, actualReciept);

		verify(discountService, times(1)).applyApplicableDiscount(cartItems);
	}
}
