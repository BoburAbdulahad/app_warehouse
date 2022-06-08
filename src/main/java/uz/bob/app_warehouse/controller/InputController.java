package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.Input;
import uz.bob.app_warehouse.payload.InputDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @GetMapping
    public List<Input> getAll(){
        return inputService.getAll();
    }

    @GetMapping("/{id}")
    public Input getOneById(@PathVariable Integer id){
        return inputService.getOneById(id);
    }

    @PostMapping
    public Result add(@RequestBody InputDto inputDto){
        return inputService.add(inputDto);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody InputDto inputDto){
return null;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
return null;
    }


}
