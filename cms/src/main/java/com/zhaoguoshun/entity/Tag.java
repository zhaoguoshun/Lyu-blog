package com.zhaoguoshun.entity;

import com.zhaoguoshun.utils.Entity;
import lombok.Data;

@Data
public class Tag extends Entity {
    private Integer id;
    private String tagName;
}
