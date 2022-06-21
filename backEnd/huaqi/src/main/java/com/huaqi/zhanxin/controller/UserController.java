package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.service.UserService;
import com.huaqi.zhanxin.tools.GetInformationFromRequest;
import com.huaqi.zhanxin.tools.JwtConfig;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    RestControllerHelper helper = new RestControllerHelper();


    @RequestMapping("show")
    public List<UserBean> userList(){
        return userService.userList();
    }

    @ApiOperation(value = "验证身份")
    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public Result<?> verifyIdentity(@RequestParam String userEmail, @RequestParam String userPassword)
    {
        UserBean user = userService.login(userEmail);
        if(user==null)
            return Result.error("404", "用户不存在");
        else {
            if(userPassword.equals(user.getUserPwd()) && user.getUserType().equals(1))
            {
                return Result.success();
            }
            else return Result.error("403", "验证失败");
        }
    }

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Map<String, Object> login(String userEmail, String userPassword, Integer userType, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(userEmail)) {
            map.put("msg", "关键数据缺失");
            return map;
        }
        UserBean user = userService.login(userEmail);
        if (user == null) {
            map.put("msg", "用户不存在");
            return map;
        }
        if (userPassword.equals(user.getUserPwd())) {
            if (Objects.equals(userType, user.getUserType())) {
                try {
                    Map<String, String> payload = new HashMap<>();
                    payload.put("userEmail", userEmail);
                    payload.put("userId", String.valueOf(user.getUserID()));
                    String token = JwtConfig.getToken(payload);
                    map.put("msg", "登录成功");
                    map.put("token", token);
                    HttpSession session = request.getSession();//session的创建
                    session.setAttribute("userEmail", userEmail);
                    session.setMaxInactiveInterval(15 * 60);
                    Cookie cookie = new Cookie("JSESSIONID", URLEncoder.encode(session.getId(), StandardCharsets.UTF_8));
                    cookie.setPath(request.getContextPath());
                    cookie.setMaxAge(48 * 60 * 60);//设置cookie有效期为2天
                    response.addCookie(cookie);
                } catch (Exception e) {
                    map.put("msg", e.getMessage());
                }
            }
            else map.put("msg", "账号权限错误");
        } else {
            map.put("msg", "密码错误");
        }
        return map;
    }

    @ApiOperation(value = "获取身份信息")
    @GetMapping("userInfo")
    public Map<String, Object> userInfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID =1;
        UserBean user = userService.selectName(userID);
        map.put("user_id", userID);
        map.put("userName", user.getUserName());
        map.put("userEmail", user.getUserEmail());
        map.put("userAvatar", user.getUserAvatar());
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();

    }

    @ApiOperation(value = "获取用户姓名")
    @GetMapping("getUserName")
    public Map<String, Object> getUserName(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        GetInformationFromRequest getInfo = new GetInformationFromRequest(request);
        int userID = getInfo.getUserId();
        //int userID = 4;
        UserBean user = userService.selectName(userID);
        map.put("userName", user.getUserName());
        map.put("userID", user.getUserID());
        map.put("userEmail", user.getUserEmail());
        map.put("userAvatar", user.getUserAvatar());
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
}
