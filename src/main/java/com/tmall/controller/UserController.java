package com.tmall.controller;

import com.alibaba.fastjson.JSON;
import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.exception.IllegalPhoneException;
import com.tmall.exception.PasswordException;
import com.tmall.exception.PhoneNotNullException;
import com.tmall.exception.RechargeException;
import com.tmall.pojo.Password;
import com.tmall.pojo.PaymentBo;
import com.tmall.pojo.Shop;
import com.tmall.pojo.User;
import com.tmall.service.CommentService;
import com.tmall.service.PayService;
import com.tmall.service.UserService;
import com.tmall.util.CheckPhone;
import com.tmall.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/19 13:26
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PayService payService;

    //登录
    @PostMapping("/login")
    public ResponseData<?> login(@RequestBody User user) {
        User resultUser = userService.selectUserByUP(user);
        if (resultUser == null) {
            return ResponseDataUtils.buildSuccess("2", "用户或密码错误");
        } else if (0 == resultUser.getStatus()) {
            return ResponseDataUtils.buildSuccess("1", "该账号已被拉黑，请联系管理员解禁！");
        }
        return ResponseDataUtils.buildSuccess("0", "登录成功！", resultUser);
    }

    //获取用户的所有信息
    @GetMapping("/{userId}")
    public ResponseData<?> getUserById(@PathVariable("userId") BigInteger userId){

      User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseDataUtils.buildSuccess("1","未查询到此用户");
        }
        return ResponseDataUtils.buildSuccess("0", "获取用户信息成功",user);
    }

    // 用户修改个人信息
    @PutMapping("/{id}")
    public ResponseData<?> updateUserById(@RequestBody User user){
        CheckPhone.checkPhone(user.getTelephone());
        userService.updateUserById(user);
        return ResponseDataUtils.buildSuccess("0","个人信息修改成功");
    }

    //分页查询所有用户信息(包括商家) // 管理员后端
    @GetMapping
    public ResponseData<?> findUserList ( @RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "") String key,
                                         @RequestParam(defaultValue = "user")String flag){
        if (flag.equals("user")) {
            Page<User> users = userService.findUserList(pageNum,pageSize,key);
            return ResponseDataUtils.buildSuccess("0", "查询用户成功",users);
        }else {
            List<User> shopper = userService.findShopperList(pageNum,pageSize,key);
            Integer total = userService.countShopper(key);
            Page<User> page = new Page<>();
            page.setPageSize(pageSize).setPageNum(pageNum).setData(shopper).setTotal(total);
            return ResponseDataUtils.buildSuccess("0", "查询商家成功",page);
        }
    }

    /**
     * 用户密码修改功能
     * 具备:不可传空串.原密码验证.新密码再次校验
     * @param password
     * @return
     */
    @PostMapping({"/password"})
    public ResponseData<?> setPassword(@RequestBody Password password) {
        String inputPassword = password.getPassword();
        if (!StringUtils.hasLength(inputPassword)) {
            throw new PasswordException("修改内容不可为空!");
        } else {
            inputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());

            String newPassword = password.getNewPassword();
            String twicePassword = password.getTwicePassword();
            String oldPassword = this.userService.getOldPassword(password.getUserId());

            String newPMd5 = DigestUtils.md5DigestAsHex(newPassword.getBytes());
            if (!oldPassword.equals(inputPassword)) {
                throw new PasswordException("原密码不正确请重新输入");
            } else if (!StringUtils.hasLength(newPassword)) {
                throw new PasswordException("新密码不可为空");
            } else if (!newPassword.equals(twicePassword)) {
                throw new PasswordException("两次密码不一致请重新输入");
            } else if (oldPassword.equals(newPMd5)) {
                throw new PasswordException("不可与上一次密码相同");
            }else {
                this.userService.setPassword(password);
                return ResponseDataUtils.buildSuccess("0", "密码修改成功");
            }
        }
    }

    /**
     * 充值虚拟货币
     * 思路:查询到目前的余额,再添加
     * @param user
     * @return
     * 返回格式是text/html，这样前端页面就能直接显示支付宝返回的html片段
     */
    @PatchMapping(value = "/recharge",produces = {"application/json;charset=UTF-8"})
    public ResponseData<?> recharge(@RequestBody User user) throws Exception {
        BigInteger userId = user.getUserId();
        if (user.getMoney()== null || user.getMoney()==0 ) {
            throw new RechargeException("请输入充值金额!");
        }else if (user.getMoney()<0){
            throw new RechargeException("充值金额不可为负数!");
        }else{
        String bobo =  payService.pay(user);
            System.out.println("bobo = " + bobo);
            //获取当前账户余额com
            Double money1 =userService.getRecharge(userId);
            //充值后的金额
            Double newMoney= user.getMoney()+money1;
            user.setMoney(newMoney);
            userService.addRecharge(userId,newMoney);
            return ResponseDataUtils.buildSuccess("0","充值成功~",bobo);
        }
    }

    // 查询某用户是否购买了某商品
    @GetMapping("/check/{userId}/{productId}/{shopId}")
    public ResponseData<?> check(@PathVariable BigInteger userId,
                                 @PathVariable BigInteger productId,
                                 @PathVariable BigInteger shopId){
        //若flag=null则说明没有购买商品,不为null则购买了可以进行下一步(评论)
        boolean flag= userService.check(userId,productId,shopId);
        if (!flag) {
            return ResponseDataUtils.buildSuccess("1","未购买此商品不能评价哟~");
        }
        if (commentService.checkComment(productId, userId)) {
            return ResponseDataUtils.buildSuccess("1","同一商品只可评价一次!");
        }
        return ResponseDataUtils.buildSuccess("0","已购买此商品");
    }

    //更改用户权限状态
    @PatchMapping("/{userId}/{status}")
    public ResponseData<?> changeStatus(@PathVariable("userId") BigInteger userId,
                                        @PathVariable("status") BigInteger status){
        userService.updateUserStatus(userId,status);
        return ResponseDataUtils.buildSuccess("0", "用户状态更新成功");
    }

    //新增用户
    @PostMapping("/add")
    public ResponseData<?> addUser(@RequestBody User user){
        CheckPhone.checkPhone(user.getTelephone());
        //判断用户是否已存在
        String username = userService.findUserName(user.getUsername());
        if (!StringUtils.hasLength(username)) {
            userService.addUser(user);
            return ResponseDataUtils.buildSuccess("0", "新增用户成功");
        }else {
            return ResponseDataUtils.buildError("1","用户已存在,请重新输入");
        }
    }

    //用户注册
    @PostMapping("/register")
    public ResponseData<?> userRegister(@RequestBody User user) {
        // 检测用户名是否重复
        String username = userService.getCheckUsername(user.getUsername());
        if (user.getUsername().equals(username)) {
            return ResponseDataUtils.buildSuccess("1", "该用户名已被注册!");
        }
        userService.userRegister(user);
        return ResponseDataUtils.buildSuccess("0","用户注册成功!");
    }

    /**
     * 商家注册模块
     * 请求类型:post
     * 返回值类型:ResponseData
     * 请求参数:Shop对象
     */
    @Transactional
    @PostMapping("/shop/register")
    public ResponseData<?> register(@RequestBody Shop shop) {
        CheckPhone.checkPhone(shop.getTelephone());
        //检测下注册用户名是否重复
        String shopUsername = userService.getCheckUsername(shop.getUsername());
        //用户名重复禁止注册
        if (shop.getUsername().equals(shopUsername)) {
            return ResponseDataUtils.buildSuccess("1", "该用户名已被使用！");
        }
        userService.shopRegister(shop);
        return ResponseDataUtils.buildSuccess("0", "店铺注册成功,即将跳转登录");
    }

    /**
     * 更新账户信息
     */
    @PostMapping("/account")
    public ResponseData<?> updateAccountInfo(@RequestBody User user) {
        userService.updateAccountInfo(user);
        return ResponseDataUtils.buildSuccess("0", "更新信息成功");
    }

    //删除用户
    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUserById(@PathVariable("userId") BigInteger userId){
        userService.deleteById(userId);
        return ResponseDataUtils.buildSuccess("0", "用户删除成功");
    }

    /**
     * 管理员重置账户密码(包括管理员,商家,用户)
     */
    @PatchMapping("/reset/{userId}")
    public ResponseData<?> resetPassword(@PathVariable BigInteger userId) {
        User user = userService.getUserById(userId);
        userService.resetPassword(user);
        return ResponseDataUtils.buildSuccess("0", "重置密码成功!");
    }

    @GetMapping("/order_number/{orderNumber}/{shopId}")
    public User getUserByOrderNumber(@PathVariable("orderNumber") String orderNumber,
                                     @PathVariable("shopId") BigInteger shopId) {
        return userService.getUserByOrderNumber(orderNumber,shopId);
    }

    //为商家查询本店订单获取到shopId()
    @GetMapping("/shop_id/{userId}")
    public BigInteger getShopInfo(@PathVariable BigInteger userId) {
        return userService.getShopInfo(userId).getShopId();
    }
}
