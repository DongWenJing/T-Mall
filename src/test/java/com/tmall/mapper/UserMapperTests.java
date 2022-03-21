package com.tmall.mapper;

import com.tmall.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

/**
 * @author R.Yu
 * @date 2022/3/19 12:46
 */
@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testSelectUserByUP() {
        User user = new User();
        user.setUsername("user").setPassword("abcd1234");
        User user2 = userMapper.selectUserByUP(user);
        System.out.println(user2);
    }
//   @Test
//    public void getUserById(BigInteger userId) {
//        User user = userMapper.selectUserById(1);
//        System.out.println(user);
//
//    }
}
