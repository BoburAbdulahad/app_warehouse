package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.bob.app_warehouse.entity.Attachment;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.AttachmentService;

import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping
    public Result add(MultipartHttpServletRequest request){
        Result result = attachmentService.add(request);
        return result;
    }

    @GetMapping
    public List<Attachment> get(){
        List<Attachment> attachments = attachmentService.get();
        return attachments;
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        Result result = attachmentService.delete(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody MultipartHttpServletRequest request){
        Result result = attachmentService.edit(id,request);
        return result;
    }

}
