import { reactive } from 'vue'

export const store = reactive({
  cartItems: new Array(),
  addToCart(props) {
    // if(this.cartItems == undefined){
    //     this.cartItems = new Array({props, itemCount:1});
    //     return
    // }
    console.log("this.cartitmes is ",this.cartItems, "of type ", typeof(this.cartItems));
    var item = this.cartItems.find(element => element.id === props.id);
    console.log("Found item", item, this.cartItems)
    if(!item)
        {
            this.cartItems.push({props, itemCount: 1})
            console.log("Pushed new element to store", item)
            return item;
        }
    item.itemCount++;
    console.log("Store cartItems after incrementing count", item)
  },

  removeFromCart() {
    // var itemIndex = this.cartItems.findIndex(itemId);
    // if(itemIndex == -1)
    //     return
    // if(cartItems[itemIndex].itemCount < 1){
    //     cartItems.splice(itemIndex, 1);
    //     return
    // }
    // cartItems[itemIndex].itemCount--;
    console.log("Store cartItems after removing")
    }
})

