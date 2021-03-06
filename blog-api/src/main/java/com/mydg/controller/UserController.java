package com.mydg.controller;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.mydg.common.annotation.LogAnnotation;
import com.mydg.common.constant.Base;
import com.mydg.common.constant.ResultCode;
import com.mydg.common.result.Result;
import com.mydg.common.util.UserUtils;
import com.mydg.entity.User;
import com.mydg.service.UserService;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @LogAnnotation(module = "用户", operation = "获取所有用户")
    @RequiresRoles(Base.ROLE_ADMIN)
    public Result listUsers() {
        List<User> users = userService.findAll();

        return Result.success(users);
    }

    @GetMapping("/guid/{account}")
    @LogAnnotation(module = "用户", operation = "根据用户名获取ID")
    // @RequiresRoles(Base.ROLE_ADMIN)
    public Result getIdByAccount(@PathVariable("account") String account) {

        Result r = new Result();
        if (null == account) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        Long uid = userService.getUserByAccount(account).getId();

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(uid);
        return r;
    }

    @GetMapping("/guser/{account}")
    @LogAnnotation(module = "用户", operation = "根据用户名获取用户")
    // @RequiresRoles(Base.ROLE_ADMIN)
    public Result getUserByAccount(@PathVariable("account") String account) {
        System.out.println("Get user " + account);
        Result r = new Result();
        if (null == account) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        //Long uid = userService.getUserByAccount(account).getId();
        User user = userService.getUserByAccount(account);

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(user);
        return r;
    }

    @GetMapping("/searchUser/{account}")
    @LogAnnotation(module = "用户", operation = "搜索用户")
    public Result searchByTitle(@PathVariable String account) {
        String pattern = String.format("%%%s%%", account);
        System.out.println("Search by username " + pattern);
        List<User> users = userService.listUsersByNicknameLike(pattern);
        return Result.success(users);
    }

    @GetMapping("/{id}")
    @LogAnnotation(module = "用户", operation = "根据id获取用户")
    @RequiresAuthentication
    public Result getUserById(@PathVariable("id") Long id) {
        System.out.println("Getting name of " + id);

        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        User user = userService.getUserById(id);

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(user);
        return r;
    }

    @GetMapping("/currentUser")
    @FastJsonView(include = { @FastJsonFilter(clazz = User.class, props = { "id", "account", "nickname", "avatar" }) })
    @LogAnnotation(module = "用户", operation = "获取当前登录用户")
    public Result getCurrentUser(HttpServletRequest request) {

        Result r = new Result();

        User currentUser = UserUtils.getCurrentUser();

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(currentUser);
        return r;
    }

    @PostMapping("/create")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "用户", operation = "添加用户")
    public Result saveUser(@Validated @RequestBody User user) {

        Long userId = userService.saveUser(user);

        Result r = Result.success();
        r.simple().put("userId", userId);
        return r;
    }

    @PostMapping("/update")
    @RequiresAuthentication
    @LogAnnotation(module = "用户", operation = "修改用户")
    public Result updateUser(@RequestBody User user) {
        Result r = new Result();
        User origin = userService.getUserByAccount(user.getAccount());
        if (null == origin) {
            r.setResultCode(ResultCode.USER_NOT_EXIST);
            return r;
        }
        user.setId(origin.getId());
        Long userId = userService.updateUser(user);

        r.setResultCode(ResultCode.SUCCESS);
        r.simple().put("userId", userId);
        return r;
    }

    @GetMapping("/delete/{id}")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "用户", operation = "删除用户")
    public Result deleteUserById(@PathVariable("id") Long id) {
        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        userService.deleteUserById(id);

        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }
}
