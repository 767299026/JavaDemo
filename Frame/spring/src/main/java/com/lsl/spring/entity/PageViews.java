package com.lsl.spring.entity;

import lombok.Data;

@Data
public class PageViews {

    private Integer id;
    private String pageUrl;
    private long visitCount;

}
