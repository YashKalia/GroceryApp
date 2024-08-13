package dev.yashkalia.groceries.Controllers;

import dev.yashkalia.groceries.Models.CartItem.CartItem;
import dev.yashkalia.groceries.Models.Items.Item;
import dev.yashkalia.groceries.Models.Reciept.Reciept;
import dev.yashkalia.groceries.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private ItemService itemService;

    @CrossOrigin
    @PostMapping(value = "/checkout", produces = "application/json")
    public ResponseEntity<Reciept> CheckOut(@RequestBody List<CartItem> cartItems){
        return new ResponseEntity<Reciept>(checkoutService.Checkout(cartItems), HttpStatus.OK) ;
    }

    @CrossOrigin
    @RequestMapping("/items")
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){
        return new ResponseEntity<List<Item>>(itemService.getAllItems(), HttpStatus.OK) ;
    }

    @CrossOrigin
    @PostMapping(value = "/updateItem", produces = "application/json")
    public ResponseEntity<Long> updateItem(@RequestBody Item updatedItem){
        System.out.println("id - "+updatedItem.getId() + "updatedprice - "+updatedItem.getPrice());
        return new ResponseEntity<>(itemService.updateItemPrice(updatedItem.getId(),updatedItem.getPrice()), HttpStatus.OK) ;
    }


}
