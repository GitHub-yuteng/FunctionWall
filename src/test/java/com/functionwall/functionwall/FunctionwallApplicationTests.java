package com.functionwall.functionwall;

import com.functionwall.service.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FunctionwallApplicationTests {


    @Autowired
    TopicService topicService;

    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
            topicService.save("熊鹏是渣男", "表白墙", "熊鹏是渣男", "39");
        }
        for (int i = 0; i < 100; i++) {
            topicService.save("熊鹏是渣男", "吐槽墙", "熊鹏是渣男", "39");
        }
    }
}
