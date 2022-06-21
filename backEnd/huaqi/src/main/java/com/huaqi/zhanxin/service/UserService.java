package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    public List<UserBean> userList();
    public UserBean login(String userEmail);
    public UserBean selectName(int userID);
}
