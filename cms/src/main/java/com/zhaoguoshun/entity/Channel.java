package com.zhaoguoshun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.zhaoguoshun.utils.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 栏目
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_channel")
@ApiModel(value="Channel对象", description="栏目")
public class Channel extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "上级栏目")
    private Integer parentId;

    @ApiModelProperty(value = "栏目图片")
    private String channelImg;

    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "是否单页 .Y为单页，Null为不单页")
    private String single;

    @ApiModelProperty(value = "外链URL")
    private String url;

    @ApiModelProperty(value = "SEO标题")
    private String seoTitle;

    @ApiModelProperty(value = "SEO关键字")
    private String seoKeyword;

    @ApiModelProperty(value = "SEO描述")
    private String seoDescription;

    @ApiModelProperty(value = "正文")
    private String content;

    @ApiModelProperty(value = "排序")
    private Integer orderby;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    @ApiModelProperty(value = "D删除")
    private String deletedFlag;
    @ApiModelProperty(value = "发布人名称")
    private String userName;

    private String pos;

}
