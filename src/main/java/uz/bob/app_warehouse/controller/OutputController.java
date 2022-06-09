package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.Output;
import uz.bob.app_warehouse.payload.OutputDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @GetMapping
    public List<Output> getAll(){
        return outputService.getAll();
    }
    @GetMapping("/{id}")
    public Output getOneById(@PathVariable Integer id){
        return outputService.getOneById(id);
    }

    @PostMapping
    public Result add(@RequestBody OutputDto outputDto){
        return outputService.add(outputDto);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody OutputDto outputDto){
        return outputService.edit(id, outputDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return outputService.delete(id);
    }


}
