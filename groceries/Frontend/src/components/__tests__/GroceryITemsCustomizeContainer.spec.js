// GroceryItemsCustomizeContainer.spec.js
import { mount } from '@vue/test-utils';
import { describe, it, expect, beforeEach, vi } from 'vitest';
import { createPinia, setActivePinia } from 'pinia';
import GroceryItemsCustomizeContainer from '@/components/GroceryItemsCustomizeContainer.vue';
import ItemCustomizeCard from '@/components/ItemCards/ItemCustomizeCard.vue';
import { useItemStore } from '@/itemstore'// Adjust the import based on your store's location
import { createTestingPinia } from '@pinia/testing'

// Mock the getAllItems service
vi.mock('@/services/ItemService', () => ({
  getAllItems: vi.fn(),
}));

describe('GroceryItemsCustomizeContainer.vue', () => {
  let pinia;
  let itemStore;
  let wrapper; // Declare wrapper here

  beforeEach(() => {
    pinia = createTestingPinia({
      createSpy: vi.fn, // Configure spy functions
    });

    setActivePinia(pinia);
    itemStore = useItemStore();

    // Initialize store state before each test
    itemStore.$state = {
      cartItems: [
        { id: 1, itemName: 'Apple', itemType: 'Fruit', price: 1.2, isPack: false, discountId: null, age: 7, weight: 0.5, productImage: 'apple.jpg' },
        { id: 2, itemName: 'Broccoli', itemType: 'Vegetable', price: 2.0, isPack: false, discountId: null, age: 3, weight: 0.6, productImage: 'broccoli.jpg' }
      ],
    };
  });

  it('renders without crashing', async () => {
    wrapper = mount(GroceryItemsCustomizeContainer, {
      global: {
        plugins: [pinia],
      },
    });

    // Wait for the component to process the mounted hook
    await wrapper.vm.$nextTick();

    // Check that the items are correctly handled by the store
    expect(itemStore.cartItems).toHaveLength(2);
    // console.log("Wrapper", wrapper.vm)
    expect(wrapper.vm.item).toEqual(['Fruit', 'Vegetable']);

    // Check if the correct number of ItemCustomizeCard components are rendered
    const cards = wrapper.findAllComponents(ItemCustomizeCard);
    expect(cards).toHaveLength(2);

    // Check if the first card has correct props
    const firstCardProps = cards[0].props();
    expect(firstCardProps.itemName).toBe('Apple');
    expect(firstCardProps.price).toBe(1.2);
    expect(firstCardProps.itemType).toBe('Fruit');
  });

  it('renders h1 title correctly for each item type', async () => {
    wrapper = mount(GroceryItemsCustomizeContainer, {
      global: {
        plugins: [pinia],
      },
    });

    // Wait for the component to process the mounted hook
    await wrapper.vm.$nextTick();

    // Check the presence and content of h1 tags
    const titles = wrapper.findAll('.ProductTitle h1');
    expect(titles).toHaveLength(2);
    expect(titles[0].text()).toBe('Customize Fruit');
    expect(titles[1].text()).toBe('Customize Vegetable');
  });
});