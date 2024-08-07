
// stores/counter.js
import { defineStore } from 'pinia'

export const useItemStore = defineStore('Items', {
  state: () => {
    return { 
        cartItems: []
     }
  },
  actions: {
    addToCart(props) {
        var item = this.cartItems.find(element => element.props.id === props.id);
        if(!item)
            {
                var itemCount = 1
                this.cartItems.push({props, itemCount})
                return itemCount;
            }
        item.itemCount++;
        return item.itemCount;
      },

      removeFromCart(id) {
        var itemIndex = this.cartItems.findIndex(element => element.props.id === id);
        if(itemIndex == -1)
            return 0
        if(this.cartItems[itemIndex].itemCount == 1){
            this.cartItems.splice(itemIndex, 1);
            return 0
        }
        this.cartItems[itemIndex].itemCount--;
        return this.cartItems[itemIndex].itemCount;
        },

        emptyEntireCart(){
          this.cartItems = []
          console.log("this.cartItems", this.cartItems)
        }
  },
})