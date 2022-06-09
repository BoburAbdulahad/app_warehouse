package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.app_warehouse.entity.Input;
import uz.bob.app_warehouse.entity.InputProduct;
import uz.bob.app_warehouse.entity.Product;
import uz.bob.app_warehouse.payload.InputProductDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.repository.InputProductRepository;
import uz.bob.app_warehouse.repository.InputRepository;
import uz.bob.app_warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InputRepository inputRepository;

    public List<InputProduct> getAll(){
        return inputProductRepository.findAll();
    }

    public InputProduct getOneById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElseGet(InputProduct::new);
    }

    public Result add(InputProductDto inputProductDto){
        boolean b = inputProductRepository.existsByProduct_IdAndInput_Id(inputProductDto.getProductId(), inputProductDto.getInputId());
        if (b)
            return new Result("This product such as at the input already exist",false);
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Product not found",false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()) {
            return new Result("Input not found",false);
        }

        InputProduct inputProduct=new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(optionalInput.get());

        inputProductRepository.save(inputProduct);
        return new Result("InputProduct added",true);
    }

    public Result edit(Integer id,InputProductDto inputProductDto){
        boolean b = inputProductRepository.existsByProduct_IdAndInput_IdAndIdNot(inputProductDto.getProductId(), inputProductDto.getInputId(), id);
        if (b)
            return new Result("The other id have this product and this input",false);
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new Result("InputProduct not found",false);
        }
        if (!productRepository.existsById(inputProductDto.getProductId())) {
            return new Result("Product not found",false);
        }
        if (!inputRepository.existsById(inputProductDto.getProductId())) {
            return new Result("Input not found",false);
        }
        InputProduct editingProduct = optionalInputProduct.get();

        editingProduct.setProduct(productRepository.getReferenceById(inputProductDto.getProductId()));
        editingProduct.setAmount(inputProductDto.getAmount());
        editingProduct.setPrice(inputProductDto.getPrice());
        editingProduct.setExpireDate(inputProductDto.getExpireDate());
        editingProduct.setInput(inputRepository.getReferenceById(inputProductDto.getInputId()));

        InputProduct savedInputProduct = inputProductRepository.save(editingProduct);
        return new Result("InputProduct edited",true,savedInputProduct);
    }

    public Result delete(Integer id){
        try {
            inputProductRepository.deleteById(id);
            return new Result("InputProduct deleted",true);
        }catch (Exception e){
            return new Result("Error in deleting",false);
        }
    }


}
