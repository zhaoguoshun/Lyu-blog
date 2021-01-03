package com.zhaoguoshun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

import com.zhaoguoshun.utils.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_comment")
@ApiModel(value="Comment对象", description="评论")
public class Comment extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "评论者")
    private String author;

    @ApiModelProperty(value = "邮箱")
    private String email;


    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "正文")
    private String content;

    @ApiModelProperty(value = "0 待批准，1已通过2未通过")
    private Integer status;

    @ApiModelProperty(value = "文章ID")
    private Integer articleId;

    private String statusName;


}
