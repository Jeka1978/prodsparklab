package com.ebay.services;

import org.apache.spark.sql.DataFrame;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import static com.ebay.conf.ProfilesConst.DEV;

/**
 * Created by Evegeny on 10/05/2017.
 */
@Service
@Profile(DEV)
public class DataFinalizerMock implements DataFinalizer {
    @Override
    public void finalize(DataFrame dataFrame) {
        dataFrame.show();
    }
}
