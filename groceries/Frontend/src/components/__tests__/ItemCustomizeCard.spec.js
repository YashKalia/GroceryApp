// tests/unit/ItemCustomizeCard.spec.js
import { mount } from '@vue/test-utils';
import { describe, it, expect, beforeEach, vi, spyOn } from 'vitest';
import ItemCustomizeCard from '../ItemCards/ItemCustomizeCard.vue';
import { useItemStore } from '@/itemstore.js';
import { UpdateItem } from '@/services/CustomizeService.js';
import ItemCustomizeCard from '../ItemCards/ItemCustomizeCard.vue';

// Mock the store
vi.mock('@/itemstore.js', () => ({
  useItemStore: vi.fn()
}));

// Mock the UpdateItem service
vi.mock('@/services/CustomizeService.js', () => ({
  UpdateItem: vi.fn()
}));

describe('ItemCustomizeCard.vue', () => {
  let wrapper;
  let props;

  beforeEach(() => {
    props = {
      itemName: 'Test Item',
      price: 100,
      discountId: '123',
      isPack: false,
      id: '1',
      itemType: 'Type A',
      weight: 500,
      age: 10,
      productImage: 'test-image.jpg'
    };

    useItemStore.mockReturnValue({
      addToCart: vi.fn(),
      removeFromCart: vi.fn(),
      emptyEntireCart: vi.fn()
    });

    wrapper = mount(ItemCustomizeCard, {
      props
    });
  });

  it('renders correctly with given props', () => {
    expect(wrapper.find('.card-title').text()).toBe(props.itemName);
    expect(wrapper.find('.card-text-current-price').text()).toContain(`Current Price(€): ${props.price}`);
    expect(wrapper.find('img').attributes('src')).toBe(props.productImage);
    
  });

  it('binds updatedPrice correctly to the input field', async () => {
    const input = wrapper.find('input[type="number"]');
    await input.setValue(150);
    expect(wrapper.vm.updatedPrice).toBe(150);
  });

  it('calls UpdateItem service with correct parameters', async () => {
    const newPrice = 150;
    const input = wrapper.find('input[type="number"]');
    await input.setValue(newPrice);
    await wrapper.find('.btn-success').trigger('click');
    expect(UpdateItem).toHaveBeenCalledWith(props.id, newPrice);
  });

  it('should match snapshot', async () => {
    expect(wrapper).toMatchSnapshot();
  })
});