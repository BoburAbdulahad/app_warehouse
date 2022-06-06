package uz.bob.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.app_warehouse.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
