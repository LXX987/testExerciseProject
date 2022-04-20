package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService {

    public String uploadFile(int userID, MultipartFile file, String picType) throws Exception;
    int getCurNumber();
    Picture getHouse(int userId);
    List<Picture> pictureList();
}
