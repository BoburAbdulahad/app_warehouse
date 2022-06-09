package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;
import uz.bob.app_warehouse.entity.Output;
import uz.bob.app_warehouse.payload.OutputDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.payload.UniversalClass;
import uz.bob.app_warehouse.repository.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;

    public List<Output> getAll(){
        return outputRepository.findAll();
    }

    public Output getOneById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElseGet(Output::new);
    }

    public Result add(OutputDto outputDto){
        if (!warehouseRepository.existsById(outputDto.getWarehouseId())) {
            return new Result("Warehouse not found",false);
        }
        if (clientRepository.existsById(outputDto.getClientId())) {
            return new Result("Client not found",false);
        }
        if (!currencyRepository.existsById(outputDto.getCurrencyId())) {
            return new Result("Currency not found",false);
        }

        Timestamp timestamp=Timestamp.from(Instant.now());
        Output output=new Output();
        output.setDate(timestamp);
        output.setWarehouse(warehouseRepository.getReferenceById(outputDto.getWarehouseId()));
        output.setClient(clientRepository.getReferenceById(outputDto.getClientId()));
        output.setCurrency(currencyRepository.getReferenceById(outputDto.getCurrencyId()));

        output.setFactureNumber(String.valueOf(outputRepository.sizeOfInput()));
        output.setCode(UniversalClass.forRandomCode());

        outputRepository.save(output);
        return new Result("Output added",true);
    }

    public Result edit(Integer id,OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) {
            return new Result("Output not found",false);
        }
        Output editingOutput = optionalOutput.get();
        editingOutput.setWarehouse(warehouseRepository.getReferenceById(outputDto.getWarehouseId()));
        editingOutput.setClient(clientRepository.getReferenceById(outputDto.getClientId()));
        editingOutput.setCurrency(currencyRepository.getReferenceById(outputDto.getCurrencyId()));

        Output savedOutput = outputRepository.save(editingOutput);
        return new Result("Output edited",true,savedOutput);
    }

    public Result delete(Integer id){
        try {
            outputRepository.deleteById(id);
            return new Result("Output deleted",true);
        }catch (Exception e){
            return new Result("Error in deleting",false);
        }
    }





}
