package appwarehouse.service.imlp;

import appwarehouse.entity.Attachment;
import appwarehouse.entity.AttachmentContent;
import appwarehouse.helpers.MapstructMapper;
import appwarehouse.repository.AttachmentContentRepo;
import appwarehouse.repository.AttachmentRepo;
import appwarehouse.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepo attachmentRepo;
    private final AttachmentContentRepo attachmentContentRepo;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepo attachmentRepo, AttachmentContentRepo attachmentContentRepo, MapstructMapper mapstructMapper) {
        this.attachmentRepo = attachmentRepo;
        this.attachmentContentRepo = attachmentContentRepo;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public Attachment upload(MultipartFile file) throws IOException {

        // Creating Attachment object
        Attachment attachment=new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());

        // Saving Attachment object to database
        Attachment savedAttachment= attachmentRepo.save(attachment);

        // Creating Attachment content object
        AttachmentContent attachmentContent=new AttachmentContent();
        attachmentContent.setData(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);

        // Saving Attachment content object to database
        attachmentContentRepo.save(attachmentContent);

        // Creating Attachment response object
//        AttachmentDto dto = new AttachmentDto();
//        dto.setId(savedAttachment.getId());
//        dto.setName(savedAttachment.getName());
//        dto.setSize(savedAttachment.getSize());
//        dto.setContentType(savedAttachment.getContentType());

        // Yoki mapstructMapperdan foydalanamiz
        //return mapstructMapper.toAttachmentDto(savedAttachment);
        return savedAttachment;
    }
}
