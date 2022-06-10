package uz.bob.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.app_warehouse.entity.OutputProduct;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {

    //for add
    boolean existsByProduct_IdAndOutput_Id(Integer product_id, Integer output_id);

    //for edit
    boolean existsByProduct_IdAndOutput_IdAndIdNot(Integer product_id, Integer output_id, Integer id);
}
