package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import uz.bob.app_warehouse.entity.Supplier;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> getAll(){
        return supplierRepository.findAll();
    }

    public Supplier getOneById(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElseGet(Supplier::new);
    }

    public Result add(Supplier comeSupplier){
        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(comeSupplier.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Supplier already exist",false);
        Supplier supplier=new Supplier();
        supplier.setName(comeSupplier.getName());
        supplier.setPhoneNumber(comeSupplier.getPhoneNumber());
        supplierRepository.save(supplier);
        return new Result("Supplier added",true);
    }

    public Result edit(Integer id,Supplier supplier){
        boolean b = supplierRepository.existsByPhoneNumberAndIdNot(supplier.getPhoneNumber(), id);
        if (b)
            return new Result("This type phone number such us supplier already exist",false);
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()) {
            return new Result("Supplier not found",false);
        }
        Supplier editingSupplier = optionalSupplier.get();
        editingSupplier.setName(supplier.getName());
        editingSupplier.setPhoneNumber(supplier.getPhoneNumber());
        Supplier savedSupplier = supplierRepository.save(editingSupplier);
        return new Result("Supplier edited",true,savedSupplier);
    }

    public Result delete(Integer id){
        try {
            supplierRepository.deleteById(id);
            return new Result("Supplier deleted",true);
        }catch (Exception e){
            return new Result("Error in deleting",false);
        }
    }
}
