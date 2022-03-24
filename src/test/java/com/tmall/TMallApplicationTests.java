package com.tmall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class TMallApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }

    @Test
    void testMd5Y() {
        String s = UUID.randomUUID().toString();
        System.out.println(s); // fb27b071-f6c5-4da9-9417-44af165634ec
        System.out.println(DigestUtils.md5DigestAsHex(("1234"+ s).getBytes())); // a7c0f498458a2051262e3f1a9fc0239a
    }
}
