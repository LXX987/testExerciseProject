package com.huaqi.zhanxin.mapper;


import com.huaqi.zhanxin.entity.VideoInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface VideoMapper {
    @Select("select * from Video_Info")
    List<VideoInfo> selectAll();

    @Insert("insert into Video_Info(video_name,video_time,video_url,video_intro,video_test,video_answer)" +
            " values (#{video_name},#{video_time},#{video_url},#{video_intro},#{video_test},#{video_answer})")
    void insert(@Param("video_name")String video_name, @Param("video_time")LocalDateTime video_time, @Param("video_url")String video_url,
                @Param("video_intro")String video_intro, @Param("video_test")String video_test, @Param("video_answer")String video_answer);

    @Delete("delete from Video_Info where video_id=#{id}")
    Integer deleteOneById(Integer id);
}