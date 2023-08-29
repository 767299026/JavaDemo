package com.lsl.spring.dao;

import com.lsl.spring.entity.PageViews;

public interface PageViewDao {

    void insertPageView(String pageUrl);

    void incrementPageViews(String pageUrl);

    PageViews getPageViews(String pageUrl);
}
