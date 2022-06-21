package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.mapper.UserMapper;
import com.huaqi.zhanxin.service.UserService;
import com.huaqi.zhanxin.tools.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Value("${path.picture-upload-path}")
    private String PIC_UPLOAD_PATH;

    @Override
    public List<UserBean> userList() {
        return userMapper.userList();
    }

    @Override
    public UserBean login(String userEmail) {
        return userMapper.loginUser(userEmail);
    }

    @Override
    public UserBean selectName(int userID) {
        return userMapper.selectName(userID);
    }
}
