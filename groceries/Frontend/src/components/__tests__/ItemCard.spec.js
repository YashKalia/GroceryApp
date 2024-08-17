import { mount } from '@vue/test-utils'
import { describe, it, expect, beforeEach, vi } from 'vitest'
import ItemCard from '../ItemCards/ItemCard.vue'
import { useItemStore } from '@/itemstore.js'

// Mock the item store
vi.mock('@/itemstore.js', () => ({
  useItemStore: vi.fn()
}))

describe('ItemCard.vue', () => {
  let wrapper
  let addToCartMock
  let removeFromCartMock

  beforeEach(() => {
    addToCartMock = vi.fn((props) => {
      const item = { props, itemCount: 1 }
      return item.itemCount
    })
    removeFromCartMock = vi.fn((id) => {
      return 0
    })
    useItemStore.mockReturnValue({
      addToCart: addToCartMock,
      removeFromCart: removeFromCartMock
    })

    wrapper = mount(ItemCard, {
      props: {
        itemName: 'Apple',
        price: 1.2,
        id: '1',
        itemType: 'Fruit',
        weight: 200,
        age: 5,
        productImage: 'apple.jpg'
      }
    })
  })

  it('calls addToCart and returns updated itemCount when increment button is clicked', async () => {
    await wrapper.find('.btn-success').trigger('click')
    expect(addToCartMock).toHaveBeenCalledWith(expect.objectContaining({
      itemName: 'Apple',
      price: 1.2,
      id: '1',
    }))
    expect(addToCartMock).toHaveReturnedWith(1)
  })

  it('calls removeFromCart and returns updated itemCount when decrement button is clicked', async () => {
    await wrapper.find('.btn-light').trigger('click')
    expect(removeFromCartMock).toHaveBeenCalledWith('1')
    expect(removeFromCartMock).toHaveReturnedWith(0)
  })

  it('displays correct item count when increment and decrement are clicked multiple times', async () => {
    // Simulate adding to cart multiple times
    addToCartMock.mockImplementation((props) => {
      const item = { props, itemCount: 3 }
      return item.itemCount
    })
    await wrapper.find('.btn-success').trigger('click')
    await wrapper.find('.btn-success').trigger('click')
    await wrapper.find('.btn-success').trigger('click')
    expect(wrapper.find('.card-body').text()).toContain('3 units chosen')

    // Simulate removing from cart multiple times
    removeFromCartMock.mockImplementation((id) => {
      return 1
    })
    await wrapper.find('.btn-light').trigger('click')
    await wrapper.find('.btn-light').trigger('click')
    expect(removeFromCartMock).toHaveBeenCalledTimes(2)
    expect(wrapper.find('.card-body').text()).toContain('1 units chosen')
  })

  it('displays item count as zero when removeFromCart is called enough times', async () => {
    // Simulate adding to cart
    addToCartMock.mockImplementation((props) => {
      const item = { props, itemCount: 3 }
      return item.itemCount
    })
    await wrapper.find('.btn-success').trigger('click')
    expect(wrapper.find('.card-body').text()).toContain('3 units chosen')

    // Simulate removing from cart
    removeFromCartMock.mockImplementation((id) => {
      return 0
    })
    await wrapper.find('.btn-light').trigger('click')
    await wrapper.find('.btn-light').trigger('click')
     await wrapper.find('.btn-light').trigger('click')
    expect(removeFromCartMock).toHaveBeenCalledTimes(3)
    expect(wrapper.find('.card-body').text()).not.toContain('units chosen')
  })

  it('should match snapshot', async () => {
    expect(wrapper).toMatchSnapshot();
  })
  
})