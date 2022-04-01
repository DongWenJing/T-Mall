package com.tmall.service.impl;

import com.tmall.mapper.OrderMapper;
import com.tmall.mapper.ProductMapper;
import com.tmall.mapper.ShopMapper;
import com.tmall.mapper.UserMapper;
import com.tmall.pojo.Order;
import com.tmall.pojo.Password;
import com.tmall.pojo.Shop;
import com.tmall.pojo.User;
import com.tmall.service.UserService;
import com.tmall.util.CheckPhone;
import com.tmall.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;


import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author R.Yu
 * @date 2022/3/19 13:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public User selectUserByUP(User user) {
        String password = user.getPassword();
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        User resultUser = userMapper.selectUserByUP(user);
        if (resultUser == null) {
            return null;
        }
        //获取shopId 通过身份验证是否是shop,是则通过userId查找到shopId
        if (resultUser.getRole().equals("shop")) {
            BigInteger shopId = shopMapper.getShopId(resultUser.getUserId());
            resultUser.setShopId(shopId);
        }
        return resultUser;
    }

    /**
     * 根据登录用户的Id获取展示个人信息
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(BigInteger userId) {
        User user = userMapper.selectUserById(userId);
        return user;
    }

    /**
     * 修改个人信息
     *
     * @param user
     */
    @Override
    public void updateUserById(User user) {
        userMapper.updateUserById(user);
    }

    /**
     * 分页查询所有用户信息
     *
     * @param pageNum
     * @param pageSize
     * @param key
     * @return
     */
    @Override
    public Page<User> findUserList(Integer pageNum, Integer pageSize, String key) {
        pageNum = (pageNum - 1) * pageSize;
        //获取查询到的数据
        List<User> users = userMapper.findUserList(pageNum, pageSize, key);
        //总记录条数
        Integer total = userMapper.countUser(key);
        //封装查询到的数据
        Page<User> page = new Page();
        page.setTotal(total).setData(users).setPageSize(pageSize).setPageNum(pageNum);
        return page;
    }

    /**
     * 获取数据库中原始密码
     *
     * @param userId
     * @return
     */
    public String getOldPassword(BigInteger userId) {
        String oldPassword = this.userMapper.getOldPassword(userId);
        return oldPassword;
    }

    /**
     * 充值虚拟货币
     *
     * @param userId
     * @param money
     */

    @Override
    public void addRecharge(BigInteger userId, double money) {
        userMapper.addRecharge(userId, money);
    }

    /**
     * 获取数据库当前余额
     *
     * @param userId
     * @param money
     * @return
     */
    @Override
    public Double getRecharge(BigInteger userId, double money) {
        return userMapper.getRecharge(userId, money);
    }

    /**
     * 更新用户权限状态
     *
     * @param userId
     * @param status
     */
    @Override
    @Transactional
    public void updateUserStatus(BigInteger userId, BigInteger status) {
        userMapper.updateStatus(userId, status);
    }

    /**
     * 新增用户
     *
     * @param user
     */
    @Override
    @Transactional
    public void addUser(User user) {
        String newPassword = DigestUtils.md5DigestAsHex(("1234").getBytes());
        user.setPassword(newPassword);
        userMapper.addUser(user);
    }

    @Override
    public String findUserName(String username) {
        return userMapper.findUserName(username);
    }

    @Override
    public User getUserByOrderNumber(String orderNumber, BigInteger shopId) {
        return userMapper.getUserByOrderNumber(orderNumber, shopId);
    }

    //获取shopId
    @Override
    public Shop getShopInfo(BigInteger userId) {
        return userMapper.getShopInfo(userId);
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @Override
    @Transactional
    public void deleteById(BigInteger userId) {
        userMapper.deleteById(userId);
    }

    /**
     * 重置账户密码
     *
     * @param user
     */
    @Override
    public void resetPassword(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex("1234".getBytes()));
        userMapper.resetPassword(user);
    }


    /**
     * 更新账户信息
     *
     * @param user
     */
    @Override
    public void updateAccountInfo(User user) {
        // 更新user表中的数据
        userMapper.updateAccountInfo(user);
        CheckPhone.checkPhone(user.getTelephone());
        if (user.getShopName() != null) {
            Shop shop = shopMapper.getShopByUserId(user.getUserId());
            shop.setShopName(user.getShopName());
            shopMapper.updateShopName(shop);
        }
    }


    /**
     * 获取是否有购买记录 // TODO
     * @param userId
     * @param productId
     * @param shopId
     * @return
     */
    @Override
    public boolean check(BigInteger userId, BigInteger productId, BigInteger shopId) {
        Set<BigInteger> productIdSet = new HashSet<>();
        // 获取该用户在该店的总订单号(已完成的订单)
        List<String> orderNumbersList = orderMapper.getOrderNumbersByUS(userId,shopId);
        for (String orderNumber : orderNumbersList) {
            // 获取该订单对应的总订单号
            String orderNumberAll = productMapper.findOrderNumberAll(orderNumber);
            // 查询已购买的商品
            BigInteger getProductId = orderMapper.getProductIdByON(orderNumberAll);
            productIdSet.add(getProductId);
        }
        System.out.println("productIdSet = " + productIdSet);
        return productIdSet.contains(productId);
        /*// 先获取该用户的所有订单
        List<Order> allOrder = orderMapper.findOrderById(userId);
        // 遍历所有订单
        for (Order order : allOrder) {
            // 已经取消
            if (order.getOrderStatus() == 2)
                break;
            // 拿到子订单
            String[] oN = orderMapper.getOrderNumbers(order.getOrderNumberAll()).split(",");
            if (oN.length == 1) {
                // 说明只有一个子订单
                String orderNumber = oN[0];
                Integer status = userMapper.check(userId,shopId,orderNumber);
                if (status == 3)
                    return true;
            }
            for (String orderNumber : oN) {
                Integer status = userMapper.check(userId, shopId, orderNumber);
                if (status == 3) return true;
            }
        }
        return false;*/
    }

    /**
     * 修改用户密码,加密后存储到数据库
     *
     * @param password
     */
    public void setPassword(Password password) {
        String newPassword = password.getNewPassword();
        newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        password.setNewPassword(newPassword);
        this.userMapper.setPassword(password);
    }

    @Override
    public List<User> findShopperList(Integer pageNum, Integer pageSize, String key) {
        int startNum = (pageNum - 1) * pageSize;
        return userMapper.findShopperList(startNum, pageSize, key);
    }

    @Override
    public Integer countShopper(String key) {
        return userMapper.countShopper(key);
    }


    //获取验证商家注册时所需的username
    @Override
    public String getCheckUsername(String username) {
        return userMapper.getCheckUsername(username);
    }

    //商家注册
    @Override
    @Transactional
    public void shopRegister(Shop shop) {
        shop.setPassword(DigestUtils
                .md5DigestAsHex(shop.getPassword().getBytes()));
        userMapper.shopRegister(shop);
    }

    // 用户注册
    @Override
    @Transactional
    public void userRegister(User user) {
        user.setPassword(DigestUtils.
                md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.userRegister(user);
    }

}
