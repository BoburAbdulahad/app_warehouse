package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.app_warehouse.entity.Input;
import uz.bob.app_warehouse.payload.InputDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.payload.UniversalClass;
import uz.bob.app_warehouse.repository.CurrencyRepository;
import uz.bob.app_warehouse.repository.InputRepository;
import uz.bob.app_warehouse.repository.SupplierRepository;
import uz.bob.app_warehouse.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    SupplierRepository supplierRepository;

    public List<Input> getAll(){
        return inputRepository.findAll();
    }

    public Input getOneById(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElseGet(Input::new);
    }

    public Result add(InputDto inputDto){// TODO: 6/8/2022 postman orqali vaqtni 1 secund farq bn kiritilsa aynan bir xil warehouse ga aynan bir supplier, cheklov iwlamayapti

//        Timestamp timestamp=Timestamp.valueOf(inputDto.getDate());
//
//        List<LocalTime> intervalTimes = inputRepository.intervalTimes(timestamp.toString(), inputDto.getSupplierId());
//        for (LocalTime intervalTime : intervalTimes) {//shu joyda todo bor
//            if (intervalTime.getHour()<2)
//                return new Result("Interval time don't support!",false);
//        }
//
//        boolean existsByDateAndSupplierId = inputRepository.existsByDateAndSupplierId(timestamp, inputDto.getSupplierId());
//        if (existsByDateAndSupplierId) {
//            return new Result("This time such as supplier already exist",false);
//        }
        if (!warehouseRepository.findById(inputDto.getWarehouseId()).isPresent()) {
            return new Result("Warehouse not found",false);
        }
        if (!supplierRepository.existsById(inputDto.getSupplierId())) {
            return new Result("Supplier not found",false);
        }
        if (!currencyRepository.existsById(inputDto.getCurrencyId())) {
            return new Result("Currency not found",false);
        }

        Timestamp timestamp=Timestamp.from(Instant.now());
        Input input=new Input();
        input.setDate(timestamp);
        input.setWarehouse(warehouseRepository.getReferenceById(inputDto.getWarehouseId()));
        input.setSupplier(supplierRepository.getReferenceById(inputDto.getSupplierId()));
        input.setCurrency(currencyRepository.getReferenceById(inputDto.getCurrencyId()));

        int size = inputRepository.sizeOfInput();
        input.setFactureNumber(String.valueOf(size+1));// TODO: 6/9/2022 facture number ni avtomatik qilib oladigan qildim, supplier_id,warehouse_id va facture_number uchalasi birga qaytadan kelsa owa xolatda cheklov qoyish mumkin
        input.setCode(UniversalClass.forRandomCode());

        inputRepository.save(input);
        return new Result("Input added",true);
    }

    public Result edit(Integer id,InputDto inputDto){
//        boolean b = inputRepository.existsByDateAndSupplierIdAndIdNot( inputDto.getSupplierId(), id);
//        if (b)
//            return new Result("This supplier for its time already exist",false);

        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) {
            return new Result("Input not found",false);
        }
        Input editingInput = optionalInput.get();

        editingInput.setWarehouse(warehouseRepository.getReferenceById(inputDto.getWarehouseId()));
        editingInput.setSupplier(supplierRepository.getReferenceById(inputDto.getSupplierId()));
        editingInput.setCurrency(currencyRepository.getReferenceById(inputDto.getCurrencyId()));

        Input savedInput = inputRepository.save(editingInput);
        return new Result("Input edited",true,savedInput);
    }

    public Result delete(Integer id){
        try {
            inputRepository.deleteById(id);
            return new Result("Input deleted",true);
        }catch (Exception e){
            return new Result("Error in deleting input",false);
        }
    }

}
