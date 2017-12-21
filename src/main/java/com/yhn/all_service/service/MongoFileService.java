package com.yhn.all_service.service;
import com.yhn.all_service.entity.mongodb.File;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yhn on 2017/11/8.
 */
@RestController
public interface MongoFileService {
    @PostMapping("/File_save")
    File save(@RequestPart(value = "file") MultipartFile file);
    @PostMapping("/File_getFileById")
    byte[] getFileById(@RequestParam("id") String id);
}
