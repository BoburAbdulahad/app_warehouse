package uz.bob.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.app_warehouse.entity.InputProduct;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {

    //for add
    boolean existsByProduct_IdAndInput_Id(Integer product_id, Integer input_id);

    //for edit
    boolean existsByProduct_IdAndInput_IdAndIdNot(Integer product_id, Integer input_id, Integer id);
}
