package com.yhn.all_service.service.tencent_service.tencent_serviceImpl;

import com.yhn.all_service.service.tencent_service.Ocr_idcardocr_service;
import com.yhn.all_service.sign.TencentAISign;
import com.yhn.all_service.sign.TencentAISignSort;
import com.yhn.all_service.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yhn on 2017/12/5.
 */
@Service
@Slf4j
public class ocr_idcardocr_serviceImpl implements Ocr_idcardocr_service {
    @Override
    public String getIdInformation(String url) throws Exception {
        System.out.println(url);
        //时间戳
        String time_stamp = System.currentTimeMillis()/1000+"";
        //图片的二进制数组数据
//		byte [] imageData = FileUtil.readFileByBytes("E:/yhn.jpg");
        byte [] imageData = UrlMethodUtil.url2byte(url);
        //图片的base64编码数据
        String img64 = Base64Util.encode(imageData);
        //随机字符串
        String nonce_str = TencentAISign.getRandomString(10);
        Map<String,String> person_Id_body = new HashMap<>();
        person_Id_body.put("app_id", String.valueOf(TencentAPI.APP_ID_AI));
        person_Id_body.put("time_stamp",time_stamp);
        person_Id_body.put("nonce_str", nonce_str);
        person_Id_body.put("image", img64);
        person_Id_body.put("card_type", "0");
        String sign = TencentAISignSort.getSignature(person_Id_body);
        person_Id_body.put("sign", sign);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        HttpResponse responseBD = HttpsUtil4Tencent.doPostTencentAI(TencentAPI.PERSON_ID, headers, person_Id_body);
        String json = EntityUtils.toString(responseBD.getEntity());
        return json;
    }
}
