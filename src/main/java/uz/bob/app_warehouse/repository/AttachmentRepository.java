package uz.bob.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.app_warehouse.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
