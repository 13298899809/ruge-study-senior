package com.ruge.es5;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.io.IOException;
import java.util.Collections;

public class RestClientTest55 {
    public static void main(String[] args) {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "Arcfox20200817!@#"));
        // 单击所创建的Elasticsearch实例ID，在基本信息页面获取公网地址，即为HOST。
        RestClient restClient = RestClient.builder(new HttpHost("127.0.0.1", 9200))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)).build();
        System.out.println(restClient);

//        try {
//            //field_01、field_02为字段名，value_01、value_02为对应的值。
//            HttpEntity entity = new NStringEntity("{\n\"field_01\" : \"value_01\"\n,\n\"field_02\" : \"value_02\"\n}", ContentType.APPLICATION_JSON);
//            //index_name为索引名称；type_name为类型名称；doc_id为文档的id。
//            Response indexResponse = restClient.performRequest(
//                    "PUT",
//                    "/index_name/type_name/doc_id",
//                    Collections.<String, String>emptyMap(),
//                    entity);
//            //index_name为索引名称；type_name为类型名称；doc_id为文档的id。与以上创建索引的名称和id相同。
//            Response response = restClient.performRequest("GET", "/index_name/type_name/doc_id",
//                    Collections.singletonMap("pretty", "true"));
//            System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}