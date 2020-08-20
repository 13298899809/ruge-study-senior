package com.ruge.es5.controller;

import lombok.SneakyThrows;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/8/20 22:18
 */
@RestController

public class EsController {
    @Resource
    RestClient restClient;


    @PostMapping("/add")

//    public ResponseEntity<String> add() throws IOException {
//
//        // 构造HTTP请求，第一个参数是请求方法，第二个参数是服务器的端点，host默认是http://localhost:9200，
//
//        // endpoint直接指定为index/type的形式
//
//        Request request = new Request("POST", new StringBuilder("/test_db/book/").
//                append(book.getId()).toString());
//
//        // 设置其他一些参数比如美化json
//
//        request.addParameter("pretty", "true");
//
//        String bookString=JSON.toJSONString(book);
//
//        System.out.println(bookString);
//
//        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON
//
//        request.setEntity(new NStringEntity(bookString, ContentType.APPLICATION_JSON));
//
//        // 发送HTTP请求
//
//        Response response = client.performRequest(request);
//
//
//
//        // 获取响应体, id: AWXvzZYWXWr3RnGSLyhH
//
//        String responseBody = EntityUtils.toString(response.getEntity());
//
//        return new ResponseEntity<>(responseBody, HttpStatus.OK);
//
//    }


    @SneakyThrows
    @GetMapping("rest1")
    public String test1() {
        Map<String, String> params = new HashMap<>();
        params.put("scope","9");
        Response response = restClient.performRequest("POST", "/demo1/demo1/", params);
        String string = EntityUtils.toString(response.getEntity());
        return string;
    }
}
