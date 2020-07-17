package com.tom.gmall.logger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tom
 * @create 2020-07-17 15:02
 */

@RestController
public class LoggerController {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping("/applog")  //#http模式下，发送的地址mock.url=http://192.168.1.11:8090/applog(windows地址)

    /*RequestBody两种传参方式：
     *   http请求：
     *       1）一种放在路径里，比如：http://192.168.1.11:8090/applog?name=xxx&age=xxxx,
     *       代码实现“(@RequestParam("namelog") String json)”，这种方式的问题可以看见访问路径。
     *       2）一种放在请求体里面：将json使用@RequestBody包装成一个请求体，使用post发送；取也需要用@RequestBody
     *       从请求体里面获取json数据，代码实现：“(@RequestBody String json)”，post方式隐藏了路径。
     * */

    public String applog(@RequestBody String json) {
        System.out.println(json);

        JSONObject jsonObject = JSON.parseObject(json);

        if(jsonObject.getString("start")!=null && jsonObject.getString("start").length()>0) {
            kafkaTemplate.send("GMALL_START", json);
        }else{
            kafkaTemplate.send("GMALL_EVENT",json);
        }





        return "success";
    }


}
