package com.ruge.es6.controller;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/8/20 22:58
 */
@RestController
public class Es6Controller {
    @Resource
    private RestHighLevelClient client;

    @GetMapping("createIndex")
    public String createIndex() throws IOException {
        //创建名称为blog2的索
        CreateIndexRequest request = new CreateIndexRequest("demo2");
        //设置映射 doc type名称
        request.mapping("doc", " {\n" +
                " \t\"properties\": {\n" +
                "           \"name\": {\n" +
                "              \"type\": \"text\",\n" +
                "              \"analyzer\":\"ik_max_word\",\n" +
                "              \"search_analyzer\":\"ik_smart\"\n" +
                "           },\n" +
                "           \"description\": {\n" +
                "              \"type\": \"text\",\n" +
                "              \"analyzer\":\"ik_max_word\",\n" +
                "              \"search_analyzer\":\"ik_smart\"\n" +
                "           },\n" +
                "           \"studymodel\": {\n" +
                "              \"type\": \"keyword\"\n" +
                "           },\n" +
                "           \"price\": {\n" +
                "              \"type\": \"float\"\n" +
                "           }\n" +
                "        }\n" +
                "}", XContentType.JSON);


        CreateIndexResponse createIndexResponse = client.indices().create(request);
        return createIndexResponse.index();
    }

    @GetMapping("insert")
    public Map<String, Object> insert() throws IOException {
        String indexName = "demo2";
        String docType = "demo2";
        Map<String, Object> object = new HashMap<>();
        object.put("id", UUID.randomUUID().toString());
        object.put("name", "张三");
        IndexRequest indexRequest = new IndexRequest(indexName, docType).id(UUID.randomUUID().toString());
        // source方法里面需要填写对应数据的类型，默认数据类型为json
        indexRequest.source(object);
        IndexResponse index = client.index(indexRequest);
        DocWriteResponse.Result result = index.getResult();
        object.put("result", result);
        return object;
    }

    @GetMapping("delete")
    public String delete() throws IOException {
        Map<String, Object> object = new HashMap<>();
        //删除索引请求对象
        DeleteRequest deleteIndexRequest = new DeleteRequest("demo2");
        //删除索引
        DeleteResponse delete = client.delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return delete.toString();
    }
}
