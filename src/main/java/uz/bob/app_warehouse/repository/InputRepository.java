package uz.bob.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.bob.app_warehouse.entity.Input;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface InputRepository extends JpaRepository<Input,Integer> {

    //for add
    boolean existsByDateAndSupplierId(Timestamp date, Integer supplier_id);

    //for edit
//    @Query(value = "select * from INPUT where supplier_id=supplierId and id<>Id",nativeQuery = true)
//    boolean existsByDateAndSupplierIdAndIdNotNative(Integer supplierId, Integer Id);
//    boolean existsByDateAndSupplierIdAndIdNot(Integer supplier_id, Integer id);

//    @Query(value = "select date-str from INPUT where supplier_id=supplierId",nativeQuery = true)
//    List<LocalTime> intervalTimes(String str, Integer supplierId);



    //for facture number
    @Query(value = "select count(*) from input",nativeQuery = true)
    int sizeOfInput();


}
