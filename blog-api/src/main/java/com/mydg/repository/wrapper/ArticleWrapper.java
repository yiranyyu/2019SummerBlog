package com.mydg.repository.wrapper;

import com.mydg.entity.Article;
import com.mydg.vo.ArticleVo;
import com.mydg.vo.PageVo;

import java.util.List;

public interface ArticleWrapper {
    List<Article> listArticles(PageVo page);

    List<Article> listArticles(ArticleVo article, PageVo page);

    List<ArticleVo> listArchives();
}
