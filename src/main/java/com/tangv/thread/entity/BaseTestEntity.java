package com.tangv.thread.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author: tangwei
 * @Date: 2021/6/26 10:36
 * @Description:
 */
@SpringBootTest
@WebAppConfiguration
//@RunWith(SpringRunner.class)
@Slf4j
public class BaseTestEntity {

    @Before
    public void init() {
        log.debug("=====开始测试=====");
    }
}