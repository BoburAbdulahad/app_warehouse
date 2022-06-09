package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.InputProduct;
import uz.bob.app_warehouse.payload.InputDto;
import uz.bob.app_warehouse.payload.InputProductDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @GetMapping
    public List<InputProduct> getAll(){
        return inputProductService.getAll();
    }

    @GetMapping("/{id}")
    public InputProduct get(@PathVariable Integer id){
        return inputProductService.getOneById(id);
    }

    @PostMapping
    public Result add(@RequestBody InputProductDto inputProductDto){
        return inputProductService.add(inputProductDto);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody InputProductDto inputProductDto){
        return inputProductService.edit(id, inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return inputProductService.delete(id);
    }



}
