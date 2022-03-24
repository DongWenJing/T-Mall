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
    //分页显示用户信息
    List<User> findUserList(@Param("pageNum") Integer pageNum,
                            @Param("pageSize") Integer pageSize,
                            @Param("key") String key);

    Integer countUser(String key);

    // 显示所有商家(分页) 模糊查询
    List<User> findShopperList(@Param("startNum") Integer startNum,
                               @Param("pageSize") Integer pageSize,
                               @Param("key") String key);

    // 统计商家数量
    Integer countShopper(String key);

    //修改密码
    void setPassword(Password password);
    //获取原始密码
    String getOldPassword(BigInteger userId);

    void addRecharge(@Param("userId") BigInteger userId,@Param("money") double money);

    Double getRecharge(@Param("userId") BigInteger userId,@Param("money") double money);

    //更新用户权限状态
    void updateStatus(BigInteger userId, BigInteger status);

}
