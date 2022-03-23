package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.exception.IllegalPhoneException;
import com.tmall.exception.PasswordException;
import com.tmall.exception.PhoneNotNullException;
import com.tmall.pojo.Password;
import com.tmall.pojo.User;
import com.tmall.service.UserService;
import com.tmall.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
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

    //利用用户ID获取用户的所有信息
    @GetMapping("/{userId}")
    public ResponseData<?> getUserById(@PathVariable("userId") BigInteger userId){

      User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseDataUtils.buildSuccess("1","未查询到此用户");
        }
        return ResponseDataUtils.buildSuccess("0", "获取用户信息成功",user);
    }

    @PutMapping("/{id}")
    public ResponseData<?> updateUserById(@RequestBody User user){
        String tel = user.getTelephone();
        System.out.println(tel);
        if (!StringUtils.hasLength(tel)) {
            throw new PhoneNotNullException("手机号必填");
        }else if (!tel.matches("[1][0-9]{10}")) {
            throw new IllegalPhoneException("手机格式错误");
        }
        userService.updateUserById(user);
        return ResponseDataUtils.buildSuccess("0","个人信息修改成功");
    }

    //分页查询所有用户信息
    @GetMapping
    public ResponseData<?> findUserList( @RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "") String key){
        Page<User> users = userService.findUserList(pageNum,pageSize,key);
        return ResponseDataUtils.buildSuccess("0", "查询用户成功",users);
    }

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
            if (!oldPassword.equals(inputPassword)) {
                throw new PasswordException("原密码不正确请重新输入");
            } else if (!StringUtils.hasLength(newPassword)) {
                throw new PasswordException("新密码不可为空");
            } else if (!newPassword.equals(twicePassword)) {
                throw new PasswordException("两次密码不一致请重新输入");
            } else {
                this.userService.setPassword(password);
                return ResponseDataUtils.buildSuccess("0", "密码修改成功");
            }
        }
    }

}
