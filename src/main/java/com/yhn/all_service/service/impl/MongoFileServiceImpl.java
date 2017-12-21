package com.yhn.all_service.service.impl;
import com.yhn.all_service.dao.mongodb.FileRepository;
import com.yhn.all_service.entity.mongodb.File;
import com.yhn.all_service.service.MongoFileService;
import com.yhn.all_service.util.KeyUtil;
import com.yhn.all_service.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yhn on 2017/11/8.
 */
@Service
@Slf4j
public class MongoFileServiceImpl implements MongoFileService{
    @Autowired
    private FileRepository fileRepository;
    public File save(MultipartFile file) {
        String fileId = "";
        File mongoFile;
        try {
            File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes());
            f.setMd5(MD5Util.getMD5(file.getInputStream()) );
            fileId = KeyUtil.genUniqueKey();
            f.setId(fileId);
            mongoFile = fileRepository.save(f);
            log.info("mongoDB存图片情况报告！f={}",f);
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            log.error("【MongoDB上传文件错误】:fileId={}",fileId);
            return null;
        }
        return mongoFile;
    }
    public byte[] getFileById(String id) {
        File file = fileRepository.findOne(id);
        if (file != null) {
            return file.getContent();
        } else {
            log.error("mongoDB查询文件不存在！ id={}", id);
            return null;
        }
    }
}
