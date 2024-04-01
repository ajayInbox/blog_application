package com.aj.blog.article;

import com.aj.blog.article_tag.ArticleTag;
import com.aj.blog.article_tag.ArticleTagService;
import com.aj.blog.dto.ArticleDTO;
import com.aj.blog.response.ArticleResultForLatestContainerDTO;
import com.aj.blog.response.ArticleWithoutUserDTO;
import com.aj.blog.user.AppUser;
import com.aj.blog.user.ApplicationUserService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private final ArticleRepository articleRepository;
    private final ArticleTagService tagService;
    private final ApplicationUserService userService;

    Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleTagService tagService,
                              ApplicationUserService userService) {
        this.articleRepository = articleRepository;
        this.tagService = tagService;
        this.userService = userService;
    }

    @Override
    public List<Article> getAllArticles() throws Exception {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    @Override
    public void addArticle(ArticleDTO articleDTO) {
        Collection<ArticleTag> tags = new HashSet<>();
        for(String tag:articleDTO.getArticleTags().split(",")){
            tags.add(tagService.createTag(tag));
        }
        String email = userService.getUserWithSecurityContext();
        AppUser user = userService.getUserWithEmail(email);
        Article newArticle = Article.builder()
                .articleTitle(articleDTO.getArticleTitle())
                .articleContent(articleDTO.getArticleContent())
                .articleBannerUrl(articleDTO.getArticleBannerUrl())
                .tags(tags)
                .createdAt(LocalDate.now())
                .user(user)
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

    @Override
    public List<ArticleWithoutUserDTO> getAllArticlesWithoutUser() {
//        List<Article> articles = articleRepository.findAll();
//        List<ArticleWithoutUserDTO> result = new ArrayList<>();
//        for(Article article: articles){
//            ArticleWithoutUserDTO dto = mapper.map(article, ArticleWithoutUserDTO.class);
//            result.add(dto);
//        }
//        return result;
        return articleRepository.findArticlesWithoutUser();
    }

    @Override
    public ArticleResultForLatestContainerDTO getArticleForLatestContainer(Long articleId) {
        return articleRepository.getArticleForLatestContainer(articleId);
    }

    @Override
    public List<Article> getAllArticlesWithTagId(Long tagId) {
        return articleRepository.findAllArticlesByTagId(tagId);
    }
}
