//package dev.yashkalia.groceries.Controllers;
//
//import dev.yashkalia.groceries.Models.DiscountTypes.BeerDiscount;
//import dev.yashkalia.groceries.Models.DiscountTypes.BreadDiscount;
//import dev.yashkalia.groceries.Models.DiscountTypes.VegetableDiscount;
//import dev.yashkalia.groceries.Services.BeerDiscountService;
//import dev.yashkalia.groceries.Services.BreadService;
//import dev.yashkalia.groceries.Services.VegetableService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//    @RestController
//    @RequestMapping("/api/v1/discounts")
//    public class DiscountController {
//
//        @Autowired
//        private ItemService itemService;
//
//        @Autowired
//        private BreadService breadService;
//
//        @Autowired
//        private BeerDiscountService beerDiscountService;
//
//        @Autowired
//        private VegetableService vegetableService;
//
//        @RequestMapping("/bread")
//        @GetMapping
//        public ResponseEntity<List<BreadDiscount>> getAllBreadDiscountItems(){
//            return new ResponseEntity<>(breadService.getAllBreadDiscountItems(), HttpStatus.OK) ;
//        }
//        @RequestMapping("/beer")
//        @GetMapping
//        public ResponseEntity<List<BeerDiscount>> getAllBeerDiscountItems(){
//            return new ResponseEntity<>(beerDiscountService.getAllBeerDiscountItems(), HttpStatus.OK) ;
//        }
//
//        @RequestMapping("/vegetable")
//        @GetMapping
//        public ResponseEntity<List<VegetableDiscount>> getAllVegetableDiscountItems(){
//            return new ResponseEntity<>(vegetableService.getAllVegetableDiscountItems(), HttpStatus.OK) ;
//        }
//    }
