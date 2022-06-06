package uz.bob.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bob.app_warehouse.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
