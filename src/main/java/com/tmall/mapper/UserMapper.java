package com.tmall.mapper;

import com.tmall.pojo.Password;
import com.tmall.pojo.Shop;
import com.tmall.pojo.User;
import org.apache.ibatis.annotations.Param;

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
    //充值
    void addRecharge(@Param("userId") BigInteger userId,@Param("money") double money);
    //查询当前余额
    Double getRecharge(@Param("userId") BigInteger userId);
    //查询购买记录
    Integer check(@Param("userId") BigInteger userId,
                  @Param("shopId") BigInteger shopId,
                  @Param("orderNumber") String orderNumber);

    //更新用户权限状态
    void updateStatus(@Param("userId") BigInteger userId,@Param("status") BigInteger status);

    //新增用户
    void addUser(User user);


    // 进行用户名校验
    String getCheckUsername(String username);

    //商户注册
    void shopRegister(Shop shop);

    // 用户注册
    void userRegister(User user);

    // 更新账户信息
    void updateAccountInfo(User user);

    //删除用户
    void deleteById(BigInteger userId);

    String findUserName(String username);

    User getUserByOrderNumber(@Param("orderNumber") String orderNumber,
                              @Param("shopId") BigInteger shopId);

    // 获取店铺信息
    Shop getShopInfo(BigInteger userId);

    // 重置账户密码
    void resetPassword(User user);

    // 支付订单后,进行扣款
    void setRecharge(@Param("userId") BigInteger userId,
                     @Param("money") Double money);

    // 将收入加入到每个商家的收入中
    void addShopIncome(@Param("orderAmount") Double orderAmount,
                       @Param("shopId") Integer shopId);
}
