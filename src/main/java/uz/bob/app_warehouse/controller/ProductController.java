package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.Product;
import uz.bob.app_warehouse.payload.ProductDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> get(){

    }

    @PostMapping
    public Result add(@RequestBody ProductDto productDto){
        Result result = productService.add(productDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result edit(){

    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){

    }
}
