package com.ebay.services;

import org.apache.spark.sql.DataFrame;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import static com.ebay.conf.ProfilesConst.PROD;

/**
 * Created by Evegeny on 10/05/2017.
 */
@Service
@Profile(PROD)
public class DataFinalizerImpl implements DataFinalizer {

    @Value("${pathToWrite}")
    private String pathToWrite;

    @Override
    public void finalize(DataFrame dataFrame) {
        dataFrame.coalesce(1).write().json(pathToWrite+"/"+System.currentTimeMillis());
    }

}
