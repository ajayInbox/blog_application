package com.aj.blog.article_tag;

import com.aj.blog.article_tag.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {

    Optional<ArticleTag> findByTagLabel(String tagLabel);

}
