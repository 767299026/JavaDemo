package com.lsl.spring.interceptor;

import com.lsl.spring.entity.PageViews;
import com.lsl.spring.service.PageViewService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PageViewInterceptor implements HandlerInterceptor {

    @Resource
    private PageViewService pageViewService;

    @Transactional
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String pageUrl = httpServletRequest.getRequestURI();
        pageViewService.incrementPageView(pageUrl);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        PageViews pageViews = pageViewService.getPageViews(httpServletRequest.getRequestURI());
        long visitCount = pageViews.getVisitCount();
        modelAndView.addObject("visitCount", visitCount);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
