package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.Category;
import uz.bob.app_warehouse.payload.CategoryDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> get(){
        return categoryService.get();
    }

    @PostMapping
    public Result add(@RequestBody CategoryDto categoryDto){
        Result add = categoryService.add(categoryDto);
        return add;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        Result edit = categoryService.edit(id, categoryDto);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result delete = categoryService.delete(id);
        return delete;
    }

}
