package uz.bob.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.app_warehouse.entity.AttachmentContent;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {

    void deleteByAttachment_Id(Integer attachment_id);
}
