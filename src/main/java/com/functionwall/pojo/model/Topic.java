package com.functionwall.pojo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Yu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic implements Serializable {

    private Long id;
    private String title;//帖子标题
    private String username;//用户名字
    private String link;//用户联系方式
    private String content;//帖子内容
    private String imageUrl;//帖子图片
    private Date createdDate;//创建时间
    private Long idUser;
}
