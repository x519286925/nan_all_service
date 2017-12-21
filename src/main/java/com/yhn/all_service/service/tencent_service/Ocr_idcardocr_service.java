package com.yhn.all_service.service.tencent_service;

import com.yhn.all_service.entity.mongodb.File;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yhn on 2017/12/5.
 */
@RestController
public interface Ocr_idcardocr_service {
     @PostMapping("/getID_Information")
     String getIdInformation(@RequestParam("url") String url)throws Exception;
}
