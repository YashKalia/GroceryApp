import { mount } from '@vue/test-utils';
import { describe, it, expect, vi } from 'vitest';
import { createTestingPinia } from '@pinia/testing';
import GroceryItemsCustomizeContainer from '../Containers/GroceryItemsCustomizeContainer.vue';
import { getAllItems } from '@/services/ItemService';
import flushPromises from 'flush-promises';

// Mock the getAllItems function
vi.mock('@/services/ItemService', () => ({
  getAllItems: vi.fn(),
}));

describe('GroceryItemsCustomizeContainer.vue', () => {
    it('mounts and fetches items', async () => {
        // Arrange
        const items = [
          { itemName: 'Item 1', price: 10, itemType: 'Type 1', id: '1', isPack: false, discountId: 'd1', age: 1, weight: 1, productImage: 'img1.jpg' },
          { itemName: 'Item 2', price: 20, itemType: 'Type 2', id: '2', isPack: false, discountId: 'd2', age: 2, weight: 2, productImage: 'img2.jpg' },
        ];
    
        const itemTypes = ['Type 1', 'Type 2'];
        getAllItems.mockResolvedValue(items);
    
        // Act
        const wrapper = mount(GroceryItemsCustomizeContainer, {
            global: {
              plugins: [createTestingPinia(
                  {            
                  createSpy: vi.fn()
              }
              )],
            },
          });
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
    const wrapper = mount(GroceryItemsCustomizeContainer, {
      global: {
        plugins: [createTestingPinia(
            {            
            createSpy: vi.fn()
        }
        )],
      },
    });
    await flushPromises();
    console.log("Wrapper html", wrapper.html());
    // Assert
    const itemTypes = wrapper.findAll('.product-type-heading');
    console.log("Item types", itemTypes);
    expect(itemTypes).toHaveLength(2);
    expect(itemTypes[0].text()).toBe('Customize Type 1');
    expect(itemTypes[1].text()).toBe('Customize Type 2');
  });

  it('should match Snapshot', async () => {
    // Arrange
    const items = [
      { itemName: 'Item 1', price: 10, itemType: 'Type 1', id: '1', isPack: false, discountId: 'd1', age: 1, weight: 1, productImage: 'img1.jpg' },
      { itemName: 'Item 2', price: 20, itemType: 'Type 2', id: '2', isPack: false, discountId: 'd2', age: 2, weight: 2, productImage: 'img2.jpg' },
    ];

    const itemTypes = ['Type 1', 'Type 2'];
    getAllItems.mockResolvedValue(items);

    // Act
    const wrapper = mount(GroceryItemsCustomizeContainer, {
        global: {
          plugins: [createTestingPinia(
              {            
              createSpy: vi.fn()
          }
          )],
        },
      });
    await flushPromises();

    // Assert
    expect(wrapper).toMatchSnapshot();
  });
});