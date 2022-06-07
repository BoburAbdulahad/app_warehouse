package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.Supplier;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping
    public List<Supplier> getAll(){
       return supplierService.getAll();
    }

    @GetMapping("/{id}")
    public Supplier getOneById(@PathVariable Integer id){
        return supplierService.getOneById(id);
    }

    @PostMapping
    public Result add(@RequestBody Supplier supplier){
        return supplierService.add(supplier);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Supplier supplier){
        return supplierService.edit(id, supplier);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return supplierService.delete(id);
    }
}
