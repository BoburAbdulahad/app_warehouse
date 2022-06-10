package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.app_warehouse.entity.OutputProduct;
import uz.bob.app_warehouse.payload.OutputProductDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.repository.OutputProductRepository;
import uz.bob.app_warehouse.repository.OutputRepository;
import uz.bob.app_warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public List<OutputProduct> getAll(){
        return outputProductRepository.findAll();
    }

    public OutputProduct getOneById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElseGet(OutputProduct::new);
    }

    public Result add(OutputProductDto outputProductDto){
        boolean b = outputProductRepository.existsByProduct_IdAndOutput_Id(outputProductDto.getProductId(), outputProductDto.getOutputId());
        if (b)
            return new Result("Its product already exist in output",false);
        if (!productRepository.existsById(outputProductDto.getProductId())) {
            return new Result("Product not found",false);
        }
        if (!outputRepository.existsById(outputProductDto.getOutputId())) {
            return new Result("Output not found",false);
        }
        OutputProduct outputProduct=new OutputProduct();

        outputProduct.setProduct(productRepository.getReferenceById(outputProductDto.getProductId()));
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(outputRepository.getReferenceById(outputProductDto.getOutputId()));

        outputProductRepository.save(outputProduct);
        return new Result("OutputProduct added",true);
    }

    public Result delete(Integer id){
        try {
            outputProductRepository.deleteById(id);
            return new Result("OutputProduct deleted",true);
        }catch (Exception e){
            return new Result("Error in deleting",false);
        }
    }

}
