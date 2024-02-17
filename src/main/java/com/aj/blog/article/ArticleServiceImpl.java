package com.aj.blog.article;

import com.aj.blog.dto.ArticleDTO;
import com.aj.blog.article_tag.ArticleTag;
import com.aj.blog.exception.ArticleNotFoundException;
import com.aj.blog.article_tag.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private final ArticleRepository articleRepository;
    private final ArticleTagService tagService;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleTagService tagService) {
        this.articleRepository = articleRepository;
        this.tagService = tagService;
    }

    @Override
    public List<Article> getAllArticles() throws Exception {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            throw new ArticleNotFoundException("no articles available");
        }
        return articles;
    }

    @Override
    public void addArticle(ArticleDTO articleDTO) {
        Collection<ArticleTag> tags = new HashSet<>();
        for(String tag:articleDTO.getArticleTags().split(",")){
            tags.add(tagService.createTag(tag));
        }
        Article newArticle = Article.builder()
                .articleTitle(articleDTO.getArticleTitle())
                .articleContent(articleDTO.getArticleContent())
                .articleBannerUrl(articleDTO.getArticleBannerUrl())
                .tags(tags)
                .build();

        articleRepository.save(newArticle);
    }

    @Override
    public Article getIndividualArticle(Long articleId) throws Exception {
        Optional<Article> ops = articleRepository.findById(articleId);
        Article article;
        article = ops.orElseThrow(()-> new Exception("Article not found"));

        return article;
    }
}
