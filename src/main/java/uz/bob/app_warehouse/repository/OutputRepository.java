package uz.bob.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.bob.app_warehouse.entity.Output;

@Repository
public interface OutputRepository extends JpaRepository<Output,Integer> {



    //for facture number
    @Query(value = "select count(*) from output",nativeQuery = true)
    int sizeOfInput();

}
