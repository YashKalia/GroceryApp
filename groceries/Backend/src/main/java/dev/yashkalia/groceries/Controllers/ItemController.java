package dev.yashkalia.groceries.Controllers;

import dev.yashkalia.groceries.Models.CartItem.CartItem;
import dev.yashkalia.groceries.Models.DiscountTypes.BeerDiscount;
import dev.yashkalia.groceries.Models.DiscountTypes.BreadDiscount;
import dev.yashkalia.groceries.Models.DiscountTypes.VegetableDiscount;
import dev.yashkalia.groceries.Models.Items.BeerItem;
import dev.yashkalia.groceries.Models.Items.BreadItem;
import dev.yashkalia.groceries.Models.Items.Item;
import dev.yashkalia.groceries.Models.Items.VegetableItem;
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
    private BeerService beerService;

    @Autowired
    private BreadService breadService;

    @Autowired
    private VegetableService vegetableService;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private ItemService itemService;

    @CrossOrigin
    @RequestMapping("/beer-items")
    public ResponseEntity<List<BeerItem>> getAllBeerItems(){
        return new ResponseEntity<List<BeerItem>>(beerService.getAllBeerItems(), HttpStatus.OK) ;
    }
    @CrossOrigin
    @RequestMapping("/beer-discounts")
    @GetMapping
    public ResponseEntity<List<BeerDiscount>> getAllBeerDiscountItems(){
        return new ResponseEntity<List<BeerDiscount>>(beerService.getAllBeerDiscountItems(), HttpStatus.OK) ;
    }

    @CrossOrigin
    @RequestMapping("/bread-items")
    @GetMapping
    public ResponseEntity<List<BreadItem>> getAllBreadItems(){
        List<BreadItem> items = breadService.getAllBreadItems();
        System.out.println("Bread items" +  items);
        return new ResponseEntity<List<BreadItem>>(items, HttpStatus.OK) ;
    }
    @CrossOrigin
    @RequestMapping("/bread-discounts")
    @GetMapping
    public ResponseEntity<List<BreadDiscount>> getAllBreadDiscountItems(){
        System.out.println("Hello there");
        return new ResponseEntity<List<BreadDiscount>>(breadService.getAllBreadDiscountItems(), HttpStatus.OK) ;
    }
    @CrossOrigin
    @RequestMapping("/vegetable-items")
    @GetMapping
    public ResponseEntity<List<VegetableItem>> getAllVegetableItems(){
        return new ResponseEntity<List<VegetableItem>>(vegetableService.getAllVegetableItems(), HttpStatus.OK) ;
    }

    @CrossOrigin
    @RequestMapping("/vegetable-discounts")
    @GetMapping
    public ResponseEntity<List<VegetableDiscount>> getAllVegetableDiscountItems(){
        return new ResponseEntity<List<VegetableDiscount>>(vegetableService.getAllVegetableDiscountItems(), HttpStatus.OK) ;
    }

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
    @PostMapping(value = "/updateBeerItem", produces = "application/json")
    public ResponseEntity<Long> UpdateBeerItem(@RequestBody BeerItem updatedItem){
        System.out.println("id - "+updatedItem.getId() + "updatedprice - "+updatedItem.getPrice());
        return new ResponseEntity<>(beerService.updateBeerItem(updatedItem.getId(),updatedItem.getPrice()), HttpStatus.OK) ;
    }

    @CrossOrigin
    @PostMapping(value = "/updateBreadItem", produces = "application/json")
    public ResponseEntity<Long> UpdateBreadItem(@RequestBody BreadItem updatedItem){
        System.out.println("id - "+updatedItem.getId() + "updatedprice - "+updatedItem.getPrice());
        return new ResponseEntity<>(breadService.updateBreadItem(updatedItem.getId(),updatedItem.getPrice()), HttpStatus.OK) ;
    }

    @CrossOrigin
    @PostMapping(value = "/updateVegetableItem", produces = "application/json")
    public ResponseEntity<Long> UpdateVegetableItem(@RequestBody VegetableItem updatedItem){
        System.out.println("id - "+updatedItem.getId() + "updatedprice - "+updatedItem.getPrice());
        return new ResponseEntity<>(vegetableService.updateVegetableItem(updatedItem.getId(),updatedItem.getPrice()), HttpStatus.OK) ;
    }


}
