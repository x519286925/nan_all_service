package com.yhn.all_service.dao.mongodb;
import com.yhn.all_service.entity.mongodb.File;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * Created by yhn on 2017/11/8.
 */
public interface FileRepository extends MongoRepository<File,String> {

}
