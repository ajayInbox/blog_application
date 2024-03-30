package com.aj.blog.article_tag;

import com.aj.blog.article_tag.ArticleTag;
import com.aj.blog.response.TagsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {

    Optional<ArticleTag> findByTagLabel(String tagLabel);

    @Query("SELECT NEW com.aj.blog.response.TagsDTO(t.tagId, t.tagLabel) FROM ArticleTag t")
    List<TagsDTO> findAllV2();

}
