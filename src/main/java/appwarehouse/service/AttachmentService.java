package appwarehouse.service;

import appwarehouse.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AttachmentService {

    Attachment upload(MultipartFile file) throws IOException;
}
