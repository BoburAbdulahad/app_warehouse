package uz.bob.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.app_warehouse.entity.Product;
import uz.bob.app_warehouse.service.ProductService;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    boolean existsByNameAndCategoryId(String name, Integer category_id);

}
