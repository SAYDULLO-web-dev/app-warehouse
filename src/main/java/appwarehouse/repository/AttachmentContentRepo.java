package appwarehouse.repository;

import appwarehouse.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentContentRepo extends JpaRepository<AttachmentContent, Long> {
}
