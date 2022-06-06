package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.Warehouse;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getAll(){
        return warehouseService.getAll();
    }

    @GetMapping("/{id}")
    public Warehouse getByIds(@PathVariable Integer id){
        return warehouseService.getByIds(id);
    }

    @PostMapping
    public Result add(@RequestBody Warehouse warehouse){
        return warehouseService.add(warehouse);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Warehouse warehouse){
        return warehouseService.edit(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return warehouseService.delete(id);
    }

}
