package com.ebay.services;

import org.apache.spark.sql.DataFrame;

/**
 * Created by Evegeny on 10/05/2017.
 */
public interface DataFinalizer {
    void finalize(DataFrame dataFrame);
}
