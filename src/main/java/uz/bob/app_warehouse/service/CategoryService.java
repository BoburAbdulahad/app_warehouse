package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.app_warehouse.entity.Category;
import uz.bob.app_warehouse.payload.CategoryDto;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    //get all category
    public List<Category> get(){
        return categoryRepository.findAll();
    }
    //add Category
    public Result add(CategoryDto categoryDto) {// TODO: 6/1/2022 add qilishda jpa query yozish kk parentCategoryId va name boyicha
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent()) {
                return new Result("This type category not exist", false);
            }
            category.setParentCategory(optionalParentCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category added",true);
    }

    //edit category
    public Result edit(Integer id,CategoryDto categoryDto){// TODO: 6/1/2022 => hotel project dagi holat boyicha tekshir editni
        if (!categoryRepository.existsById(id))
            return new Result("Category not found",false);
        Category editingCategory = categoryRepository.getReferenceById(id);
        editingCategory.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId())
    }



}
