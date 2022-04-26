package com.huaqi.zhanxin.controller;



import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.common.Result;
import com.huaqi.zhanxin.service.VideoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController

public class VideoController {
    @Resource
    VideoService videoService;

    @ApiOperation(value = "上传视频")
    @RequestMapping(value = "/admin/videos", method = RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<?> uploadVideo(@RequestParam(name="video") MultipartFile video, @RequestParam(name="cover") MultipartFile cover, String title, String intro, String type, HttpServletRequest request)
    {
        String uploadInfo = videoService.uploadVideo(video, cover, title, intro, type, request);
        if(uploadInfo.equals("-1"))
            return Result.error("500","上传失败");
        else return Result.success(uploadInfo);
    }

    @ApiOperation(value = "查看视频列表")
    @RequestMapping(value = "/common/videos/{type}", method = RequestMethod.GET)
    public Result<?> getVideoList(@PathVariable String type)
    {
        List<JSONObject> jsonObjects = videoService.getVideoList(type);
        if(jsonObjects == null)
            return Result.error("404", "暂无视频");
        else return Result.success(jsonObjects);
    }

    @ApiOperation(value = "删除视频")
    @RequestMapping(value = "/admin/videos/{id}", method = RequestMethod.DELETE)
    public Result<?> deleteVideo(@PathVariable Integer id)
    {
        Integer count = videoService.deleteVideo(id);
        if(count == -1)
            return Result.error("500", "删除失败");
        else return Result.success();
    }



}
