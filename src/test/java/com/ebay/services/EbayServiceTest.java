package com.ebay.services;

import com.ebay.conf.MainConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ebay.conf.ProfilesConst.DEV;
import static org.junit.Assert.*;

/**
 * Created by Evegeny on 10/05/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MainConfig.class)
@ActiveProfiles(DEV)
public class EbayServiceTest {
    @Autowired
    private EbayService ebayService;

    @Test
    public void start() throws Exception {
        ebayService.start();
    }

}