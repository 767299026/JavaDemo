package com.lsl.spring.dao.impl;

import com.lsl.spring.dao.PageViewDao;
import com.lsl.spring.entity.PageViews;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PageViewDaoImpl implements PageViewDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertPageView(String pageUrl) {
        String sql = "INSERT INTO page_views (page_url,visit_count) VALUES (?,1);";
        jdbcTemplate.update(sql, pageUrl);
    }

    @Override
    public void incrementPageViews(String pageUrl) {
        String sql = "UPDATE page_views set visit_count = visit_count + 1 where page_url = ?";
        jdbcTemplate.update(sql, pageUrl);
    }

    @Override
    public PageViews getPageViews(String pageUrl) {
        String sql = "SELECT * FROM page_views where page_url = ?";
        Object[] params = {pageUrl};
        RowMapper<PageViews> rowMapper = (rs, rowNum) -> {
            PageViews pageViews = new PageViews();
            pageViews.setId(rs.getInt("id"));
            pageViews.setPageUrl(rs.getString("page_url"));
            pageViews.setVisitCount(rs.getInt("visit_count"));
            return pageViews;
        };
        List<PageViews> pageViews = jdbcTemplate.query(sql, params, rowMapper);
        if (pageViews.isEmpty()) {
            return null;
        } else {
            return pageViews.get(0);
        }
    }
}
