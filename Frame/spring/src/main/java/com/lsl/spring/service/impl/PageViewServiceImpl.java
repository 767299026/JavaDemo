package com.lsl.spring.service.impl;

import com.lsl.spring.dao.PageViewDao;
import com.lsl.spring.entity.PageViews;
import com.lsl.spring.service.PageViewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PageViewServiceImpl implements PageViewService {

    @Resource
    private PageViewDao pageViewDao;

    @Override
    public void incrementPageView(String pageUrl) {
        PageViews pageViews = pageViewDao.getPageViews(pageUrl);
        if (pageViews == null) {
            pageViewDao.insertPageView(pageUrl);
        } else {
            pageViewDao.incrementPageViews(pageUrl);
        }

    }

    @Override
    public PageViews getPageViews(String pageUrl) {
        return pageViewDao.getPageViews(pageUrl);
    }
}
