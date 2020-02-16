package com.functionwall.pojo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Item implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String realnameUser;//用户名字
    private String link;//用户联系方式
    private String type;//类型 丢/捡
    private String category;//物品分类
    private String content;//帖子内容
    private String imageUrl;//帖子图片
    private Date createdDate;//创建时间
    private Long idUser;
}
