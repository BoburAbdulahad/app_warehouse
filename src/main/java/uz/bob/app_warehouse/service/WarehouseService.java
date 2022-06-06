package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.app_warehouse.entity.Warehouse;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Warehouse> getAll(){
        return warehouseRepository.findAll();
    }

    public Warehouse getByIds(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElseGet(Warehouse::new);
    }

    public Result add(Warehouse comeWarehouse){
        boolean existsByName = warehouseRepository.existsByName(comeWarehouse.getName());
        if (existsByName)
            return new Result("Warehouse already exist",false);
        Warehouse warehouse=new Warehouse();
        warehouse.setName(comeWarehouse.getName());
        warehouseRepository.save(warehouse);
        return new Result("Warehouse added",true);
    }

    public Result edit(Integer id,Warehouse warehouse){
        boolean existsByNameAndIdNot = warehouseRepository.existsByNameAndIdNot(warehouse.getName(), id);
        if (existsByNameAndIdNot)
            return new Result("Such name us warehouse already exist",false);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found",false);
        Warehouse editingWarehouse = optionalWarehouse.get();
        editingWarehouse.setName(warehouse.getName());
        Warehouse savedWarehouse = warehouseRepository.save(editingWarehouse);
        return new Result("Warehouse edited",true,savedWarehouse);
    }

    public Result delete(Integer id){
        try {
            warehouseRepository.deleteById(id);
            return new Result("Warehouse deleted",true);
        }catch (Exception e){
            return new Result("Error in deleting",false);
        }
    }

}
