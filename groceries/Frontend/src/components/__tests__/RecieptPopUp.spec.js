import { mount } from '@vue/test-utils';
import { describe, it, expect, vi } from 'vitest';
import RecieptPopUp from '../Reciept/RecieptPopUp.vue';

describe('RecieptPopUp.vue', () => {
  it('renders receipt items correctly', () => {
    const reciept = {
      receiptItems: [
        { id: '1', cartItem: { itemCount: 2, props: { itemName: 'Item 1' } }, totalPrice: 20 },
        { id: '2', cartItem: { itemCount: 1, props: { itemName: 'Item 2' } }, totalPrice: 10 },
      ],
      totalOrderPrice: 30,
    };

    const wrapper = mount(RecieptPopUp, {
      props: {
        reciept,
        isVisible: true,
      },
    });

    const items = wrapper.findAll('li');
    expect(items).toHaveLength(2);
    expect(items[0].text()).toBe('2 x Item 1 = €20');
    expect(items[1].text()).toBe('1 x Item 2 = €10');
  });

  it('renders total order price correctly', () => {
    const reciept = {
      receiptItems: [],
      totalOrderPrice: 30,
    };

    const wrapper = mount(RecieptPopUp, {
      props: {
        reciept,
        isVisible: true,
      },
    });

    const total = wrapper.find('p:nth-of-type(2)');
    expect(total.text()).toBe('Total(€): 30');
  });

  it('emits update:visible event when close button is clicked', async () => {
    const reciept = {
      receiptItems: [],
      totalOrderPrice: 0,
    };

    const wrapper = mount(RecieptPopUp, {
      props: {
        reciept,
        isVisible: true,
      },
    });

    await wrapper.find('button').trigger('click');
    expect(wrapper.emitted('update:visible')).toBeTruthy();
    expect(wrapper.emitted('update:visible')[0]).toEqual([false]);
  });

  it('does not render when isVisible is false', () => {
    const reciept = {
      receiptItems: [],
      totalOrderPrice: 0,
    };

    const wrapper = mount(RecieptPopUp, {
      props: {
        reciept,
        isVisible: false,
      },
    });
    console.log("wrapper.html()", wrapper.html())
    expect(wrapper.html()).toBe('<!--v-if-->');
  });

  it('should match snapshot', async () => {
    const reciept = {
        receiptItems: [
          { id: '1', cartItem: { itemCount: 2, props: { itemName: 'Item 1' } }, totalPrice: 20 },
          { id: '2', cartItem: { itemCount: 1, props: { itemName: 'Item 2' } }, totalPrice: 10 },
        ],
        totalOrderPrice: 30,
      };
  
      const wrapper = mount(RecieptPopUp, {
        props: {
          reciept,
          isVisible: true,
        },
      });
      expect(wrapper).toMatchSnapshot();
  })
});