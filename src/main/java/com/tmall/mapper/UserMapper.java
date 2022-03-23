package com.tmall.mapper;

import com.tmall.pojo.Password;
import com.tmall.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/19 12:41
 */
public interface UserMapper {

    User selectUserByUP(User user);

    //通过用户Id显示查询数据
    User selectUserById(BigInteger userId);
    //修改用户信息
    void updateUserById(User user);

    List<User> findUserList(@Param("pageNum") Integer pageNum,
                            @Param("pageSize") Integer pageSize,
                            @Param("key") String key);

    Integer countUser(String key);

    List<User> findShopperList(@Param("startNum") Integer startNum,
                               @Param("pageSize") Integer pageSize,
                               @Param("key") String key);

    Integer countShopper(String key);

    void setPassword(Password password);

    String getOldPassword(BigInteger userId);
}
