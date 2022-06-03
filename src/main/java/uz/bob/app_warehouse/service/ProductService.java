package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import uz.bob.app_warehouse.entity.Attachment;
import uz.bob.app_warehouse.entity.Category;
import uz.bob.app_warehouse.entity.Measurement;
import uz.bob.app_warehouse.entity.Product;
import uz.bob.app_warehouse.payload.ProductDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.repository.AttachmentRepository;
import uz.bob.app_warehouse.repository.CategoryRepository;
import uz.bob.app_warehouse.repository.MeasurementRepository;
import uz.bob.app_warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;



    public List<Product> get(){
        return productRepository.findAll();
    }

    public Result add(ProductDto productDto){
        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists)
            return new Result("This type product already exist in this category",false);
        Product product=new Product();
        product.setName(productDto.getName());
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("Category not found",false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()) {
            return new Result("Photo not found",false);
        }
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()) {
            return new Result("Measurement not found",false);
        }
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setCode(forProductCode());
        productRepository.save(product);
        return new Result("Product added",true);
    }

    public Result delete(Integer id){
        if (!productRepository.existsById(id)) {
            return new Result("Product not found",false);
        }
        productRepository.deleteById(id);
        return new Result("Product deleted",true);
    }

    public Result edit(Integer id,ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new Result("Product not found",false);
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setCategory(categoryRepository.findById(productDto.getCategoryId()).get());
        product.setPhoto(attachmentRepository.getReferenceById(productDto.getPhotoId()));
        product.setMeasurement(measurementRepository.getReferenceById(productDto.getMeasurementId()));
//        product.setCode(forProductCode());
        return null;
    }


    public String forProductCode(){
        double random = Math.random()*10000;
        String value = String.valueOf(random);
        String[] split = value.split("\\.");
        return split[0];
    }

}
