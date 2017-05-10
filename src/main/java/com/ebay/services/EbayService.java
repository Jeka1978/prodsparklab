package com.ebay.services;

import com.ebay.AutowiredBroadcast;
import com.ebay.UserProperties;
import com.ebay.conf.MainConfig;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.*;

/**
 * Created by Evegeny on 08/05/2017.
 */
@Service
public class EbayService {
    public static final String COUNTRY_CODE = "item_site_id";
    public static final String PRICE = "start_price_lstg_curncy";
    private static final String TOTAL_PRICE = "total_price";
    private static final String COUNTRY_NAME = "country_name";
    @Autowired
    private SQLContext sqlContext;

    @Autowired
    private DataFinalizer finalizer;

    @AutowiredBroadcast
    private Broadcast<UserProperties> userProperties;


    @Value("${pathToRead}")
    private String pathToRead;

    public void start(){
        DataFrame dataFrame = sqlContext.read().json(pathToRead).select(COUNTRY_CODE, PRICE);
        dataFrame.show();
        dataFrame = dataFrame.groupBy(COUNTRY_CODE).agg(round(sum(col(PRICE)),2).as(TOTAL_PRICE)).orderBy(col(TOTAL_PRICE).desc());
        dataFrame =  dataFrame.withColumn(COUNTRY_NAME, callUDF(CountryNameByCodeResolver.class.getName(),col(COUNTRY_CODE)));
        finalizer.finalize(dataFrame);
        ;


    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(MainConfig.class).getBean(EbayService.class).start();
    }


}















