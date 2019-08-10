package com.example.elasticsearchex;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;


@Configuration
public class ElasticsearchConfig {
  private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchConfig.class);

  @Value("${elasticsearch.ip}")
  private String hostName;

  @Value("${elasticsearch.port}")
  private String port;

  @Value("${elasticsearch.cluster.name}")
  private String clusterName;

  @Value("${elasticsearch.pool}")
  private String poolSize;

  /**
   * Bean name default
   * @return
   */
  @Bean(name = "transportClient")
  public TransportClient transportClient(){
    LOGGER.info("Elasticsearch初始化。。");
    TransportClient transportClient = null;
    try {
      Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff",false)//增加嗅探机制，找到ES集群.单机要设为false不然找不到。。https://segmentfault.com/q/1010000016691258
                .put("thread_pool.search.size", Integer.parseInt(poolSize))
                .build();
      transportClient = new PreBuiltTransportClient(settings);
      TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hostName),Integer.valueOf(port));
      transportClient.addTransportAddress(transportAddress);
    }catch (Exception e){
      LOGGER.error("elasticsearch TransportClient create error!", e);
    }
    return transportClient;
  }
}
