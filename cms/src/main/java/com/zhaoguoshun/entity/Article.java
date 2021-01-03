package com.zhaoguoshun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zhaoguoshun.utils.Entity;
import com.zhaoguoshun.vo.ArticleVo;
import com.zhaoguoshun.vo.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_article")
@ApiModel(value="Article对象", description="文章")
public class Article extends Entity implements Serializable  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "栏目id")
    private Integer channelId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "标题图")
    private String titleImg;

    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "外链URL")
    private String url;

    @ApiModelProperty(value = "正文")
    private String content;

    @ApiModelProperty(value = "0待发布1发布")
    private Integer status;

    @ApiModelProperty(value = "0/Null允许评论，不允许评论")
    private Integer commentStatus;

    @ApiModelProperty(value = "0/NULL非轮播 1 轮播")
    private Integer rotation;

    @ApiModelProperty(value = "0/null不置顶，1置顶")
    private Integer top;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    @ApiModelProperty(value = "最后修改时间")
    private Date updateDate;

    private List<Map<String,Object>> articleAttachments;

    private List<Integer> selectTagList;

    private User user;

    private List<Page> pageList;

    @ApiModelProperty(value = "栏目")
    private Channel channel;

    private List<ArticleVo> articleVo;

    private Integer articleView;

    private Boolean front;
}
