package com.lsl.spring.service;

import com.lsl.spring.entity.PageViews;

public interface PageViewService {

    void incrementPageView(String pageUrl);

    PageViews getPageViews(String pageUrl);
}
