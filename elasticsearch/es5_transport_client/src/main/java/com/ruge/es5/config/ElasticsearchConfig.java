package com.ruge.es5.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/8/20 21:20
 */
@Configuration
public class ElasticsearchConfig {
    @Bean
    public TransportClient transportClient() {
        TransportClient transportClient = null;
        try {
            transportClient = new PreBuiltXPackTransportClient(Settings.builder()
                    .put("cluster.name", "es-cn-oew1sdxak001z9h7w") //替换为对应阿里云ES实例的ID
                    .put("xpack.security.user", "elastic:Arcfox20200817!@#") //阿里云ES实例的用户名、密码
                    .put("client.transport.sniff", false) //设置sniff为false
                    .build())
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));//指定域名及端口，替换为对应阿里云ES实例的域名
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transportClient;
    }
}
