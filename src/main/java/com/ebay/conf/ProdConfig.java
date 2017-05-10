package com.ebay.conf;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import static com.ebay.conf.ProfilesConst.PROD;

/**
 * Created by Evegeny on 04/05/2017.
 */
@Configuration
@Profile(PROD)
@PropertySource("classpath:prod.properties")
public class ProdConfig {
    @Bean
    public SparkConf sparkConf() {
        return new SparkConf().setAppName("Words");
    }
}
