import { mount } from '@vue/test-utils';
import { describe, it, expect, vi } from 'vitest';
import GroceryItemsGridContainer from '../Containers/GroceryItemsGridContainer.vue';
import { getAllItems } from '@/services/ItemService';
import { CheckOut } from '@/services/CheckoutService';
import flushPromises from 'flush-promises';

// Mock the services
vi.mock('@/services/ItemService', () => ({
  getAllItems: vi.fn(),
}));

vi.mock('@/services/CheckoutService', () => ({
  CheckOut: vi.fn(),
}));

vi.mock('@/itemstore', () => ({
  useItemStore: vi.fn(() => ({
    cartItems: [],
    emptyEntireCart: vi.fn(),
  })),
}));

describe('GroceryItemsGridContainer.vue', () => {
  it('mounts and fetches items', async () => {
    // Arrange
    const items = [
      { itemName: 'Item 1', price: 10, itemType: 'Type 1', id: '1', isPack: false, discountId: 'd1', age: 1, weight: 1, productImage: 'img1.jpg' },
      { itemName: 'Item 2', price: 20, itemType: 'Type 2', id: '2', isPack: false, discountId: 'd2', age: 2, weight: 2, productImage: 'img2.jpg' },
    ];

    const itemTypes = ['Type 1', 'Type 2'];
    getAllItems.mockResolvedValue(items);

    // Act
    const wrapper = mount(GroceryItemsGridContainer);
    await flushPromises();

    // Assert
    expect(getAllItems).toHaveBeenCalled();
    expect(wrapper.vm.items).toEqual(items);
    expect(wrapper.vm.itemTypes).toEqual(itemTypes);
  });

  it('renders item types correctly', async () => {
    // Arrange
    const items = [
      { itemName: 'Item 1', price: 10, itemType: 'Type 1', id: '1', isPack: false, discountId: 'd1', age: 1, weight: 1, productImage: 'img1.jpg' },
      { itemName: 'Item 2', price: 20, itemType: 'Type 2', id: '2', isPack: false, discountId: 'd2', age: 2, weight: 2, productImage: 'img2.jpg' },
    ];
    getAllItems.mockResolvedValue(items);

    // Act
    const wrapper = mount(GroceryItemsGridContainer);
     await flushPromises();
    //  await wrapper.vm.$nextTick(); // Wait for the next DOM update cycle

    // Assert
    // const itemTypes = wrapper.findAll('.product-type-heading');
    const itemTypes = wrapper.findAll('.product-type-heading');
    expect(itemTypes).toHaveLength(2);
    expect(itemTypes[0].text()).toBe('Type 1');
    expect(itemTypes[1].text()).toBe('Type 2');
  });

  it('triggers checkout process on button click', async () => {
    // Arrange
    const receipt = { total: 30 };
    CheckOut.mockResolvedValue(receipt);
    const wrapper = mount(GroceryItemsGridContainer);

    // Act
    await wrapper.find('button.CheckoutButton').trigger('click');
    await wrapper.vm.$nextTick(); // Wait for the next DOM update cycle

    // Assert
    expect(CheckOut).toHaveBeenCalled();
    expect(wrapper.vm.reciept).toEqual(receipt);
    expect(wrapper.vm.isRecieptVisible).toBe(true);
  });

  it('should match snapshot', async () => {
    // Arrange
    const items = [
      { itemName: 'Item 1', price: 10, itemType: 'Type 1', id: '1', isPack: false, discountId: 'd1', age: 1, weight: 1, productImage: 'img1.jpg' },
      { itemName: 'Item 2', price: 20, itemType: 'Type 2', id: '2', isPack: false, discountId: 'd2', age: 2, weight: 2, productImage: 'img2.jpg' },
    ];

    const itemTypes = ['Type 1', 'Type 2'];
    getAllItems.mockResolvedValue(items);

    // Act
    const wrapper = mount(GroceryItemsGridContainer);
    await flushPromises();

    // Assert
    expect(wrapper).toMatchSnapshot();
  });




});