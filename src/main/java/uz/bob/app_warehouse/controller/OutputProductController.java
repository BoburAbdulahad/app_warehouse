package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.OutputProduct;
import uz.bob.app_warehouse.payload.OutputProductDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @GetMapping
    public List<OutputProduct> getAll(){

    }

    @GetMapping("/{id}")
    public OutputProduct getOneById(@PathVariable Integer id){

    }

    @PostMapping
    public Result add(@RequestBody OutputProductDto outputProductDto){

    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody OutputProductDto outputProductDto){

    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){

    }

}
