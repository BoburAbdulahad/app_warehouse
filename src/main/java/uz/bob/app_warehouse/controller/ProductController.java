package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.Product;
import uz.bob.app_warehouse.payload.ProductDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.AttachmentService;
import uz.bob.app_warehouse.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping
    public List<Product> get(){
        List<Product> products = productService.get();
        return products;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id,HttpServletResponse response){
        Product product = productService.getById(id);
        return product;
    }

    @PostMapping
    public Result add(@RequestBody ProductDto productDto){
        Result result = productService.add(productDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody ProductDto productDto){
        Result result = productService.edit(id, productDto);
        return result;
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = productService.delete(id);
        return result;
    }
}
