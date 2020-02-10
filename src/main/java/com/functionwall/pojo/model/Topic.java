package com.functionwall.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
    private String category;//帖子分类
    private String content;//帖子内容
    private Byte[] image;//帖子图片
    private Date createdDate;//创建时间
    private Integer userId;
}
