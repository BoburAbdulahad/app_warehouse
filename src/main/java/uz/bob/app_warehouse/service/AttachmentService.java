package uz.bob.app_warehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.bob.app_warehouse.entity.Attachment;
import uz.bob.app_warehouse.entity.AttachmentContent;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.repository.AttachmentContentRepository;
import uz.bob.app_warehouse.repository.AttachmentRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public List<Attachment> get(){
        return attachmentRepository.findAll();
    }

    @SneakyThrows
    public Result add(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if(file==null)
            return new Result("File is empty!",false);
        Attachment attachment=new Attachment(
                null,
                file.getOriginalFilename(),
                file.getSize(),
                file.getContentType()
        );
        Attachment savedAttachment = attachmentRepository.save(attachment);
        AttachmentContent attachmentContent=new AttachmentContent(
                null,
                file.getBytes(),
                savedAttachment
        );
      attachmentContentRepository.save(attachmentContent);
      return new Result("File saved",true,"Id: "+savedAttachment.getId());
    }

    public Result delete(Integer id){
        if (!attachmentRepository.findById(id).isPresent())
            return new Result("Attachment not found",false);
        Attachment attachment = attachmentRepository.getReferenceById(id);
        attachmentContentRepository.deleteByAttachment_Id(attachment.getId());
        attachmentRepository.deleteById(id);
        return new Result("Attachment deleted",true);
    }

    @SneakyThrows
    public Result edit(Integer id, MultipartHttpServletRequest request){// TODO: 6/2/2022 check this method its no correct
        if (!attachmentRepository.findById(id).isPresent()) {
            return new Result("Attachment not found",false);
        }
        Attachment attachment = attachmentRepository.getReferenceById(id);
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        if (file==null)
            return new Result("File is null",false);
        attachment.setName(file.getName());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(attachment.getId());
        if (!optionalAttachmentContent.isPresent()) {
            return new Result("AttachmentContent not found",false);
        }
        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("File successfully edited",true,savedAttachment);
    }

}
